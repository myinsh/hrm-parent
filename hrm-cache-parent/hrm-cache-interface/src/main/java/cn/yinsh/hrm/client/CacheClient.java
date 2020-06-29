package cn.yinsh.hrm.client;

import cn.yinsh.hrm.client.impl.CacheClientImpl;
import cn.yinsh.hrm.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "cache-service",fallback = CacheClientImpl.class)
public interface CacheClient {
    @GetMapping("/setStr")
    AjaxResult set(@RequestParam("key") String key, @RequestParam("value") String value);

    @GetMapping("/getStr")
    AjaxResult get(@RequestParam("key")String key);

    @GetMapping("/setex")
    AjaxResult setex(@RequestParam("key") String key,@RequestParam("value") String value,@RequestParam("seconds")Integer seconds);


    @GetMapping("/setnx")
    AjaxResult setnx(@RequestParam("key") String key,@RequestParam("value") String value);

    @GetMapping("/deleteKey")
    AjaxResult deleteKey(@RequestParam("key") String key);
}