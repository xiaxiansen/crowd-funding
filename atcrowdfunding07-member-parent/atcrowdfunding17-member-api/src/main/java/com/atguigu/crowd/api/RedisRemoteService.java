package com.atguigu.crowd.api;

import com.atguigu.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @program: Crowd-funding -- com.atguigu.crowd.api
 * @description: TODO
 * @author: xia liang
 * @create: 2020-08-31 12:15
 */
@FeignClient("atguigu-crowd-redis")
public interface RedisRemoteService {
    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);
    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeOut(@RequestParam("key") String key, @RequestParam("value") String value,
                                                           @RequestParam("time")Long time, @RequestParam("timeUnit")TimeUnit timeUnit);
    @RequestMapping("get/redis/string/value/by/key")
    ResultEntity<String> getRedisStringValueByKeyRemote(@RequestParam("key") String key);
    @RequestMapping("remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key);
}
