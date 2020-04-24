package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImGroupUser {
    private String id;
    private String groupId;
    private String userId;
    private LocalDateTime createTime;
}
