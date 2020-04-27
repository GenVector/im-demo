package pers.lujiayi.im.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pers.lujiayi.im.entity.ImUser;
import pers.lujiayi.im.mapper.ImUserMapper;

/**
 * @author Lujiayi
 * @date 2020/4/27
 */
@Component
public class imListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ImUserMapper imUserMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ImUser imUser = new ImUser();
        imUser.setStatus(ImUser.OFFLINE);
        this.imUserMapper.update(imUser, new QueryWrapper<>());
    }

}
