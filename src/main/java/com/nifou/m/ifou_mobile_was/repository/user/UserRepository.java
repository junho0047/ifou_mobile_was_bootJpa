package com.nifou.m.ifou_mobile_was.repository.user;


import com.nifou.m.ifou_mobile_was.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM TB_BAS_USER WHERE USER_ID = :userid AND USER_PW = :userpw", nativeQuery = true)
    public UserEntity getUser(@Param("userid") String userId, @Param("userpw") String userPw);



}
