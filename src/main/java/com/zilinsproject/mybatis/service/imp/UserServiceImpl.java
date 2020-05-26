package com.zilinsproject.mybatis.service.imp;


import com.zilinsproject.mybatis.dao.UserInfoMapper;
import com.zilinsproject.mybatis.dao.UserInfoMapperExtended;
import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author zilinsmac
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapperExtended userMapper;

    @Override
    public void register(UserInfo userinfo) {
        String username = userinfo.getUsername();
        if (userMapper.countByUsername(username) > 0) {
            throw new CustomizeException(ResultEnum.USERNAME_ALREADY_EXIST);
        }

        String email = userinfo.getEmail();
        if (email != null && userMapper.countByEmail(email) > 0){
            throw new CustomizeException(ResultEnum.EMAIL_ALREADY_EXIST);
        }

        String encodedPassword = DigestUtils.md5DigestAsHex(
                userinfo.getPassword().getBytes(StandardCharsets.UTF_8));

        userinfo.setPassword(encodedPassword);

        int res = userMapper.insertAutoFill(userinfo);
        if (res == 0){
            throw new CustomizeException(ResultEnum.REGISTER_FAILED_ERROR);
        }
    }


    @Override
    public UserInfo login(String username, String password) {
        String encodedPassword = DigestUtils.md5DigestAsHex(
                password.getBytes(StandardCharsets.UTF_8));
        if (userMapper.countByUsername(username) == 0) {
            throw new CustomizeException(ResultEnum.LOGGING_INFO_NOT_CORRECT);
        }
        UserInfo userInfoCheck = userMapper.selectByUsername(username);
        if (!userInfoCheck.getPassword().equals(encodedPassword)){
            throw new CustomizeException(ResultEnum.LOGGING_INFO_NOT_CORRECT);
        }

        return userInfoCheck;
    }
}
