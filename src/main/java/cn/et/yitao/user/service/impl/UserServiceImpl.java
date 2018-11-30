package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.Role;
import cn.et.yitao.user.bean.SecurityCode;
import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserPermission;
import cn.et.yitao.user.dao.UserDao;
import cn.et.yitao.user.service.SecurityCodeService;
import cn.et.yitao.user.service.UserService;
import cn.et.yitao.util.RandomUtil;
import cn.et.yitao.util.SaltUtils;
import cn.et.yitao.util.YiTaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityCodeService securityCodeService;


    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public YiTaoResult insertUser(User user, String vcode) {
        String userid = RandomUtil.getRandom(9); // 设置用户的id 调用RandomUtil工具类生成9位的随机数
        user.setId(userid);
        SecurityCode selectcode = securityCodeService.selectcode(user.getTelephone());
        if (selectcode == null) {
            return YiTaoResult.build(400, "未发验证码");
        }
        if ((new Date().getTime() - selectcode.getTime().getTime()) > 1000 * 60) {
            return YiTaoResult.build(404, "验证码已过期");
        }
        if (!vcode.equals(selectcode.getCode())) {
            return YiTaoResult.build(406, "验证码不正确");
        }
        user.setRegisterDate(new Date()); // 设置注册时间
        user.setSalt(SaltUtils.getSaltValue()); // 设置盐值
        String userPassword = SaltUtils.getMatcher(user.getPassword(), user.getSalt());// SaltUtils工具类,对用户密码进行加密
        user.setPassword(userPassword); // 将加密的密码保存到用户表中
        user.setIconUrl("http://192.168.4.254:8888/three/img/userPicture/cda8699739994aae8faa30b4b6675476.jpg");
        userDao.addUser(user);
        return YiTaoResult.ok();
    }

    /**
     * 根据id查询用户个人基本信息
     * 方彩霞
     *
     * @param uid
     * @return
     */
    @Override
    public User findUserByUserId(String uid) {
        User userByUserId = userDao.findUserByUserId(uid);
        return userByUserId;
    }

    /**
     * 根据用户id修改用户个人基本信息
     * fangcaixia
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        int i = userDao.updateUser(user);
        if (i == 1) {
            User user1 = new User();
            user1.setId(user.getId());
            List<User> userList = userDao.getUserList(user1);
            if (userList != null && !userList.isEmpty()) {
                return userList.get(0);
            }
            return null;
        }
        return null;
    }


    /**
     * 功能描述 验证用户名是否重复
     *
     * @param account
     * @return cn.et.yitao.user.bean.User 实体类user
     * @authorpyj
     * @date 2018/10/10 0010
     */
    @Override
    public User selectUserByAccount(String account) {
        if (account == null || account.length() == 0) {
            throw new RuntimeException("用户名不能为空");
        }
        User user = new User();
        user.setAccount(account);
        List<User> userList = userDao.getUserList(user);
        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 功能描述 验证用户手机号码是否重复
     *
     * @param telephone 手机号码
     * @return java.lang.String 手机号
     * @author pyj
     * @date 2018/10/10 0010
     */
    @Override
    public String selectTelephone(String telephone) {
        if (telephone == null) {
            throw new RuntimeException("用户手机号码不能为空");
        }
        return userDao.inspection(telephone);
    }

    @Override
    public int updateUserPicture(User user) {
        return 0;
    }

    /**
     * 功能描述 修改用户的头像
     *
     * @param newfileName 头像地址
     * @param uid         当前操作的用户id
     * @return int 1 修改成功 0 修改失败
     * @author pyj
     * @date 2018/10/10 0010
     */
    @Override
    public User updateUserPicture(String newfileName, String uid) {
        if (newfileName == null || uid == null)
            return null;
        User user = new User();
        user.setIconUrl(newfileName);
        user.setId(uid);
        userDao.uploadUserPicture(user);
        User user1 = userDao.findUserByUserId(uid);
        if (user1 == null) { // 根据user的id查询用户
            return null;
        }
        return user1;
    }

    /**
     * 功能描述 用户登录验证
     *
     * @param account  用户名
     * @param password 用户密码
     * @return User
     * @author pyj
     * @date 2018/10/11 0011
     */
    @Override
    public User userLoginIn(String account, String password) {
        User user = userDao.findUserByAccount(account); // 根据用户名查询用户
        if (user != null) { // 判读user是否为null,不为null的话,就判断该用户的密码是否和查询出来的密码相同

            return user;
        }
        return null;
    }

    @Override
    public User getUserByTelphone(String telphone) {
        User user = new User();
        user.setTelephone(telphone);
        List<User> userList = userDao.getUserList(user);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> getRoleByAccount(String account) {
        List<Role> roleByAccount = userDao.getRoleByAccount(account);
        Set<String> roleSet = new HashSet<>();
        for (Role role : roleByAccount) {
            roleSet.add(role.getName());
        }
        return roleSet;
    }

    @Override
    public Set<String> getRoleByTel(String tel) {
        List<Role> roleByTel = userDao.getRoleByTel(tel);
        Set<String> roleSet = new HashSet<>();
        for (Role role : roleByTel) {
            roleSet.add(role.getName());
        }
        return roleSet;
    }

    @Override
    public Set<String> getPermissionByAccount(String account) {
        List<UserPermission> permissionByAccount = userDao.getPermissionByAccount(account);
        Set<String> permissionSet = new HashSet<>();
        for (UserPermission permission : permissionByAccount) {
            permissionSet.add(permission.getName());
        }
        return permissionSet;
    }

    @Override
    public Set<String> getPermissionByTel(String tel) {
        List<UserPermission> permissionByTel = userDao.getPermissionByTel(tel);
        Set<String> permissionSet = new HashSet<>();
        for (UserPermission permission : permissionByTel) {
            permissionSet.add(permission.getName());
        }
        return permissionSet;
    }

    @Override
    public Boolean isExistEmail(String email) {
        User user = new User();
        user.setEmail(email);
        List<User> userList = userDao.getUserList(user);
        if (userList != null && !userList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isExistNickName(String nickName) {
        User user = new User();
        user.setNickname(nickName);
        List<User> userList = userDao.getUserList(user);
        if (userList != null && !userList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getDimUserList(User user) {
        return userDao.getDimUserList(user);
    }

    /**
     * 根据用户输入的值
     *
     * @param name
     * @return
     */
    @Override
    public String verify(String name) {
        return userDao.verify(name);
    }

    /**
     * 根据邮箱来获取用户id
     *
     * @param emil
     */
    @Override
    public String getuid(String emil) {
        return userDao.getuid(emil);
    }


}
