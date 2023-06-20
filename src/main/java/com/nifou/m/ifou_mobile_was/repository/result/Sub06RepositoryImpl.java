package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub06Entity;
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
public class Sub06RepositoryImpl implements Sub06Repository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Sub06Entity> getSub06(String orgcd, String setWhere, String expddWhere,String acqWhere) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ")
                .append("    ROW_NUMBER() OVER(ORDER BY DEP_NM)     AS  id, ")
                .append("    T1.EXP_DD                                 AS  expdd, ")
                .append("    DEP_NM                                 AS  depnm, ")
                .append("    PUR_NM                                 AS  acqnm, ")
                .append("    T7.PUR_KOCES                                 AS  acqcd, ")
                .append("    SUM(T_EXP)                             AS   totamt ")
                .append("FROM ( ")
                .append("    SELECT ")
                .append("        MID, EXP_DD, ACQ_CD,  ")
                .append("        SUM(TOT_CNT) T_CNT, SUM(TOT_BAN) T_BAN, SUM(TOT_NETAMT) T_AMT, SUM(TOT_INPAMT) T_FEE, SUM(TOT_EXPAMT) T_EXP, ")
                .append("        SUM(I_CNT) I_CNT, SUM(I_BAN) I_BAN, SUM(I_AMT) I_AMT, SUM(I_FEE) I_FEE, SUM(I_EXP) I_EXP ")
                .append("    FROM ( ")
                .append("        SELECT ")
                .append("            MID, EXP_DD, DEP_SEQ, ")
                .append("            SUM(TOT_CNT) TOT_CNT, SUM(BAN_CNT) TOT_BAN, (SUM(EXP_AMT)+SUM(INP_AMT)) TOT_NETAMT, ")
                .append("            SUM(INP_AMT) TOT_INPAMT, SUM(EXP_AMT) TOT_EXPAMT ")
                .append("        FROM ")
                .append("            TB_MNG_DEPTOT ")
                .append("        WHERE " + setWhere + " AND " + expddWhere)
                .append("        GROUP BY MID, EXP_DD, DEP_SEQ ")
                .append("        ORDER BY EXP_DD DESC ")
                .append("    ) T1 ")
                .append("    LEFT OUTER JOIN( ")
                .append("        SELECT ")
                .append("            DEP_SEQ, ACQ_CD, ")
                .append("            (SUM(ITEM_CNT60)+SUM(ITEM_CNT67)) I_CNT, SUM(ITEM_CNTBAN) I_BAN, (SUM(ITEM_AMT60)-SUM(ITEM_AMT67)) I_AMT, ")
                .append("            (SUM(ITEM_FEE60)-SUM(ITEM_FEE67)) I_FEE, (SUM(ITEM_AMT60)-SUM(ITEM_AMT67))-(SUM(ITEM_FEE60)-SUM(ITEM_FEE67)) I_EXP ")
                .append("        FROM ( ")
                .append("            SELECT ")
                .append("                DEP_SEQ, ")
                .append("                ACQ_CD, ")
                .append("                CASE WHEN RTN_CD='60' THEN COUNT(1) ELSE 0 END ITEM_CNT60, ")
                .append("                CASE WHEN RTN_CD='67' THEN COUNT(1) ELSE 0 END ITEM_CNT67, ")
                .append("                CASE WHEN RTN_CD NOT IN ('60', '67') THEN COUNT(1) ELSE 0 END ITEM_CNTBAN, ")
                .append("                CASE WHEN RTN_CD='60' THEN SUM(SALE_AMT) ELSE 0 END ITEM_AMT60, ")
                .append("                CASE WHEN RTN_CD='67' THEN SUM(SALE_AMT) ELSE 0 END ITEM_AMT67, ")
                .append("                CASE WHEN RTN_CD='60' THEN SUM(FEE) ELSE 0 END ITEM_FEE60, ")
                .append("                CASE WHEN RTN_CD='67' THEN SUM(FEE) ELSE 0 END ITEM_FEE67 ")
                .append("            FROM ")
                .append("                TB_MNG_DEPDATA ")
                .append("            WHERE " + setWhere + " AND " + expddWhere )
                .append("            GROUP BY DEP_SEQ, RTN_CD, ACQ_CD ")
                .append("        ) ")
                .append("        GROUP BY DEP_SEQ, ACQ_CD ")
                .append("    ) T2 ON (T1.DEP_SEQ=T2.DEP_SEQ) ")
                .append("    GROUP BY MID, EXP_DD, ACQ_CD ")
                .append(") T1 ")
                .append("LEFT OUTER JOIN( ")
                .append("    SELECT EXP_DD, MID, EXP_AMT BANK_AMT FROM TB_MNG_BANKDATA ")
                .append("    GROUP BY EXP_DD, MID, EXP_AMT ")
                .append(") T3 ON (T1.MID=T3.MID AND T1.EXP_DD=T3.EXP_DD) ")
                .append("LEFT OUTER JOIN( ")
                .append("    SELECT ORG_CD, DEP_CD, MER_NO, PUR_CD FROM TB_BAS_MERINFO ")
                .append(") T4 ON (T1.MID=T4.MER_NO) ")
                .append("LEFT OUTER JOIN( ")
                .append("    SELECT ORG_CD, ORG_NM FROM TB_BAS_ORG ")
                .append(") T5 ON (T4.ORG_CD=T5.ORG_CD) ")
                .append("LEFT OUTER JOIN( ")
                .append("    SELECT DEP_CD, DEP_NM FROM TB_BAS_DEPART ")
                .append(") T6 ON (T4.DEP_CD=T6.DEP_CD) ")
                .append("LEFT OUTER JOIN( ")
                .append("    SELECT PUR_CD, PUR_NM, PUR_SORT, PUR_KOCES,PUR_OCD FROM TB_BAS_PURINFO ")
                .append(") T7 ON (T4.PUR_CD=T7.PUR_CD) ")
                .append("WHERE T1." + setWhere+ " AND T1."+expddWhere + " AND DEP_NM IS NOT NULL AND PUR_NM IS NOT NULL" + acqWhere)
                .append(" GROUP BY DEP_NM, T1.EXP_DD, PUR_NM, T7.PUR_KOCES ")
                .append(" ORDER BY DEP_NM, T1.EXP_DD ASC, DEP_NM ASC, PUR_NM ASC");



        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub06Entity.class);
//        query.setParameter("orgcd", orgcd);

        ArrayList<Sub06Entity> resultList = (ArrayList<Sub06Entity>) query.getResultList();
        return resultList;
    }

    @Override
    public List<Sub06Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub06Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub06Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub06Entity> findAllById(Iterable<Long> longs) {
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
    public void delete(Sub06Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub06Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub06Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub06Entity> findById(Long aLong) {
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
    public <S extends Sub06Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub06Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub06Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub06Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub06Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub06Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub06Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub06Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub06Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}