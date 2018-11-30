package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 角色和权限的关系表
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 8:47
 */
@Repository
public interface RolePermissionDao {

    /**
     * 功能描述 添加角色权限
     * @author pyj
     * @date 2018/10/16 0016
     * @param rolePermission
     * @return void
     */
    void addRolePerm(RolePermission rolePermission);

    /**
     * 功能描述 根据角色id查询所有权限
     * @author pyj
     * @date 2018/10/17 0017
     * @param eid 角色ID
     * @return java.util.List<cn.et.yitao.user.bean.RolePermission>
     */
    List<RolePermission> findRolePermByEid(Integer eid);

    /**
     * 功能描述 根据角色id删除权限
     * @author pyj
     * @date 2018/10/17 0017
     * @param eid 角色id
     * @return void
     */
    void removePerm(Integer eid);
}
