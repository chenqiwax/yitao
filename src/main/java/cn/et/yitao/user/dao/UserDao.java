package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 添加用户
     * pyj
     *
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据账户名查询用户
     * pyj
     *
     * @param account
     * @return 一个User实体类
     */
    public User findUserByAccount(String account);

    /**
     * 功能描述 用户头像上传
     *
     * @param user 当前操作的用户
     * @return void
     * @author pyj
     * @date 2018/10/10 0010
     */
    public void uploadUserPicture(User user);

    /**
     * 根据id查询用户个人基本信息
     * fangciaxia
     *
     * @param uid
     * @return
     */
    public User findUserByUserId(String uid);


    /**
     * 通用更新用户方法
     * fangcaioxia
     *
     * @param user
     * @return
     */
    public int updateUser(User user);


    /***
     * 检验手机号是否被注册
     * libinhe
     * @param phone   手机号
     * @return
     */
    public String inspection(String phone);

    /**
     * 根据手机号查询用户id
     */
    public String selectuid(String phone);

    /**
     * 通用精确用户查询
     * chenqi
     *
     * @param user
     * @return
     */
    List<User> getUserList(User user);

    /**
     * 模糊查询用户
     *
     * @param user
     * @return
     */
    List<User> getDimUserList(User user);


    /**
     * 根据用户输入的手机号或者邮箱号来判断是否存在
     *
     * @param name
     * @return
     */
    public String verify(String name);

    /**
     * 根据邮箱查找用户id
     *
     * @param emil
     */
    public String getuid(String emil);

    /**
     * author:chenqi
     * desc:根据账户名查询用户所有角色
     *
     * @param account
     * @return
     */
    List<Role> getRoleByAccount(String account);

    /**
     * author:chenqi
     * desc:根据手机号查询该用户所有角色
     *
     * @return
     */
    List<Role> getRoleByTel(String tel);

    /**
     * author:chenqi
     * desc:根据账户名查询用户的所有角色
     *
     * @param account
     * @return
     */
    List<UserPermission> getPermissionByAccount(String account);

    /**
     * author:chenqi
     * desc:根据手机号查询用户所有的权限
     *
     * @param tel
     * @return
     */
    List<UserPermission> getPermissionByTel(String tel);


}
