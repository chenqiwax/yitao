package cn.et.yitao.user.service.impl;

import cn.et.yitao.user.bean.UserMessage;
import cn.et.yitao.user.dao.UserMessageDao;
import cn.et.yitao.user.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @描述: 消息类的service实现
 * @Author: pyj
 * @DateTime: 2018/10/15 0015--下午 4:44
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageDao userMessageDao;

    @Override
    public List<UserMessage> selectMsgByUid(String uid) {
        return userMessageDao.findALLMsgByUid(uid);
    }

    @Override
    public int selectUnreadMsgCountByUid(String uid) {
        return userMessageDao.findUnreadMsgCountByUid(uid);
    }

    @Override
    public void updateMsgStatus(Integer mid) {
        userMessageDao.editMsgStatus(mid);
    }

    @Override
    public void insertMsg(UserMessage userMessage) {
        userMessageDao.addMsg(userMessage);
    }

    @Override
    public void deleteMsgByMid(Integer mid) {
        userMessageDao.removeMsgByMid(mid);
    }
}

