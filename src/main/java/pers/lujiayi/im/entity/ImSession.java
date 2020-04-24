package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImSession {
    private String id;
    private String userId;
    private String sessionId;
    private String sessionName;
    private LocalDateTime updateTime;
}
