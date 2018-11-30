package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.RolePermission;
import cn.et.yitao.user.dao.RolePermissionDao;
import cn.et.yitao.user.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述: 角色权限service的实现层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 8:57
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Override
    public void insertRolePerm(Integer eid,Integer sid) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setEid(eid);
        rolePermission.setSid(sid);
        rolePermissionDao.addRolePerm(rolePermission);
    }
}
