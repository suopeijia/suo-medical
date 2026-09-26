package com.suo.medical.controller;

import com.suo.medical.common.response.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis-test")
@RequiredArgsConstructor
/**
 * Redis测试接口
 */
@Tag(name = "Redis测试接口")
public class RedisTestController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public Result<String> set(@RequestParam String key, @RequestParam String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return Result.success("缓存成功!");
    }

    @GetMapping("/get")
    public Result<String> get(@RequestParam String key) {
        return Result.success((stringRedisTemplate.opsForValue().get(key)));
    }
}
