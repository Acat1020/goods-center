package com.xw.goodscenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id  唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @TableField(value = "userAccount")
    private String userAccount;

    /**
     * 用户密码
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户头像URL
     */
    @TableField(value = "avatarUrl")
    private String avatarUrl;

    /**
     * 用户性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 用户电话号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户角色 0普通用户  1管理员
     */
    @TableField(value = "role")
    private Integer role;

    /**
     * 逻辑删除  0代表正常  1代表此用户已被删除
     */
    @TableField(value = "isDelete")
    @TableLogic
    private Integer isDelete;

    /**
     * 用户状态 0正常 1封号
     */
    @TableField(value = "userStatus")
    private Integer userStatus;

    /**
     * 用户注册时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}