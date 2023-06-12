package com.nifou.m.ifou_mobile_was.repository.user;

import com.nifou.m.ifou_mobile_was.entity.user.UserUauthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserUauthRepository extends JpaRepository<UserUauthEntity, String> {


    @Query(value ="    SELECT" +
            "            t2.USER_ID      AS userid," +
            "            t2.ORG_CD       AS orgcd," +
            "            t2.DEP_CD       AS depcd," +
            "            t1.ORG_NO       AS orgno," +
            "            t1.PTAB         AS ptab," +
            "            t1.VTAB         AS vtab," +
            "            t1.DTAB         AS dtab," +
            "            t2.USER_LV      AS userlv," +
            "            t2.TRANS_NO     AS transno," +
            "            t2.AUTH_SEQ     AS authseq," +
            "            t2.USER_NM      AS usernm" +
            "          FROM " +
            "              TB_BAS_ORG t1 " +
            "            INNER JOIN TB_BAS_USER t2 " +
            "            ON" +
            "                    (t1.ORG_CD=t2.ORG_CD) " +
            "          where " +
            "            t2.USER_ID = :userid "
            , nativeQuery = true)
    public UserUauthEntity getUserUauth(@Param("userid") String userId);
}
