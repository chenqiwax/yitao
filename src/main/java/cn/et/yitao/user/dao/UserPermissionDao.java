package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.UserPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *Author:chenqi
 *Email:chenqiwax@gmail.com
 *Datetime:2018年10月24日 下午 9:30
 */
@Repository
public interface UserPermissionDao {

    List<UserPermission> getAllActionPermission();

}
