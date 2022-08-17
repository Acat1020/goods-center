package com.xw.goodscenter.service;

import com.xw.goodscenter.model.domain.Players;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.goodscenter.model.domain.request.PlayerSearchRequest;

import java.util.List;

/**
* @author 000000
* @description 针对表【players(运动员信息表)】的数据库操作Service
* @createDate 2022-06-10 08:54:09
*/
public interface PlayersService extends IService<Players> {

    List<PlayerSearchRequest> selectPlayer();
}
