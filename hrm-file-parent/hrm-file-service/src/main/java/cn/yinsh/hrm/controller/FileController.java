package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //获取文件名中最后一个 ‘点’ 的索引
            int index = fileName.lastIndexOf(".");
            //从文件名中的 ‘点’ 的后一位开始截取，获取到文件后缀名
            String exName = fileName.substring(index + 1);
            //文件内容
            byte[] bytes = file.getBytes();
            //上传成功后，服务器返回文件的id
            String file_id = FastDfsApiOpr.upload(bytes, exName);
            return AjaxResult.me().setSuccess(true).setMessage("上传成功").setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败");
        }

    }


}
