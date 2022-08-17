package com.xw.goodscenter.model.domain.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author ACat
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -2682070453302618714L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
