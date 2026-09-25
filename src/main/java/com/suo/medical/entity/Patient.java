package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Patient {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
}
