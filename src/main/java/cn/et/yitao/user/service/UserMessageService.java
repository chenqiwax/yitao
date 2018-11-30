package cn.et.yitao.user.service;

import cn.et.yitao.user.bean.UserMessage;

import java.util.List;

/**
 * @描述: 消息类的service层
 * @Author: pyj
 * @DateTime: 2018/10/15 0015--下午 4:44
 */
public interface UserMessageService {

    /**
     * 功能描述 查询用户的未读消息
     * @author pyj
     * @date 2018/10/15 0015
     * @param uid 用户id
     * @return java.util.List<cn.et.yitao.user.bean.UserMessage>
     */
    List<UserMessage> selectMsgByUid(String uid);

    /**
     * 功能描述 查询未读消息的数量
     * @author pyj
     * @date 2018/10/15 0015
     * @param uid 用户id
     * @return int
     */
    int selectUnreadMsgCountByUid(String uid);

    /**
     * 功能描述 修改消息的状态
     * @author pyj
     * @date 2018/10/15 0015
     * @param mid 消息id
     * @return void
     */
    void updateMsgStatus(Integer mid);

    /**
     * 功能描述 增加消息
     * @author pyj
     * @date 2018/10/15 0015
     * @param userMessage
     * @return void
     */
    void insertMsg(UserMessage userMessage);

    /**
     * 功能描述 删除消息
     * @author pyj
     * @date 2018/10/15 0015
     * @param mid 消息id
     * @return void
     */
    void deleteMsgByMid(Integer mid);
}
