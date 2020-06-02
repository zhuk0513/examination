package com.zhuk.examination.service.impl;

import com.zhuk.examination.model.entity.Test;
import com.zhuk.examination.dao.TestMapper;
import com.zhuk.examination.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuk
 * @since 2020-05-26
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
