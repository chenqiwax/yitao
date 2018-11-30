package cn.et.yitao.user.dao;

import cn.et.yitao.user.bean.UserMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @描述: 消息的dao层
 * @Author: pyj
 * @DateTime: 2018/10/12 0012--下午 2:18
 */
@Repository
public interface UserMessageDao {

    /**
     * 功能描述 根据用户的id查询它所有的未读消息
     * @author pyj
     * @date 2018/10/12 0012
     * @param uid 用户id
     * @return java.util.List<cn.et.yitao.user.bean.UserMessage>
     */
    List<UserMessage> findALLMsgByUid(String uid);

    /**
     * 功能描述 根据用户id,查询未读消息的数量
     * @author pyj
     * @date 2018/10/12 0012
     * @param uid  用户id
     * @return int 未读消息的数量
     */
    int findUnreadMsgCountByUid(String uid);

    /**
     * 功能描述 根据消息表id,修改消息的状态
     * @author pyj
     * @date 2018/10/12 0012
     * @param mid 消息id
     * @return void
     */
    void editMsgStatus(Integer mid);

    /**
     * 功能描述 新增消息
     * @author pyj
     * @date 2018/10/12 0012
     * @param userMessage 消息类
     * @return void
     */
    void addMsg(UserMessage userMessage);

    /**
     * 功能描述 根据消息id,删除消息
     * @author pyj
     * @date 2018/10/12 0012
     * @param mid 消息id
     * @return void
     */
    void removeMsgByMid(Integer mid);

    List<UserMessage> getListUserMessage(UserMessage userMessage);


}
