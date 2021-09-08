package com.krysin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krysin.server.pojo.Admin;
import com.krysin.server.pojo.Menu;
import com.krysin.server.pojo.RespBean;
import com.krysin.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface IAdminService extends IService<Admin> {
    //登录之后返回token
    RespBean login(String username, String password, String code, HttpServletRequest request);

    //根据用户名获取用户
    Admin getAdminByUserName(String username);

    //根据用户id查询角色列表
    List<Role> getRoles (Integer adminId);

    //获取所有操作员
    List<Admin> getAllAdmins(String keywords);

    //更新操作员角色
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    //更新用户密码
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
