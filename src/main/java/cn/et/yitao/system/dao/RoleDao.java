package cn.et.yitao.system.dao;

import cn.et.yitao.user.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 角色类的dao层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 2:14
 */
@Repository
public interface RoleDao {

    /**
     * 功能描述 添加角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param role
     * @return void
     */
    void addRole(Role role);

    /**
     * 功能描述 查询所有的角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param
     * @return java.util.List<cn.et.yitao.user.bean.Role>
     */
    List<Role> findAllRole();

    /**
     * 功能描述 修改角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param role 角色对象
     * @return void
     */
    void editRole(Role role);

    /**
     * 功能描述 根据角色id删除角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param id 角色id
     * @return void
     */
    void removeRoleById(Integer id);

    /**
     * 功能描述 角色是否禁用
     * @author pyj
     * @date 2018/10/16 0016
     * @param role
     * @return void
     */
    void editRoleByBun(Role role);

    /**
     * 功能描述 通用精确查询(条件)
     * @author pyj
     * @date 2018/10/16 0016
     * @param  role 角色对象
     * @return java.util.List<cn.et.yitao.user.bean.Role>
     */
    List<Role> findRoleByCondition(Role role);

    /**
     * 功能描述 根据角色id查询角色
     * @author pyj
     * @date 2018/10/17 0017
     * @param id 角色id
     * @return cn.et.yitao.user.bean.Role
     */
    Role findRoleById(Integer id);
}
