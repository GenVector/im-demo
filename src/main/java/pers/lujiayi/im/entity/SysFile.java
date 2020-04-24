package pers.lujiayi.im.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lujiayi
 * @date 2020/4/24
 */
@Data
public class SysFile {
    private String id;
    private String name;
    private String ext;
    private Long size;
    private LocalDateTime createTime;
}
