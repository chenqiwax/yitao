package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.UserCollection;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**用户收藏的Service层
 * Author:zpf
 * Datetime:2018年10月10日 10:55
 */
public interface UserCollectionService {

    /**
     * 用户收藏
     * zhoupengfei
     * @throws SQLException
     */
    public Integer insertCollection(String uid, Integer gid) throws SQLException;


    /**
     * 逻辑删除收藏书籍
     * @return
     * @throws SQLException
     */
    public Integer updateUserCollection(String uid, Integer gid) throws SQLException;

    /**
     * 查询用户所有收藏书籍
     * @return
     * @throws SQLException
     */
    public List<UserCollection> getListUserCollection(String uid) throws SQLException;

    /**
     * 查询用户已经取消的收藏书籍
     * @param uid
     * @return
     * @throws SQLException
     */
    public List<UserCollection> getListUserCollections(String uid) throws SQLException;

}
