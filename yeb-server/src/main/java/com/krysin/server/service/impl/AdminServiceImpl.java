package com.krysin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krysin.server.AdminUtils;
import com.krysin.server.config.security.component.JwtTokenUtil;
import com.krysin.server.mapper.AdminMapper;
import com.krysin.server.mapper.AdminRoleMapper;
import com.krysin.server.mapper.RoleMapper;
import com.krysin.server.pojo.Admin;
import com.krysin.server.pojo.AdminRole;
import com.krysin.server.pojo.RespBean;
import com.krysin.server.pojo.Role;
import com.krysin.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    //登录之后返回token
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        //验证码校验
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码填写错误！");
        }

        //登录
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(null==userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return  RespBean.error("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账户被禁用，请联系管理员");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    //根据用户名获取用户
    @Override
    public Admin getAdminByUserName(String username) {
        return  adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    //根据用户id查询角色列表
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    //获取所有操作员
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(),keywords);
    }

    //更新操作员角色
    @Override
    @Transactional //事务
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId,rids);
        if(rids.length==result){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //更新用户密码
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = baseMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //比较密码是否正确
        if (encoder.matches(oldPass, admin.getPassword())) {
            //设置密码
            admin.setPassword(encoder.encode(pass));
            int result = baseMapper.updateById(admin);
            if (1 == result) {
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }
}
