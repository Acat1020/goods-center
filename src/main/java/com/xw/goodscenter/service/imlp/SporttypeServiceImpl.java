package com.xw.goodscenter.service.imlp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.goodscenter.model.domain.Sporttype;
import com.xw.goodscenter.service.SporttypeService;
import com.xw.goodscenter.mapper.SporttypeMapper;
import org.springframework.stereotype.Service;

/**
* @author 000000
* @description 针对表【sporttype(运动类型表)】的数据库操作Service实现
* @createDate 2022-06-10 08:57:36
*/
@Service
public class SporttypeServiceImpl extends ServiceImpl<SporttypeMapper, Sporttype>
    implements SporttypeService{

}




