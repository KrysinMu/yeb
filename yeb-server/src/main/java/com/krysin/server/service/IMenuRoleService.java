package com.krysin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krysin.server.pojo.MenuRole;
import com.krysin.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface IMenuRoleService extends IService<MenuRole> {

    //更新角色菜单
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
