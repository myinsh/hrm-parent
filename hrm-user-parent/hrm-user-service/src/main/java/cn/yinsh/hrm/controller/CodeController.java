package cn.yinsh.hrm.controller;

import client.SMSClient;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.RandomUtil;
import cn.yinsh.hrm.client.CacheClient;
import cn.yinsh.hrm.util.AjaxResult;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private SMSClient smsClient;

    @GetMapping("/loadImageCode")
    public String imageCode(@RequestParam("uuid")String uuid){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        String imageBase64 = lineCaptcha.getImageBase64();
        String key="CODE:IMAGECODE"+uuid;
        cacheClient.setex(key,code,300);
        return imageBase64;
    }


    @GetMapping("/sendSMSCode")
    public AjaxResult sendSMSCode(
            @RequestParam("uuid") String uuid,
            @RequestParam("imageCode")String imageCode,
            @RequestParam("telephone")String telephone){

        //一系列验证

        //图形验证码验证
        String key = "CODE:IMAGECODE:"+uuid;
        AjaxResult ajaxResult = cacheClient.get(key);
        if(!ajaxResult.isSuccess()){
            return AjaxResult.me().setSuccess(false).setMessage(ajaxResult.getMessage());
        }
        String imageCodeValue = (String) ajaxResult.getResultObj();
        if(StringUtils.isEmpty(imageCodeValue)){
            return AjaxResult.me().setSuccess(false).setMessage("图形验证码过期");
        }
        if(!imageCode.equals(imageCodeValue)){
            return AjaxResult.me().setSuccess(false).setMessage("图形验证码错误");
        }


        //获取短信验证码
        key = "CODE:REGCODE:"+telephone;
        ajaxResult = cacheClient.get(key);
        if(!ajaxResult.isSuccess()){
            return AjaxResult.me().setSuccess(false).setMessage(ajaxResult.getMessage());
        }
        String smsCodeValue = (String) ajaxResult.getResultObj();

        String smsCode = null;
        //判断是否存在
        if(StringUtils.isEmpty(smsCodeValue)){
            smsCode = RandomUtil.randomString("0123456789", 6);
        }else{
            String[] smsCodeValueArr = smsCodeValue.split(",");
            smsCode = smsCodeValueArr[0];
            long lastTime = Long.parseLong(smsCodeValueArr[1]);

            //判断是否重发
            long currentTime = System.currentTimeMillis();
            if(currentTime-lastTime<30000){
                return AjaxResult.me().setSuccess(false).setMessage("你爪子!");
            }
        }


        //重新保存redis
        String value = smsCode+","+System.currentTimeMillis();
        ajaxResult = cacheClient.setex(key, value,300);
        if(!ajaxResult.isSuccess()){
            return AjaxResult.me().setSuccess(false).setMessage(ajaxResult.getMessage());
        }

        //发送短信
        return smsClient.sendRegCode(telephone, smsCode);

    }

}
