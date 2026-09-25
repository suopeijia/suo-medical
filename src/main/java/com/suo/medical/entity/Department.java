package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 科室
 */
@Data
public class Department {

    /**
     * Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 科室名称
     */
    private String name;
}
