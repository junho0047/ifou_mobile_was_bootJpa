package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub05Entity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class Sub05RepositoryImpl implements Sub05Repository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Sub05Entity> getSub05(String orgcd, String setWhere) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ")
            .append("    ROW_NUMBER() OVER(ORDER BY DEP_NM) AS id, ")
            .append("    S4.DEP_NM depnm, ")
            .append("    TO_CHAR(TO_DATE(S1.APPDD, 'YYYYMMDD'), 'YYYYMMDD') AS appdd, ")
            .append("    SUM(card_amount) + SUM(check_amount) AS totsales, ")
            .append("    SUM(card_fee) + SUM(check_fee) AS totfee, ")
            .append("    (SUM(card_amount) + SUM(check_amount)) - (SUM(card_fee) + SUM(check_fee)) AS totsaleamt, ")
            .append("    (SUM(card_amount) - SUM(card_depo)) + (SUM(check_amount) - SUM(check_depo)) AS totreceivable ")
            .append("FROM ( ")
            .append("    SELECT ")
            .append("        SUM(card_amount_a) - SUM(card_amount_c) AS card_amount, ")
            .append("        SUM(check_amount_a) - SUM(check_amount_c) AS check_amount, ")
            .append("        SUM(card_sale_amt_a) - SUM(card_sale_amt_c) AS card_depo, ")
            .append("        SUM(check_sale_amt_a) - SUM(check_sale_amt_c) AS check_depo, ")
            .append("        SUM(card_fee_a) - SUM(card_fee_c) AS card_fee, ")
            .append("        SUM(check_fee_a) - SUM(check_fee_c) AS check_fee, ")
            .append("        APPDD, TID, ACQ_CD ")
            .append("    FROM ( ")
            .append("        SELECT ")
            .append("            CASE WHEN T1.APPGB = 'A' AND T1.CHECK_CARD != 'Y' THEN amount ELSE 0 END AS card_amount_a, ")
            .append("            CASE WHEN T1.APPGB = 'C' AND T1.CHECK_CARD != 'Y' THEN amount ELSE 0 END AS card_amount_c, ")
            .append("            CASE WHEN T1.APPGB = 'A' AND T1.CHECK_CARD = 'Y' THEN amount ELSE 0 END AS check_amount_a, ")
            .append("            CASE WHEN T1.APPGB = 'C' AND T1.CHECK_CARD = 'Y' THEN amount ELSE 0 END AS check_amount_c, ")
            .append("            CASE WHEN T2.RTN_CD = '60' AND T1.CHECK_CARD != 'Y' THEN sale_amt ELSE 0 END AS card_sale_amt_a, ")
            .append("            CASE WHEN T2.RTN_CD = '67' AND T1.CHECK_CARD != 'Y' THEN sale_amt ELSE 0 END AS card_sale_amt_c, ")
            .append("            CASE WHEN T2.RTN_CD = '60' AND T1.CHECK_CARD = 'Y' THEN sale_amt ELSE 0 END AS check_sale_amt_a, ")
            .append("            CASE WHEN T2.RTN_CD = '67' AND T1.CHECK_CARD = 'Y' THEN sale_amt ELSE 0 END AS check_sale_amt_c, ")
            .append("            CASE WHEN T2.RTN_CD = '60' AND T1.CHECK_CARD != 'Y' THEN fee ELSE 0 END AS card_fee_a, ")
            .append("            CASE WHEN T2.RTN_CD = '67' AND T1.CHECK_CARD != 'Y' THEN fee ELSE 0 END AS card_fee_c, ")
            .append("            CASE WHEN T2.RTN_CD = '60' AND T1.CHECK_CARD = 'Y' THEN fee ELSE 0 END AS check_fee_a, ")
            .append("            CASE WHEN T2.RTN_CD = '67' AND T1.CHECK_CARD = 'Y' THEN fee ELSE 0 END AS check_fee_c, ")
            .append("            APPDD, TID, acq_cd ")
            .append("        FROM ( ")
            .append("            SELECT APPDD, TID, ACQ_CD, APPGB, TRANIDX, AMOUNT, CHECK_CARD ")
            .append("            FROM GLOB_MNG_ICVAN ")
            .append("            WHERE SVCGB IN ('CC', 'CE') ")
            .append("                AND AUTHCD='0000' ");

        // 동적쿼리 추가 (날짜, 사업부)
        if (setWhere != null && !setWhere.isEmpty()) {
            queryBuilder.append(setWhere);
        }

        queryBuilder.append("        ) T1 ")
            .append("        LEFT OUTER JOIN ( ")
            .append("            SELECT EXP_DD, REQ_DD, REG_DD, APP_DD, TRANIDX, RSC_CD, RTN_CD, FEE, SALE_AMT ")
            .append("            FROM TB_MNG_DEPDATA ")
            .append("            WHERE RTN_CD IN ('60','67') ")
            .append("        ) T2 ON (T1.APPDD = T2.APP_DD AND T1.TRANIDX = T2.TRANIDX) ")
            .append("    ) ")
            .append("    GROUP BY APPDD, tid, acq_cd ")
            .append(") S1 ")
            .append("LEFT OUTER JOIN ( ")
            .append("    SELECT TERM_ID, TERM_NM, DEP_CD ")
            .append("    FROM TB_BAS_TIDMST ")
            .append(") S2 ON (S1.TID = S2.TERM_ID) ")
            .append("LEFT OUTER JOIN ( ")
            .append("    SELECT pur_nm, pur_ocd ")
            .append("    FROM tb_bas_purinfo ")
            .append(") S3 ON (S1.ACQ_CD = S3.pur_ocd) ")
            .append("LEFT OUTER JOIN ( ")
            .append("    SELECT dep_cd, dep_nm ")
            .append("    FROM tb_bas_depart ")
            .append(") S4 ON (S2.dep_cd = S4.dep_cd) ")
            .append("GROUP BY S4.DEP_NM, APPDD ")
            .append("ORDER BY S4.DEP_NM, APPDD ");


        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub05Entity.class);
//        query.setParameter("orgcd", orgcd);

        ArrayList<Sub05Entity> resultList = (ArrayList<Sub05Entity>) query.getResultList();
        return resultList;
    }

    @Override
    public List<Sub05Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub05Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub05Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub05Entity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Sub05Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub05Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub05Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub05Entity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Sub05Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub05Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub05Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub05Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub05Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub05Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub05Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub05Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub05Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
