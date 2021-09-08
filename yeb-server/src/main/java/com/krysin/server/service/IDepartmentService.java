package com.krysin.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krysin.server.pojo.Department;
import com.krysin.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface IDepartmentService extends IService<Department> {
    //获取所有部门
    List<Department> getAllDepartments();

    //添加部门
    RespBean addDep(Department dep);

    //删除部门
    RespBean deleteDep(Integer id);
}
