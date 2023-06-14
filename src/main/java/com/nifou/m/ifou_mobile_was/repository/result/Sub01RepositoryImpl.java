package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
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
public class Sub01RepositoryImpl implements Sub01Repository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Sub01Entity> getSub01(String orgcd, String setWhere) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT  ROW_NUMBER() OVER(ORDER BY PUR_NM) AS id, DEP_NM AS depnm, ")
                .append("       PUR_NM AS acqnm, ")
                .append("       ACQ_CD AS acqcd, ")
                .append("       TRIM(TO_CHAR((SUM(ACNT) + SUM(CCNT)), '999,999,999,999,999,999')) AS totcnt, ")
                .append("       TRIM(TO_CHAR((SUM(AAMT) - SUM(CAMT)), '999,999,999,999,999,999')) AS totamt, ")
                .append("       APPDD AS appdd ")
                .append("FROM (")
                .append("  SELECT TID, ")
                .append("         MID, ")
                .append("         ACQ_CD, ")
                .append("         SUM(ACNT) AS ACNT, ")
                .append("         SUM(CCNT) AS CCNT, ")
                .append("         SUM(AAMT) AS AAMT, ")
                .append("         SUM(CAMT) AS CAMT, ")
                .append("         APPDD ")
                .append("  FROM (")
                .append("    SELECT TID, ")
                .append("           MID, ")
                .append("           ACQ_CD, ")
                .append("           APPDD, ")
                .append("           CASE WHEN APPGB = 'A' THEN COUNT(1) ELSE 0 END AS ACNT, ")
                .append("           CASE WHEN APPGB = 'A' THEN SUM(AMOUNT) ELSE 0 END AS AAMT, ")
                .append("           CASE WHEN APPGB = 'C' THEN COUNT(1) ELSE 0 END AS CCNT, ")
                .append("           CASE WHEN APPGB = 'C' THEN SUM(AMOUNT) ELSE 0 END AS CAMT ")
                .append("    FROM GLOB_MNG_ICVAN ")
                .append("    WHERE SVCGB IN ('CC', 'CE') ")
                .append("      AND AUTHCD IN ('0000', '6666') ");

        // 동적인 조건 추가
        if (setWhere != null && !setWhere.isEmpty()) {
            queryBuilder.append(setWhere);
        }

        queryBuilder.append("    GROUP BY TID, MID, ACQ_CD, APPGB, APPDD")
                .append("  ) ")
                .append("  GROUP BY TID, MID, ACQ_CD, APPDD")
                .append(") T1 ")
                .append("LEFT OUTER JOIN (")
                .append("  SELECT PUR_NM, PUR_KOCES, PUR_OCD, PUR_SORT FROM TB_BAS_PURINFO")
                .append(") T2 ON (T1.ACQ_CD = T2.PUR_OCD OR T1.ACQ_CD = T2.PUR_KOCES) ")
                .append("LEFT OUTER JOIN (")
                .append("  SELECT DEP_CD, TERM_NM, TERM_ID FROM TB_BAS_TIDMST WHERE ORG_CD = :orgcd")
                .append(") T3 ON (T1.TID = T3.TERM_ID) ")
                .append("LEFT OUTER JOIN (")
                .append("  SELECT DEP_NM, DEP_CD FROM TB_BAS_DEPART WHERE ORG_CD = :orgcd")
                .append(") T4 ON (T3.DEP_CD = T4.DEP_CD) ")
                .append("GROUP BY ACQ_CD, PUR_NM, APPDD, DEP_NM ")
                .append("ORDER BY PUR_NM ASC");

        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub01Entity.class);
        query.setParameter("orgcd", orgcd);

        ArrayList<Sub01Entity> resultList = (ArrayList<Sub01Entity>) query.getResultList();
        return resultList;
    }

    @Override
    public List<Sub01Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub01Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub01Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub01Entity> findAllById(Iterable<Long> longs) {
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
    public void delete(Sub01Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub01Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub01Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub01Entity> findById(Long aLong) {
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
    public <S extends Sub01Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub01Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub01Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub01Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub01Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub01Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub01Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub01Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub01Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
