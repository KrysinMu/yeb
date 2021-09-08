package com.krysin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krysin.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface RoleMapper extends BaseMapper<Role> {

    //根据用户id查询角色列表
    List<Role> getRoles(Integer adminId);
}
