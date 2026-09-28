package com.suo.medical.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 药品实体，对应数据库药品表。
 *
 * @author suo
 */
@Data
@Schema(description = "药品实体")
public class Medicine {
    /**
     * 药品主键 id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "药品主键id")
    private Long id;

    /**
     * 药品名称
     */
    @Schema(description = "药品名称")
    private String name;

    /**
     * 药品价格
     */
    @Schema(description = "药品价格")
    private Double price;

    /**
     * 药品库存
     */
    @Schema(description = "药品库存")
    private Integer stock;
}
