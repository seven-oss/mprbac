package com.mp.mprbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mp.mprbac.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.mprbac.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询当前登录人的资源（自定义sql，这里采用xml的方式，也可以使用注解的方式）
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER)Wrapper<Resource> wrapper);

}
