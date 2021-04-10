package com.mp.mprbac.service;

import com.mp.mprbac.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.mprbac.vo.ResourceVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据角色id查询该角色所具有的资源
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

}
