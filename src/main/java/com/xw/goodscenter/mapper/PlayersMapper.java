package com.xw.goodscenter.mapper;

import com.xw.goodscenter.model.domain.Players;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.goodscenter.model.domain.request.PlayerSearchRequest;

import java.util.List;

/**
* @author 000000
* @description 针对表【players(运动员信息表)】的数据库操作Mapper
* @createDate 2022-06-10 08:54:09
* @Entity com.xw.goodscenter.model.domain.Players
*/
public interface PlayersMapper extends BaseMapper<Players> {

    List<PlayerSearchRequest> selectPlayer();
}




