package cn.yinsh.hrm.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.yinsh.hrm.client.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CacheClient cacheClient;

    @GetMapping("/loadImageCode")
    public String imageCode(@RequestParam("uuid")String uuid){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        String imageBase64 = lineCaptcha.getImageBase64();
        String key="CODE:IMAGECODE"+uuid;
        cacheClient.setex(key,code,5000);
        return imageBase64;
    }

}
