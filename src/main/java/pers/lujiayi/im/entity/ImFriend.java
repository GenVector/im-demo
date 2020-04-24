package pers.lujiayi.im.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lujiayi
 * @date 2020/4/22
 */
@Data
public class ImFriend {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private String categoryId;
    private String friendId;
    private LocalDateTime createTime;
}
