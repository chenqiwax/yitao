package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.UserCollection;
import cn.et.yitao.user.dao.UserCollectionDao;
import cn.et.yitao.user.dao.UserDao;
import cn.et.yitao.user.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:zpf
 * Datetime:2018年10月10日 10:58
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService{

    @Autowired
    private UserCollectionDao userCollectionDao;


    /**
     * 用户收藏
     * @param uid 用户id
     * @param gid 书籍id
     * @return 0 错误 1 成功
     * @throws SQLException
     */
    @Override
    public Integer insertCollection(String uid,Integer gid) throws SQLException {
        UserCollection userCollection = new UserCollection();
        userCollection.setUid(uid);
        userCollection.setGid(gid);

        List<UserCollection> listUserCollection = userCollectionDao.getListUserCollection(userCollection);
        if (!listUserCollection.isEmpty()) {
            for (UserCollection userCollections : listUserCollection) {
                if (userCollections.getIsdelete() != 0){
                    userCollections.setIsdelete(0);
                    userCollectionDao.updateUserCollection(userCollections);
                    return 1;
                }else {
                    return 0;
                }
            }
        }else {
            userCollection.setIsdelete(0);
            userCollectionDao.addCollection(userCollection);
            return 1;
        }
        return 0;
    }

    /**
     * 逻辑删除用户收藏的书籍
     * @return
     * @throws SQLException
     */
    @Override
    public Integer updateUserCollection(String uid, Integer gid) throws SQLException {
        UserCollection userCollection = new UserCollection();
        userCollection.setUid(uid);
        userCollection.setGid(gid);
        List<UserCollection> listUserCollection = userCollectionDao.getListUserCollection(userCollection);
        if (!listUserCollection.isEmpty()) {
            UserCollection userCollection1 = listUserCollection.get(0);
            if (userCollection1.getIsdelete()==0){
                userCollection1.setIsdelete(1);
                Integer id = userCollection1.getId();
                userCollectionDao.updateUserCollection(userCollection1);
                return 1;
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }

    /**
     * 根据书籍状态查询所有收藏书籍
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<UserCollection> getListUserCollection(String uid) throws SQLException {
        UserCollection userCollection = new UserCollection();
        userCollection.setUid(uid);
        userCollection.setIsdelete(0);
        List<UserCollection> userCollectionList = new ArrayList<>();
        List<UserCollection> listUserCollection = userCollectionDao.getListUserCollection(userCollection);
        for (UserCollection userCollection1:listUserCollection) {
            if (userCollection1.getBook()!=null)userCollectionList.add(userCollection1);
        }
        if (userCollectionList.isEmpty()) {
            return null;
        }
        return userCollectionList;
    }



    /**
     * 查询用户已经取消的收藏书籍
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<UserCollection> getListUserCollections(String uid) throws SQLException {
        UserCollection userCollection = new UserCollection();
        userCollection.setUid(uid);
        userCollection.setIsdelete(1);
        List<UserCollection> userCollectionList = new ArrayList<>();
        List<UserCollection> listUserCollections = userCollectionDao.getListUserCollection(userCollection);
        for (UserCollection userCollection1:listUserCollections) {
            if (userCollection1.getBook()!=null)userCollectionList.add(userCollection1);
        }
        if (userCollectionList.isEmpty()) {
            return null;
        }
        return userCollectionList;
    }


}