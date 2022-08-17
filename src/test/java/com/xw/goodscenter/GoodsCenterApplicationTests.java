package com.xw.goodscenter;

import com.xw.goodscenter.mapper.UserMapper;
import com.xw.goodscenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class GoodsCenterApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }


    @Test
    void selectTest(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }

}
