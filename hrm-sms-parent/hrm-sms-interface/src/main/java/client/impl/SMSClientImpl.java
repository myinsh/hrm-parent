package client.impl;

import client.SMSClient;
import cn.yinsh.hrm.util.AjaxResult;
import org.springframework.stereotype.Component;

@Component
public class SMSClientImpl implements SMSClient {
    @Override
    public AjaxResult sendRegCode(String phoneNum, String code) {
        return AjaxResult.me().setSuccess(false).setMessage("系统异常");
    }
}
