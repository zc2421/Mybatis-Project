package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.UserInfoMapperExtended;
import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.SmsService;
import com.zilinsproject.mybatis.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;


@Service
public class SmsServiceImpl implements SmsService {

    private final static String CODE_REDIS_KEY_TEMPLATE= "code_%d";
    private final static String SUCCESS = "Ok";

    @Autowired
    private UserInfoMapperExtended userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void SendSmsLoginCode(String username) {
        //查看用户是否存在
        UserInfo user = userService.selectByUsername(username);
        if (username == null){
            throw new CustomizeException(ResultEnum.USER_NOT_EXIST);
        }
        //获取用户注册手机号
//        String phoneNumber = user.getPhone();
        String phoneNumber = "+19175998345";
        String code = LoginUtils.generateCode();

        //发送消息
        try{

            Credential cred = new Credential("AKIDaJm0xCsJflCKEnpC2PVLxL0PGtPCYmeh", "wc3wc2PFJ8jAOSYlNgjWBbmQnwuc3GVU");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.na-ashburn.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "na-ashburn", clientProfile);

            String params = "{\"PhoneNumberSet\":[" + phoneNumber + "],\"TemplateID\":\"626864\",\"TemplateParamSet\":[" + code + "],\"SmsSdkAppid\":\"1400381989\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            //写入redis
            if (resp.getSendStatusSet()[0].getCode().equals(SUCCESS)){
                String redisKey = String.format(CODE_REDIS_KEY_TEMPLATE, user.getUser_id());
                ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                valueOperations.set(redisKey, code, Duration.ofMinutes(5));
            }else{
                throw new CustomizeException(ResultEnum.TEMPORAL_CODE_FAILED);
            }

        } catch (TencentCloudSDKException e) {
            throw new CustomizeException(ResultEnum.CLOUT_SERVER_STATUS_ERROR);
        }

    }


    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public UserInfo loginViaCode(String username, String code) {
        UserInfo userInfo = userService.selectByUsername(username);
        if (userInfo == null){
            throw new CustomizeException(ResultEnum.USER_NOT_EXIST);
        }
        String redisKey = String.format(CODE_REDIS_KEY_TEMPLATE, userInfo.getUser_id());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String valid = valueOperations.get(redisKey);
        //验证码无效
        if (valid == null){
            throw new CustomizeException(ResultEnum.CODE_STATUS_ERROR);
        }

        if (valid.equals(code)){
            //成功登陆删除验证码
            redisTemplate.delete(redisKey);
            return userInfo;
        } else {
            //验证码错误
            throw new CustomizeException(ResultEnum.CODE_INCORRECT_ERROR);
        }

    }
}
