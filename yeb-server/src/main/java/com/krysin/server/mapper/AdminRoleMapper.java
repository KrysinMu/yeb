package com.krysin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krysin.server.pojo.AdminRole;
import com.krysin.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    //更新操作员角色
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
