package com.suo.medical.controller;

import com.suo.medical.common.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis 测试控制器，提供简单的字符串缓存读写接口，用于验证 Redis 连通性。
 *
 * @author suo
 */
@RestController
@RequestMapping("/redis-test")
@RequiredArgsConstructor
@Tag(name = "Redis测试接口", description = "Redis连通性测试")
public class RedisTestController {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 写入缓存。
     *
     * @param key   缓存 key
     * @param value 缓存值
     * @return 操作结果提示
     */
    @Operation(summary = "写入缓存")
    @GetMapping("/set")
    public Result<String> set(@RequestParam String key, @RequestParam String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return Result.success("缓存成功!");
    }

    /**
     * 读取缓存。
     *
     * @param key 缓存 key
     * @return 缓存值
     */
    @Operation(summary = "读取缓存")
    @GetMapping("/get")
    public Result<String> get(@RequestParam String key) {
        return Result.success((stringRedisTemplate.opsForValue().get(key)));
    }
}
