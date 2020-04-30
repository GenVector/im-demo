package pers.lujiayi.im.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lujiayi.im.entity.ImGroupUser;
import pers.lujiayi.im.entity.ImUser;

import java.util.List;

public interface ImGroupUserMapper extends BaseMapper<ImGroupUser> {

    List<ImUser> getGroupUsers(String groupId);

}
