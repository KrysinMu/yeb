package com.krysin.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krysin.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author krysin
 * @since 2021-09-04
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    //获取所有部门
    List<Department> getAllDepartments(Integer parentId);

    //添加部门
    void addDep(Department dep);

    //删除部门
    void deleteDep(Department dep);
}
