package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class CacheController {
    //设置值
    @GetMapping("/setStr")
    public AjaxResult set(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            RedisUtils.INSTANCE.set(key, value);
            return AjaxResult.me().setSuccess(true).setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败" + e.getMessage());
        }
    }

    //获取值
    @GetMapping("/getStr")
    public AjaxResult get(@RequestParam("key") String key) {
        try {
            String value = RedisUtils.INSTANCE.get(key);
            return AjaxResult.me().setSuccess(true).setMessage("获取成功").setResultObj(value);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取失败");
        }
    }

    //设置过期时间
    @GetMapping("/setex")
    public AjaxResult setex(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            jedis.setex(key, seconds, value);
            return AjaxResult.me().setSuccess(true).setMessage("设置成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置失败");
        }
    }

    @GetMapping("/setnx")
    public AjaxResult setnx(@RequestParam("key") String key,@RequestParam("value") String value) {

        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            Long result = jedis.setnx(key, value);
            return AjaxResult.me().setSuccess(true).setMessage("设置成功").setResultObj(result);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("设置失败");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    @GetMapping("/deleteKey")
    public AjaxResult deleteKey(@RequestParam("key") String key) {
        Jedis jedis = null;
        try {
            jedis = RedisUtils.INSTANCE.getSource();
            jedis.del(key);
            return AjaxResult.me().setSuccess(true).setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
