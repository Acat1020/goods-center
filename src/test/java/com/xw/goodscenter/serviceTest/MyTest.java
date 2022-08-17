package com.xw.goodscenter.serviceTest;

import com.xw.goodscenter.mapper.PlayersMapper;
import com.xw.goodscenter.model.domain.request.PlayerSearchRequest;
import com.xw.goodscenter.service.PlayersService;
import com.xw.goodscenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Scanner;


@SpringBootTest
public class MyTest {

    @Resource
    private UserService userService;

    @Resource
    private PlayersService playersService;

    @Test
    void test01(){
        String userAccount = "acat";
        String userPassword = "123456";
        String checkPassword = "123456";
        long l = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(l);
    }

    @Test
    void test00(){
        List<PlayerSearchRequest> playerList = playersService.selectPlayer();
        for (PlayerSearchRequest player : playerList){
            System.out.println(player);
        }
    }

}
