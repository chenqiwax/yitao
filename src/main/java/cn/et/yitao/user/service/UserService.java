package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.Role;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.util.YiTaoResult;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @author: pyj
     */
    public YiTaoResult insertUser(User user, String vcode);


    /**
     * 根据id查询用户个人基本信息
     * fangciaxia
     *
     * @param uid
     * @return
     */
    public User findUserByUserId(String uid);

    /**
     * 根据用户id修改用户个人基本信息
     * fangcaixia
     *
     * @param user
     * @return
     */
    public User updateUser(User user);

    /**
     * 功能描述 用于做(用户名的ajax验证)
     *
     * @param account 账户名
     * @return cn.et.yitao.user.bean.User
     * @author pyj
     * @date 2018/10/10 0010
     */
    public User selectUserByAccount(String account);

    /**
     * 功能描述 用于做(用户手机号码的ajax验证)
     *
     * @param telephone 手机号码
     * @return java.lang.String
     * @author pyj
     * @date 2018/10/10 0010
     */
    public String selectTelephone(String telephone);

    /**
     * 功能描述 修改用户的头像
     *
     * @param user        用户
     * @param newfileName 用户头像地址
     * @param uid         用户id
     * @return int
     * @author pyj
     * @date 2018/10/10 0010
     */
    public int updateUserPicture(User user);


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

    public User updateUserPicture(String newfileName, String uid);

    /**
     * 功能描述 用户登录
     *
     * @param account  用户名
     * @param password 用户密码
     * @return User
     * @author pyj
     * @date 2018/10/11 0011
     */
    public User userLoginIn(String account, String password);

    /**
     * 根据手机号得到用户
     * author:chenqi
     *
     * @param telphone
     * @return
     */
    User getUserByTelphone(String telphone);

    /**
     * author:chenqi
     * desc:根据账户名查询用户所有角色
     *
     * @param account
     * @return
     */
    Set<String> getRoleByAccount(String account);

    /**
     * author:chenqi
     * desc:根据手机号查询该用户所有角色
     *
     * @param tel
     * @return
     */
    Set<String> getRoleByTel(String tel);

    /**
     * author:chenqi
     * desc:根据账户名查询用户的所有角色
     *
     * @param account
     * @return
     */
    Set<String> getPermissionByAccount(String account);


    /**
     * author:chenqi
     * desc:根据手机号查询用户所有的权限
     *
     * @param tel
     * @return
     */
    Set<String> getPermissionByTel(String tel);

    /**
     * author:chenqi
     * 判断该邮箱是否存在
     *
     * @param email
     * @return true:存在,false:不存在
     */
    Boolean isExistEmail(String email);

    /**
     * author:chenqi
     * 判断该昵称是否存在
     *
     * @param nickName
     * @return true:存在,false:不出在
     */
    Boolean isExistNickName(String nickName);

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    List<User> getDimUserList(User user);
}
