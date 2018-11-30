package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.User;
import cn.et.yitao.user.bean.UserCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.object.SqlCall;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public interface UserCollectionDao {

    /**
     * 根据用户添加收藏的书籍id进行收藏
     * zhoupengfei
     * @throws SQLException
     */
    public void addCollection(UserCollection userCollection) throws SQLException;



    /**
     * +根据收藏id进行修改
     * zhoupengfei
     * @param userCollection 用户收藏对象
     * @throws SQLException
     */
    public void updateUserCollection(UserCollection userCollection) throws SQLException;

    /**
     * 查询所有（通用查询）
     * zhoupengfei
     * @return 用户收藏集合
     * @throws SQLException
     */
    public List<UserCollection> getListUserCollection(UserCollection userCollection);



 }
