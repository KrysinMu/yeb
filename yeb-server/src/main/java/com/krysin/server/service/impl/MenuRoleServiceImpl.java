package com.krysin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krysin.server.mapper.MenuMapper;
import com.krysin.server.mapper.MenuRoleMapper;
import com.krysin.server.pojo.MenuRole;
import com.krysin.server.pojo.RespBean;
import com.krysin.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    //更新角色菜单
    @Override
    @Transactional //事务注解
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(null==mids || 0==mids.length){
            return RespBean.success("更新成功!");
        }
        menuRoleMapper.insertRecord(rid,mids);
        return null;
    }
}
