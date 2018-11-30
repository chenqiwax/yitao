package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.RolePermission;

/**
 * @描述: 角色权限service层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 8:57
 */
public interface RolePermissionService {

    /**
     * 功能描述 添加角色的权限
     * @author pyj
     * @date 2018/10/16 0016
     * @param eid 角色id
     * @param sid 权限id
     * @return void
     */
    void insertRolePerm(Integer eid,Integer sid);
}
