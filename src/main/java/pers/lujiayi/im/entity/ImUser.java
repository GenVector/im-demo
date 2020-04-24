package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImUser {
    public static final Integer ONLINE = 1;
    public static final Integer OFFLINE = 0;
    public static final Integer MALE = 1;
    public static final Integer FEMALE = 0;

    private String id;
    private String name;
    private String avatar;
    private Integer gender;
    private Integer status;
    private LocalDateTime createTime;
}
