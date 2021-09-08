package com.krysin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krysin.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //通过用户id获取菜单列表
    List<Menu> getMenusByAdminId(Integer id);

    //根据角色获取菜单列表
    List<Menu> getMenusWithRole();

    //查询所有菜单
    List<Menu> getAllMenus();
}
