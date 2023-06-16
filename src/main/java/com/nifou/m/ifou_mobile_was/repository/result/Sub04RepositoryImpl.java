package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub04Entity;
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
public class Sub04RepositoryImpl implements Sub04Repository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Sub04Entity> getSub04(String orgcd, String setWhere) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT")
                .append("    ROW_NUMBER() OVER(ORDER BY TERM_NM) AS id, ")
                .append(" TID               AS tid,")
                .append(" TERM_NM           AS tidnm,")
                .append(" DEP_NM            AS depnm,")
                .append(" APPDD             AS appdd,")
                .append(" SUM(TOTCNT)       AS totcnt,")
                .append(" SUM(TOTAMT)       AS totamt")
                .append(" FROM (")
                .append(" SELECT")
                .append(" TID, TERM_NM, DEP_NM, APPDD,")
                .append(" SUM(ACNT) AS ACNT,")
                .append(" SUM(CCNT) AS CCNT,")
                .append(" SUM(AAMT) AS AAMT,")
                .append(" TRUNC(SUM(AFEE)) AS AFEE,")
                .append(" SUM(CAMT) AS CAMT,")
                .append(" TRUNC(SUM(CFEE)) AS CFEE,")
                .append(" SUM(ACNT) + SUM(CCNT) AS TOTCNT,")
                .append(" SUM(AAMT) - SUM(CAMT) AS TOTAMT,")
                .append(" TRUNC(SUM(AFEE) - SUM(CFEE)) AS TOTFEE")
                .append(" FROM (")
                .append(" SELECT")
                .append(" TID, TERM_NM, DEP_NM, APPDD,")
                .append(" CASE WHEN APPGB = 'A' THEN COUNT(1) ELSE 0 END AS ACNT,")
                .append(" CASE WHEN APPGB = 'C' THEN COUNT(1) ELSE 0 END AS CCNT,")
                .append(" CASE WHEN APPGB = 'A' THEN SUM(AMOUNT) ELSE 0 END AS AAMT,")
                .append(" CASE WHEN APPGB = 'C' THEN SUM(AMOUNT) ELSE 0 END AS CAMT,")
                .append(" CASE WHEN APPGB = 'A' THEN SUM(FEE) ELSE 0 END AS AFEE,")
                .append(" CASE WHEN APPGB = 'C' THEN SUM(FEE) ELSE 0 END AS CFEE")
                .append(" FROM (")
                .append(" SELECT")
                .append(" APPDD, APPGB, AMOUNT, TID, TERM_NM, T4.DEP_NM, T4.DEP_CD,")
                .append(" CASE WHEN TRUNC(AMOUNT / 100) < 10 THEN 10 ELSE TRUNC(AMOUNT / 100) END AS FEE")
                .append(" FROM")
                .append(" GLOB_MNG_ICVAN T1")
                .append(" LEFT OUTER JOIN")
                .append(" (SELECT DEP_CD, TERM_NM, TERM_ID FROM TB_BAS_TIDMST WHERE ORG_CD = :orgcd) T3 ON (T1.TID = T3.TERM_ID)")
                .append(" LEFT OUTER JOIN")
                .append(" (SELECT DEP_NM, DEP_CD FROM TB_BAS_DEPART WHERE ORG_CD = :orgcd) T4 ON (T3.DEP_CD = T4.DEP_CD)")
                .append(" WHERE SVCGB IN ('IC') AND AUTHCD = '0000' ");

            // 동적쿼리 추가 (날짜, 사업부, 단말기)
            if (setWhere != null && !setWhere.isEmpty()) {
                queryBuilder.append(setWhere);
            }
        queryBuilder.append(" )")
                .append(" GROUP BY APPDD, APPGB, AMOUNT, TID, TERM_NM, DEP_NM")
                .append(" )")
                .append(" GROUP BY APPDD, TID, TERM_NM, DEP_NM")
                .append(" )")
                .append(" GROUP BY DEP_NM, TID, TERM_NM, APPDD")
                .append(" ORDER BY DEP_NM, APPDD, TID");

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub04Entity.class);
        query.setParameter("orgcd", orgcd);

        ArrayList<Sub04Entity> resultList = (ArrayList<Sub04Entity>) query.getResultList();
        return resultList;
    }

    @Override
    public List<Sub04Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub04Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub04Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub04Entity> findAllById(Iterable<Long> longs) {
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
    public void delete(Sub04Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub04Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub04Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub04Entity> findById(Long aLong) {
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
    public <S extends Sub04Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub04Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub04Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub04Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub04Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub04Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub04Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub04Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub04Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
