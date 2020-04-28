package pers.lujiayi.im;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import pers.lujiayi.im.mapper.ImGroupUserMapper;
import pers.lujiayi.im.mapper.ImMessageMapper;
import pers.lujiayi.im.websocket.WebSocketServer;

@SpringBootApplication
@MapperScan("pers.lujiayi.im")
public class ImApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ImApplication.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        WebSocketServer.setApplicationContext(applicationContext);
    }

}
