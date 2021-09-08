package com.krysin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krysin.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface IMenuService extends IService<Menu> {
    //通过用户id获取菜单列表
    List<Menu> getMenusByAdminId();

    //根据角色获取菜单列表
    List<Menu> getMenusWithRole();

    //查询所有菜单
    List<Menu> getAllMenus();
}
