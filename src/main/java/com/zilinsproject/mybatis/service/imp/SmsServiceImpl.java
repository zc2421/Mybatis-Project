package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.service.SmsService;
import com.zilinsproject.mybatis.utils.LoginUtils;
import org.springframework.stereotype.Service;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;


@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public void SendSmsLoginCode(String phoneNumber, Integer userId) {

        String code = LoginUtils.generateCode();
        //send code through Tencent Cloud SMS
        try{

            Credential cred = new Credential("AKID5uSmpbpHCy5k8EPbsPghRu7EenmZkSYt", "93nOimZ4IELBk5B1ZqsJWVHNr57wLTLS");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.na-ashburn.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "na-ashburn", clientProfile);

            String params = "{\"PhoneNumberSet\":[" + phoneNumber + "],\"TemplateID\":\"626864\",\"TemplateParamSet\":[" + code + "],\"SmsSdkAppid\":\"1400381989\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }
}
