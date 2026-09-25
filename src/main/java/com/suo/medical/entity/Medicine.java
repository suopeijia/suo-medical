package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Medicine {
    /**
     * 药品id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 药品名称
     */
    private String name;

    /**
     * 药品价格
     */
    private Double price;

    /**
     * 药品库存
     */
    private Integer stock;
}
