package pers.lujiayi.im.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lujiayi
 * @date 2020/4/22
 */
@Data
public class ImMessageDTO {
    private String fromId;
    private String fromName;
    private String fromAvatar;
    private String toId;
    private String toName;
    private String toAvatar;
    private String type;
    private String msgType;
    private String content;
    private LocalDateTime sendTime;
}
