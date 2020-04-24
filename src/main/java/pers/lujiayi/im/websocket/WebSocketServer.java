package pers.lujiayi.im.websocket;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import pers.lujiayi.im.entity.ImGroupUser;
import pers.lujiayi.im.entity.ImMessage;
import pers.lujiayi.im.entity.ImUser;
import pers.lujiayi.im.entity.dto.ImMessageDTO;
import pers.lujiayi.im.mapper.ImGroupUserMapper;
import pers.lujiayi.im.mapper.ImMessageMapper;
import pers.lujiayi.im.mapper.ImUserMapper;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ServerEndpoint("/im/{id}")
@Component
public class WebSocketServer {

    private static Integer ONLINE = 0;

    public static ConcurrentHashMap<String, WebSocketServer> WEBSOCKET_MAP = new ConcurrentHashMap<>();

    private Session session;

    private String userId;

    private static ImGroupUserMapper imGroupUserMapper;

    private static ImMessageMapper imMessageMapper;

    private static ImUserMapper imUserMapper;

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        WebSocketServer.imGroupUserMapper = applicationContext.getBean(ImGroupUserMapper.class);
        WebSocketServer.imMessageMapper = applicationContext.getBean(ImMessageMapper.class);
        WebSocketServer.imUserMapper = applicationContext.getBean(ImUserMapper.class);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.userId = id;
        this.session = session;
        WEBSOCKET_MAP.put(this.userId, this);
        ImUser imUser = new ImUser();
        imUser.setId(id);
        imUser.setStatus(ImUser.ONLINE);
        imUserMapper.updateById(imUser);
    }

    @OnMessage
    public void onMessage(String message) {
        ImMessageDTO imMessageDTO = JSON.parseObject(message, ImMessageDTO.class);
        imMessageDTO.setSendTime(LocalDateTime.now());
        ImMessage imMessage = new ImMessage();
        BeanUtils.copyProperties(imMessageDTO, imMessage);
        imMessage.setId(IdUtil.simpleUUID());
        imMessageMapper.insert(imMessage);
        if (imMessageDTO.getType().equals(ImMessage.TYPE_SYSTEM)) {
            if (imMessageDTO.getMsgType().equals(ImMessage.MSG_TYPE_JOIN)) {
//                ImGroupUser imGroupUser = new ImGroupUser();
//                imGroupUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//                imGroupUser.setGroupId(imMessage.getToId());
//                imGroupUser.setUserId(imMessage.getFromId());
//                imGroupUserMapper.insert(imGroupUser);
                WebSocketServer.send2Group(this.getUserByGroupId(imMessageDTO.getToId()), imMessageDTO);
            }
        } else if (imMessageDTO.getType().equals(ImMessage.TYPE_GROUP)) {
            WebSocketServer.send2Group(this.getUserByGroupId(imMessageDTO.getToId()), imMessageDTO);
        } else {
            WebSocketServer.send2One(imMessageDTO.getFromId(), imMessageDTO);
            WebSocketServer.send2One(imMessageDTO.getToId(), imMessageDTO);
        }

    }

    @OnClose
    public void onClose() {
        WEBSOCKET_MAP.remove(this.userId);
        // 用户下线
        ImUser imUser = imUserMapper.selectById(this.userId);
        imUser.setStatus(ImUser.OFFLINE);
        imUserMapper.updateById(imUser);
        // 发送用户下线广播
        ImMessageDTO imMessage = new ImMessageDTO();
        imMessage.setFromId(imUser.getId());
        imMessage.setFromName(imUser.getName());
        imMessage.setFromAvatar(imUser.getAvatar());
        imMessage.setType(ImMessage.TYPE_SYSTEM);
        imMessage.setMsgType(ImMessage.MSG_TYPE_LEAVE);
        imMessage.setSendTime(LocalDateTime.now());
        LambdaQueryWrapper<ImGroupUser> query = new LambdaQueryWrapper();
        query.eq(ImGroupUser::getUserId, this.userId);
        List<ImGroupUser> imGroupUsers = this.imGroupUserMapper.selectList(query);
        if (CollUtil.isNotEmpty(imGroupUsers)) {
            for (ImGroupUser imGroupUser : imGroupUsers) {
                imMessage.setToId(imGroupUser.getGroupId());
                WebSocketServer.send2Group(this.getUserByGroupId(imGroupUser.getGroupId()), imMessage);
            }
        }
    }

    public List<String> getUserByGroupId(String groupId) {
        return this.imGroupUserMapper.selectList(new LambdaQueryWrapper<ImGroupUser>().eq(ImGroupUser::getGroupId, groupId)).stream().map(ImGroupUser::getUserId).collect(Collectors.toList());
    }

    public static void send2Group(List<String> userIds, ImMessageDTO imMessage) {
        for (String userId : userIds) {
            WebSocketServer.send2One(userId, imMessage);
        }
    }

    public static void send2One(String userId, ImMessageDTO imMessage) {
        WebSocketServer webSocketServer = WEBSOCKET_MAP.get(userId);
        if (webSocketServer == null) {
            //TODO 存放离线信息
        } else {
            webSocketServer.session.getAsyncRemote().sendText(JSON.toJSONString(imMessage));
        }
    }
}
