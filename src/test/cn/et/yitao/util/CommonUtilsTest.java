package cn.et.yitao.util;

import cn.et.yitao.user.bean.User;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

public class CommonUtilsTest {

    @Test
    public void toBean() {
    }

    @Test
    public void toMap() {
        User user = new User();
        user.setId("12315");
        user.setRegisterDate(new Date());
        user.setBirthday(new Date());
        user.setEmail("1231545");
        Map map = CommonUtils.toMap(user);
        System.out.println(map);
        User user1 = CommonUtils.toBean(map, User.class);
        System.out.println(user1);
    }
}