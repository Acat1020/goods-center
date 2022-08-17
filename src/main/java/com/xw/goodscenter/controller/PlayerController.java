package com.xw.goodscenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xw.goodscenter.mapper.PlayersMapper;
import com.xw.goodscenter.model.domain.Players;
import com.xw.goodscenter.model.domain.User;
import com.xw.goodscenter.model.domain.request.PlayerSearchRequest;
import com.xw.goodscenter.service.PlayersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.xw.goodscenter.constant.UserConstant.ADMIN_ROLE;
import static com.xw.goodscenter.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Resource
    private PlayersService playersService;

    @Resource
    private PlayersMapper playersMapper;

    /**
     * 查找运动员信息
     *
     * @param playerName 运动员姓名
     * @param request request
     * @return  List
     */
    @GetMapping("/searchPlayer")
    public List<PlayerSearchRequest> searchPlayer(String playerName, HttpServletRequest request){
        if (!isAdmin(request)){
            return null;
        }
        return playersService.selectPlayer();
    }

    /**
     * 删除运动员
     *
     * @param id 运动员id
     */
    @DeleteMapping("/removePlayer")
    public void removePlayer(@RequestBody long id){
        if (id <0){
            return;
        }
        playersService.removeById(id);
    }

    /**
     * 修改运动员信息
     *
     * @param player 原始运动员信息
     */
    @PostMapping("/updatePlayer")
    public void updatePlayer(@RequestBody PlayerSearchRequest player){
        if (player == null){
            return;
        }
        Players cleanPlayer = getCleanPlayer(player);
        playersService.saveOrUpdate(cleanPlayer);
    }

    /**
     * 创建新的运动员
     *
     * @param player 运动员信息
     * 之所以update和create分开写是因为player中又typeId，需要分情况插入数据
     * 为了方便分开写
     */
    @PostMapping("/createPlayer")
    public void createPlayer(@RequestBody PlayerSearchRequest player){
        if (player == null){
            return;
        }
        Players cleanPlayer = getCleanPlayer(player);
        playersService.saveOrUpdate(cleanPlayer);
    }

    /**
     * 判断用户是否为管理员
     *
     * @param request request
     * @return boolean
     */
    public boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getRole() == ADMIN_ROLE;
    }

    /**
     * 信息脱敏
     *
     * @param player 原信息
     * @return 脱敏后的信息
     */
    public Players getCleanPlayer(PlayerSearchRequest player){
        Players cleanPlayer = new Players();
        cleanPlayer.setId(player.getId());
        cleanPlayer.setPlayerName(player.getPlayerName());
        cleanPlayer.setPlayerAge(player.getPlayerAge());
        cleanPlayer.setPlayerContent(player.getPlayerContent());
        cleanPlayer.setGender(player.getGender());
        cleanPlayer.setPhone(player.getPhone());
        cleanPlayer.setSport(player.getSport());
        if ("田径".equals(player.getTypeName())){
            cleanPlayer.setTypeId(1);
        }else if ("游泳".equals(player.getTypeName())){
            cleanPlayer.setTypeId(2);
        }else{
            cleanPlayer.setTypeId(3);
        }
        cleanPlayer.setUpdateTime(player.getUpdateTime());
        return cleanPlayer;
    }
}
