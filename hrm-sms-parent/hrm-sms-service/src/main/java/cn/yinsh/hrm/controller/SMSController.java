package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.SMSUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SMSController {

    @GetMapping("/sendRegCode")
    public AjaxResult sendRegCode(@RequestParam("phoneNum") String phoneNum, @RequestParam("code")String code){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("code",code);
        String param = JSONObject.toJSONString(paramMap);
        try {
            SMSUtil.INSTANCE.sendSMS(phoneNum,"hrm人力资源平台","SMS_187756817",param);
            return AjaxResult.me().setSuccess(true).setMessage("短信发送成功");
        } catch (ClientException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("短信发送失败");
        }
    }
}
