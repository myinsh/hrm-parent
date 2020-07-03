package client;

import client.impl.SMSClientImpl;
import cn.yinsh.hrm.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sms-service",fallback = SMSClientImpl.class)
public interface SMSClient {

    //发送短信验证码
    @GetMapping("/sendRegCode")
    AjaxResult sendRegCode(@RequestParam("phoneNum") String phoneNum, @RequestParam("code") String code);
}