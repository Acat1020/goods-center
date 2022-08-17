package com.xw.goodscenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 运动类型表
 * @TableName sporttype
 */
@TableName(value ="sporttype")
@Data
public class Sporttype implements Serializable {
    /**
     * 运动类型id 唯一标识
     */
    @TableId(value = "typeId")
    private Integer typeId;

    /**
     * 运动类型
     */
    @TableField(value = "typeName")
    private String typeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}