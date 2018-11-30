package cn.et.yitao.system.service.impl;

import cn.et.yitao.system.dao.RoleDao;
import cn.et.yitao.system.service.RoleService;
import cn.et.yitao.user.bean.Role;
import cn.et.yitao.user.bean.RolePermission;
import cn.et.yitao.user.bean.UserPermission;
import cn.et.yitao.user.dao.RolePermissionDao;
import cn.et.yitao.util.RandomUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述: 角色类service层实现层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 4:06
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Override
    public void insertRole(Role role,String perms) {
        Integer eid = Integer.parseInt(RandomUtil.getRandom(3)); // 生成角色id
        role.setId(eid);

        String[] permss = perms.split(","); // 根据 ',' 分割权限id

        // 一个角色对应多个权限
        for (int i=0;i<permss.length;i++){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setEid(role.getId());
            rolePermission.setSid(Integer.parseInt(permss[i]));
            rolePermissionDao.addRolePerm(rolePermission);
        }
        roleDao.addRole(role);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public void updateRole(Role role ,String perms) {
        if(perms!=null && perms.length()!=0){ // 权限组是存在的
            rolePermissionDao.removePerm(role.getId());

            String[] permss = perms.split(","); // 根据 ',' 分割权限id

            // 一个角色对应多个权限
            for (int i=0;i<permss.length;i++){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setEid(role.getId());
                rolePermission.setSid(Integer.parseInt(permss[i]));
                rolePermissionDao.addRolePerm(rolePermission);
            }
        }
        roleDao.editRole(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleDao.removeRoleById(id);
    }

    @Override
    public void updateRoleByBun(Role role) {
        roleDao.editRoleByBun(role);
    }

    @Override
    public List<Role> selectRoleByCondition(Role role) {
        return roleDao.findRoleByCondition(role);
    }

    @Override
    public String selectRoleById(Integer id) {
        List<UserPermission> userPermissionList = new ArrayList<>();
        UserPermission userPermission = new UserPermission();
        userPermission.setId(1);
        userPermission.setName("qx1");
        UserPermission userPermission1 = new UserPermission();
        userPermission1.setId(2);
        userPermission1.setName("qx2");
        userPermissionList.add(userPermission);
        userPermissionList.add(userPermission1);
        Role role = roleDao.findRoleById(id);

        Gson gson = new Gson();
        String roleStr = gson.toJson(role); // 将角色对象转为字符串
        String permListStr = gson.toJson(userPermissionList); // 将该角色的perm列 转为字符窜
        return roleStr+"+"+permListStr;
    }
}
