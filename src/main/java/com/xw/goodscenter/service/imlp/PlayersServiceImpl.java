package com.xw.goodscenter.service.imlp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.goodscenter.model.domain.Players;
import com.xw.goodscenter.model.domain.request.PlayerSearchRequest;
import com.xw.goodscenter.service.PlayersService;
import com.xw.goodscenter.mapper.PlayersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 000000
* @description 针对表【players(运动员信息表)】的数据库操作Service实现
* @createDate 2022-06-10 08:54:09
*/
@Service
public class PlayersServiceImpl extends ServiceImpl<PlayersMapper, Players>
    implements PlayersService{

    @Resource
    private PlayersMapper playersMapper;

    @Override
    public List<PlayerSearchRequest> selectPlayer() {
        return playersMapper.selectPlayer();
    }
}




