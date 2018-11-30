package cn.et.yitao.system.service;

import cn.et.yitao.user.bean.Role;

import java.util.List;

/**
 * @描述: 角色类service层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 4:03
 */
public interface RoleService {

    /**
     * 功能描述 添加角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param role
     * @param perms 权限id组
     * @return void
     */
    void insertRole(Role role,String perms);

    /**
     * 功能描述 查询所有的角色(未删除的)
     * @author pyj
     * @date 2018/10/16 0016
     * @param
     * @return java.util.List<cn.et.yitao.user.bean.Role>
     */
    List<Role> selectAllRole();

    /**
     * 功能描述 修改角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param role 角色对象
     * @param perms 权限组
     * @return void
     */
    void updateRole(Role role,String perms);

    /**
     * 功能描述 根据角色id删除角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param id 角色id
     * @return void
     */
    void deleteRoleById(Integer id);

    /**
     * 功能描述 角色是否禁用
     * @author pyj
     * @date 2018/10/16 0016
     * @param role
     * @return void
     */
    void updateRoleByBun(Role role);

    /**
     * 功能描述 通用精确查询(条件)
     * @author pyj
     * @date 2018/10/16 0016
     * @param  role 角色对象
     * @return java.util.List<cn.et.yitao.user.bean.Role>
     */
    List<Role> selectRoleByCondition(Role role);

    /**
     * 功能描述 根据角色id查询角色
     * @author pyj
     * @date 2018/10/17 0017
     * @param id 角色id
     * @return java.lang.String json字符串
     */
    String selectRoleById(Integer id);
}
