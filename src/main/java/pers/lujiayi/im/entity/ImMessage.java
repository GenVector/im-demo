package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ImMessage {
    public static final String TYPE_ONE = "TYPE_ONE";
    public static final String TYPE_GROUP = "TYPE_GROUP";
    public static final String TYPE_SYSTEM = "TYPE_SYSTEM";
    public static final String MSG_TYPE_JOIN = "MSG_TYPE_JOIN";
    public static final String MSG_TYPE_LEAVE = "MSG_TYPE_LEAVE";
    public static final String MSG_TYPE_FORCE_LEAVE = "MSG_TYPE_FORCE_LEAVE";

    private String id;
    private String fromId;
    private String toId;
    private String type;
    private String msgType;
    private String content;
    private LocalDateTime sendTime;
}
