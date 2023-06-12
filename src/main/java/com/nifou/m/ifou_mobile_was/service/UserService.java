package com.nifou.m.ifou_mobile_was.service;


import com.nifou.m.ifou_mobile_was.entity.user.UserEntity;
import com.nifou.m.ifou_mobile_was.entity.user.UserUauthEntity;
import com.nifou.m.ifou_mobile_was.repository.user.UserRepository;
import com.nifou.m.ifou_mobile_was.repository.user.UserUauthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUauthRepository userUauthRepository;

    public UserEntity getUser(String userId,String userPw) {
        UserEntity uInfo = new UserEntity();
        uInfo = userRepository.getUser(userId, userPw);
        return uInfo;
    }

    public UserUauthEntity getUserUauth(String userId) {
        UserUauthEntity uUauth = new UserUauthEntity();
        uUauth = userUauthRepository.getUserUauth(userId);
        return uUauth;
    }
}
