package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImGroup {
    private String id;
    private String name;
    private String avatar;
    private String type;
    private String masterId;
    private LocalDateTime createTime;
}
