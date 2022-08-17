package com.xw.goodscenter.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.goodscenter.mapper.UserMapper;
import com.xw.goodscenter.model.domain.User;
import com.xw.goodscenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xw.goodscenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 000000
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-06-01 17:57:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    private static final String SALT = "acat";

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param userAccount  用户账号
     * @param userPassword  用户密码
     * @param checkPassword  确认密码
     * @return  新用户ID
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1. 检验
        //账号密码不为空
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        //账号长度不小于4
        if (userAccount.length()<4){
            return -1;
        }
        //密码长度不小于6
        if (userPassword.length()<6 || checkPassword.length()<6){
            return -1;
        }
        //检查账号密码是否相同
        if (!userPassword.equals(checkPassword)){
            return -1;
        }
        //检查账号不能出现特殊字符
        String validPattern = "[\\\\u00A0\\\\s\\\"`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }
        //检查账户不能相同
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count != 0){
            return -1;
        }
        //2. 对密码进行加密
        String newPassword = DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes(StandardCharsets.UTF_8));
        //3. 向数据库插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(newPassword);
        boolean result = this.save(user);
        if (!result){
            return -1;
        }
        return user.getId();

    }


    /**
     * 用户登录
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return  User 脱敏后的用户信息
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1. 检验
        //账号密码不为空
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        //账号长度不小于4  密码长度不小于6
        if (userAccount.length()<4 || userPassword.length()<6){
            return null;
        }
        //查账号不能出现特殊字符
        String validPattern = "[\\\\u00A0\\\\s\\\"`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        //2. 对密码进行加密
        String newPassword = DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes(StandardCharsets.UTF_8));
        //在数据库中查找用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",newPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            return null;
        }
        //3. 用户脱敏
        User cleanUser = getSafetyUser(user);
        //4. 记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,cleanUser);
        return cleanUser;
    }


    /**
     * 用户脱敏
     *
     * @param user 用户信息
     * @return 脱敏后的用户信息
     */
    public User getSafetyUser(User user){

        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setRole(user.getRole());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        return safetyUser;

    }


    @Override
    public int userLogout(HttpServletRequest request){
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




