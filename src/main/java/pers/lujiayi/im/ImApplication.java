package pers.lujiayi.im;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pers.lujiayi.im.mapper.ImGroupUserMapper;
import pers.lujiayi.im.mapper.ImMessageMapper;
import pers.lujiayi.im.websocket.WebSocketServer;

@SpringBootApplication
@MapperScan("pers.lujiayi.im")
public class ImApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ImApplication.class, args);
        WebSocketServer.setApplicationContext(applicationContext);
    }

}
