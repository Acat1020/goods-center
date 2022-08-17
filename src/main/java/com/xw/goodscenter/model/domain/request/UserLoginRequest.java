package com.xw.goodscenter.model.domain.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author ACat
 */

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -6473763927454542512L;

    private String userAccount;

    private String userPassword;

}
