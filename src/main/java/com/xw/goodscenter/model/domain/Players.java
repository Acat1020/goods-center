package com.xw.goodscenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 运动员信息表
 * @TableName players
 */
@TableName(value ="players")
@Data
public class Players implements Serializable {
    /**
     * 运动员id  唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 运动员姓名
     */
    @TableField(value = "playerName")
    private String playerName;

    /**
     * 用户年龄
     */
    @TableField(value = "playerAge")
    private Integer playerAge;

    /**
     * 运动员介绍
     */
    @TableField(value = "playerContent")
    private String playerContent;

    /**
     * 运动员性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 运动员联系方式 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 参加运动名称
     */
    @TableField(value = "sport")
    private String sport;

    /**
     * 运动类型
     */
    @TableField(value = "typeId")
    private Integer typeId;

    /**
     * 运动员信息修改时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}