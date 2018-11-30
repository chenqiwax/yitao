package cn.et.yitao.system.controller;

import cn.et.yitao.system.service.RoleService;
import cn.et.yitao.user.bean.Role;
import cn.et.yitao.user.bean.UserPermission;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述: 角色类的controller层
 * @Author: pyj
 * @DateTime: 2018/10/16 0016--下午 4:10
 */
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 功能描述 显示添加角色的界面
     * @author pyj
     * @date 2018/10/16 0016
     * @param modelMap
     * @return java.lang.String
     */
    @RequestMapping("/showAddRolePage.do")
    public String showAddRolePage(ModelMap modelMap){
        try {
            List<UserPermission> userPermissionList = new ArrayList<>();
            UserPermission userPermission = new UserPermission();
            userPermission.setId(1);
            userPermission.setName("qx1");
            UserPermission userPermission1 = new UserPermission();
            userPermission1.setId(2);
            userPermission1.setName("qx2");
            userPermissionList.add(userPermission);
            userPermissionList.add(userPermission1);
            modelMap.addAttribute("permList",userPermissionList);
            return "addRole";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    /**
     * 功能描述 添加角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param role
     * @return int 1: 添加成功  0:添加失败
     */
    @RequestMapping("/addRole.do")
    @ResponseBody
    public int addRole(Role role,String perms){

        if(role.getName()==null || role.getName().length() == 0){
            return 0;
        }
        if(role.getDesc()==null || role.getDesc().length() == 0){
            return 0;
        }
        if (perms.length()==0){
            return 0;
        }

        roleService.insertRole(role,perms);
        return 1;
    }

    /**
     * 功能描述 查看所有的角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/showAllRole.do")
    public String showAllRole(ModelMap modelMap){
        try {
            List<Role> roleList = roleService.selectAllRole();
            if(roleList!=null && roleList.size()!=0){
                modelMap.addAttribute("roleList",roleList);
                return "showRoles";
            }
            return "redirect:/error.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    /**
     * 功能描述 数据回显
     * @author pyj
     * @date 2018/10/16 0016
     * @param id 角色id
     * @return byte[]
     */
    @RequestMapping("/loadRole.do")
    @ResponseBody
    public byte[] loadRole(Integer id){
        if(id!=null){
            String beanStr = roleService.selectRoleById(id);
            return beanStr.getBytes();
        }

        return null;
    }

    /**
     * 功能描述 删除角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param id
     * @return int 1:删除成功 0:删除失败
     */
    @RequestMapping("/delRole.do")
    @ResponseBody
    public int delRole(Integer id){
        if(id!=null){
            roleService.deleteRoleById(id);
            return 1;
        }
        return 0;
    }

    /**
     * 功能描述 禁用角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param id
     * @return int 1:禁用成功 0:禁用失败
     */
    @RequestMapping("/unUseRole.do")
    @ResponseBody
    public int unUseRole(Integer id){
        if(id!=null){
            Role role = new Role();
            role.setId(id);
            role.setBun(0); // 设置禁用指示值
            roleService.updateRoleByBun(role);
            return 1;
        }
        return 0;
    }

    /**
     * 功能描述 启用角色
     * @author pyj
     * @date 2018/10/16 0016
     * @param id
     * @return int 1:启用成功 0:启用失败
     */
    @RequestMapping("/useRole.do")
    @ResponseBody
    public int useRole(Integer id){
        if(id!=null){
            Role role = new Role();
            role.setId(id);
            role.setBun(1); // 设置启用指示值
            roleService.updateRoleByBun(role);
            return 1;
        }
        return 0;
    }

    /**
     * 功能描述 修改角色信息
     * @author pyj
     * @date 2018/10/17 0017
     * @param role 角色组
     * @param perms 权限组
     * @return int
     */
    @RequestMapping("/updRole.do")
    @ResponseBody
    public int useRole(Role role,String perms){
        if(role!=null){
            roleService.updateRole(role,perms);
            return 1;
        }
        return 0;
    }
}
