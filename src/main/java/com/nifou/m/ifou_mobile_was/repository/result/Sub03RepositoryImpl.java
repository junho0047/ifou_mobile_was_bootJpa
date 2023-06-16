package com.nifou.m.ifou_mobile_was.repository.result;

import com.nifou.m.ifou_mobile_was.entity.result.Sub03Entity;
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
public class Sub03RepositoryImpl implements Sub03Repository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ArrayList<Sub03Entity> getSub03(String orgcd, String setWhere) {
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT")
                .append("    ROW_NUMBER() OVER(ORDER BY TERM_NM) AS id, ")
                .append("    APPDD          AS appdd,")
                .append("    DEP_NM         AS depnm,")
                .append("    TID            AS tid,")
                .append("    TERM_NM        AS tidnm,")
                .append("    TOTCNT         AS totcnt,")
                .append("    TOTAMT         AS totamt")
                .append("  FROM (")
                .append("    SELECT")
                .append("        TID,")
                .append("        TERM_NM,")
                .append("        DEP_NM,")
                .append("        APPDD,")
                .append("        SUM(ACNT) ACNT,")
                .append("        SUM(CCNT) CCNT,")
                .append("        SUM(AAMT) AAMT,")
                .append("        SUM(CAMT) CAMT,")
                .append("        SUM(ACNT)+SUM(CCNT) TOTCNT,")
                .append("        SUM(AAMT)-SUM(CAMT) TOTAMT,")
                .append("        SUM(ABC )-SUM(CBC ) BC,")
                .append("        SUM(ANH )-SUM(CNH ) NH,")
                .append("        SUM(AKB )-SUM(CKB ) KB,")
                .append("        SUM(ASS )-SUM(CSS ) SS,")
                .append("        SUM(AHN )-SUM(CHN ) HN,")
                .append("        SUM(ALO )-SUM(CLO ) LO,")
                .append("        SUM(AHD )-SUM(CHD ) HD,")
                .append("        SUM(ASI )-SUM(CSI ) SI")
                .append("    FROM(")
                .append("        SELECT")
                .append("            TID,")
                .append("            TERM_NM,")
                .append("            DEP_NM,")
                .append("            APPDD,")
                .append("            CASE WHEN APPGB='A' THEN COUNT(1) ELSE 0 END ACNT,")
                .append("            CASE WHEN APPGB='C' THEN COUNT(1) ELSE 0 END CCNT,")
                .append("            CASE WHEN APPGB='A' THEN SUM(AMOUNT) ELSE 0 END AAMT,")
                .append("            CASE WHEN APPGB='C' THEN SUM(AMOUNT) ELSE 0 END CAMT,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0006', '026', '1106','01') THEN SUM(AMOUNT) ELSE 0 END ABC,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0030', '018', '2211','11') THEN SUM(AMOUNT) ELSE 0 END ANH,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0001', '016', '1101','02') THEN SUM(AMOUNT) ELSE 0 END AKB,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0004', '031', '1104','06') THEN SUM(AMOUNT) ELSE 0 END ASS,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0005', '008', '1105','03') THEN SUM(AMOUNT) ELSE 0 END AHN,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0003', '047', '1103','33') THEN SUM(AMOUNT) ELSE 0 END ALO,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0002', '027', '1102','08') THEN SUM(AMOUNT) ELSE 0 END AHD,")
                .append("            CASE WHEN APPGB='A' AND ACQ_CD IN ('VC0007', '029', '1107','07') THEN SUM(AMOUNT) ELSE 0 END ASI,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0006', '026', '1106','01') THEN SUM(AMOUNT) ELSE 0 END CBC,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0030', '018', '2211','11') THEN SUM(AMOUNT) ELSE 0 END CNH,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0001', '016', '1101','02') THEN SUM(AMOUNT) ELSE 0 END CKB,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0004', '031', '1104','06') THEN SUM(AMOUNT) ELSE 0 END CSS,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0005', '008', '1105','03') THEN SUM(AMOUNT) ELSE 0 END CHN,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0003', '047', '1103','33') THEN SUM(AMOUNT) ELSE 0 END CLO,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0002', '027', '1102','08') THEN SUM(AMOUNT) ELSE 0 END CHD,")
                .append("            CASE WHEN APPGB='C' AND ACQ_CD IN ('VC0007', '029', '1107','07') THEN SUM(AMOUNT) ELSE 0 END CSI")
                .append("        FROM (")
                .append("            SELECT")
                .append("                DEP_NM, TID, TERM_NM, APPGB, ACQ_CD, AMOUNT, APPDD")
                .append("            FROM")
                .append("                GLOB_MNG_ICVAN T1")
                .append("            LEFT OUTER JOIN (SELECT DEP_CD, TERM_NM, TERM_ID FROM TB_BAS_TIDMST WHERE ORG_CD=:orgcd) T3 ON (T1.TID=T3.TERM_ID)")
                .append("            LEFT OUTER JOIN (SELECT DEP_NM, DEP_CD FROM TB_BAS_DEPART WHERE ORG_CD=:orgcd) T4 ON (T3.DEP_CD=T4.DEP_CD)")
                .append("            LEFT OUTER JOIN (SELECT PUR_NM, PUR_OCD, PUR_KOCES FROM TB_BAS_PURINFO) T5 ON (T1.ACQ_CD=T5.PUR_OCD OR T1.ACQ_CD=T5.PUR_KOCES)")
                .append("            WHERE SVCGB IN ('CB') AND AUTHCD='0000'");

        // 동적쿼리 추가 (날짜, 사업부, 단말기)
                if (setWhere != null && !setWhere.isEmpty()) {
                    queryBuilder.append(setWhere);
                }

        queryBuilder.append("            ORDER BY APPDD DESC, apptm DESC")
                .append("        )")
                .append("        GROUP BY TID, APPGB, ACQ_CD, TERM_NM, DEP_NM, APPDD")
                .append("    )")
                .append("    GROUP BY DEP_NM, TID, TERM_NM, APPDD")
                .append(") T2")
                .append(" ORDER BY DEP_NM, APPDD, TID");


        Query query = entityManager.createNativeQuery(queryBuilder.toString(), Sub03Entity.class);
        query.setParameter("orgcd", orgcd);

        ArrayList<Sub03Entity> resultList = (ArrayList<Sub03Entity>) query.getResultList();
        return resultList;
    }

    @Override
    public List<Sub03Entity> findAll() {
        return null;
    }

    @Override
    public List<Sub03Entity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sub03Entity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sub03Entity> findAllById(Iterable<Long> longs) {
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
    public void delete(Sub03Entity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sub03Entity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sub03Entity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Sub03Entity> findById(Long aLong) {
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
    public <S extends Sub03Entity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Sub03Entity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sub03Entity getOne(Long aLong) {
        return null;
    }

    @Override
    public Sub03Entity getById(Long aLong) {
        return null;
    }

    @Override
    public Sub03Entity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sub03Entity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sub03Entity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sub03Entity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Sub03Entity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
