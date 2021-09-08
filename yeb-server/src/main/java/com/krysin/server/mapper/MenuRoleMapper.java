package com.krysin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krysin.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    //更新角色菜单
    void insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
