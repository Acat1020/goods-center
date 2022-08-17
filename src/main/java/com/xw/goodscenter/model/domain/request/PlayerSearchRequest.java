package com.xw.goodscenter.model.domain.request;



import lombok.Data;

import java.util.Date;

@Data
public class PlayerSearchRequest {

    private Integer id;


    private String playerName;


    private Integer playerAge;


    private String playerContent;


    private String gender;


    private String phone;


    private String sport;


    private String typeName;


    private Date updateTime;

}
