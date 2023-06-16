package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub01Entity;
import com.nifou.m.ifou_mobile_was.entity.result.Sub02Entity;
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
public class Sub02RepositoryImpl implements Sub02Repository{
        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public ArrayList<Sub02Entity> getSub02(String orgcd, String setWhere) {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT ")
                    .append("    ROW_NUMBER() OVER(ORDER BY TERM_NM) AS id, ")
                    .append("    DEP_NM AS depnm, ")
                    .append("    TID AS tid, ")
                    .append("    TERM_NM AS tidnm, ")
                    .append("    TRIM(SUM(ACNT + CCNT)) AS totcnt, ")
                    .append("    TRIM(SUM(AAMT - CAMT)) AS totamt, ")
                    .append("    APPDD AS appdd ")
                    .append("FROM( ")
                    .append("    SELECT ")
                    .append("        TID, ")
                    .append("        CASE ")
                    .append("            WHEN APPGB='A' THEN COUNT(1) ELSE 0 ")
                    .append("        END AS ACNT, ")
                    .append("        CASE ")
                    .append("            WHEN APPGB='A' THEN SUM(AMOUNT) ELSE 0 ")
                    .append("        END AS AAMT, ")
                    .append("        CASE ")
                    .append("            WHEN APPGB='C' THEN COUNT(1) ELSE 0 ")
                    .append("        END AS CCNT, ")
                    .append("        CASE ")
                    .append("            WHEN APPGB='C' THEN SUM(AMOUNT) ELSE 0 ")
                    .append("        END AS CAMT, ")
                    .append("        APPDD ")
                    .append("    FROM ")
                    .append("        GLOB_MNG_ICVAN ")
                    .append("        WHERE TID IN (SELECT TID FROM TB_BAS_TIDMAP WHERE ORG_CD = :orgcd)")
                    .append("        AND TID IS NOT NULL");

            // 동적쿼리 추가
            if (setWhere != null && !setWhere.isEmpty()) {
                queryBuilder.append(setWhere);
            }

                queryBuilder.append("    GROUP BY TID, APPDD, APPGB ")
                    .append(") T1 ")
                    .append("LEFT OUTER JOIN( ")
                    .append("    SELECT TERM_ID, TERM_NM, DEP_CD FROM TB_BAS_TIDMST WHERE ORG_CD = :orgcd")
                    .append(") T3 ON(T3.TERM_ID=T1.TID) ")
                    .append("LEFT OUTER JOIN( ")
                    .append("    SELECT DEP_CD, DEP_NM FROM TB_BAS_DEPART WHERE ORG_CD = :orgcd")
                    .append(") T2 ON(T3.DEP_CD=T2.DEP_CD) ")
                    .append("GROUP BY T3.DEP_CD, DEP_NM, TID, TERM_NM, APPDD ")
                    .append("ORDER BY T3.DEP_CD, TERM_NM, APPDD");

            Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub02Entity.class);
            query.setParameter("orgcd", orgcd);

            ArrayList<Sub02Entity> resultList = (ArrayList<Sub02Entity>) query.getResultList();
            return resultList;
        }

    @Override
    public List<Sub02Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub02Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub02Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub02Entity> findAllById(Iterable<Long> longs) {
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
    public void delete(Sub02Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub02Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub02Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub02Entity> findById(Long aLong) {
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
    public <S extends Sub02Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub02Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub02Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub02Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub02Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub02Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub02Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub02Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub02Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
