package com.project.jingmaoquan.service.impl;

import com.project.jingmaoquan.mapper.UserInfoMapper;
import com.project.jingmaoquan.model.UserInfo;
import com.project.jingmaoquan.model.UserInfoExample;
import com.project.jingmaoquan.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int signUp(String username, String password, String phone) {
        List<UserInfo> userCheck;
        // 检查用户名是否存在
        UserInfoExample userInfoExampleForCheckUsername = new UserInfoExample();
        userInfoExampleForCheckUsername.createCriteria().andUsernameEqualTo(username);
        userCheck = userInfoMapper.selectByExample(userInfoExampleForCheckUsername);
        if (userCheck.size() > 0) {
            return 1;
        }
        // 检查手机号是否已存在
        UserInfoExample userInfoExampleForCheckPhone = new UserInfoExample();
        userInfoExampleForCheckPhone.createCriteria().andPhoneEqualTo(phone);
        userCheck = userInfoMapper.selectByExample(userInfoExampleForCheckPhone);
        if (userCheck.size() > 0) {
            return 2;
        }


        // 生成盐
        String salt = UUID.randomUUID().toString();
        // 计算 MD5
        byte[] bytes = (salt + password).getBytes();
        String PWDHash = DigestUtils.md5DigestAsHex(bytes);

        logger.info("slat:{},PWDHash:{}", salt, PWDHash);

        // 保存到数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(PWDHash);
        userInfo.setSalt(salt);
        userInfo.setPhone(phone);
        userInfo.setPhoto("/image/default_avatar.png");
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfoMapper.insert(userInfo);
        return 0;

    }

    @Override
    public UserInfo login(String username, String password) {
        UserInfoExample userInfoExample = new UserInfoExample();
        // 查询用户信息
        userInfoExample.createCriteria().andUsernameEqualTo(username);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        if (userInfos.size() == 0) { // 用户不存在
            return null;
        }

        UserInfo userInfo=userInfos.get(0);
        // 计算 MD5
        String salt = userInfo.getSalt();
        byte[] bytes = (salt + password).getBytes();
        String PWDHash = DigestUtils.md5DigestAsHex(bytes);
        // 验证密码
        if (PWDHash.equals(userInfo.getPassword())) {// 登录成功
            // 如果 token 为空
            if (StringUtils.isEmpty(userInfo.getToken())) {
                String token = UUID.randomUUID().toString();
                userInfo.setToken(token);
            }
            userInfoMapper.updateByExample(userInfo,userInfoExample);
            return userInfo;
        }else { // 密码不匹配
            return null;
        }
    }
}
