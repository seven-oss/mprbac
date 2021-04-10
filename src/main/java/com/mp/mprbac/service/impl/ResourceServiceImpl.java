package com.mp.mprbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.mprbac.entity.Resource;
import com.mp.mprbac.mapper.ResourceMapper;
import com.mp.mprbac.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.mprbac.vo.ResourceVO;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> supWrapper = Wrappers.<Resource>query();
        supWrapper.eq("rr.role_id", roleId);
        List<ResourceVO> resourceVOS = baseMapper.listResource(supWrapper);
        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper =  Wrappers.<Resource>query();
            subWrapper.eq("rr.role_id", roleId).eq("re.parent_id", resourceId);
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);
            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }
}
