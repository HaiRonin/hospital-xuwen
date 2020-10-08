package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableCaching
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "         _                                                            \n" +
                "       | |                                                           \n" +
                "  _ __ | | __ ______  ___   _ _   _   ___ ___  _ __ ___    ___ _ __  \n" +
                " | '_ \\| |/ /|_  /\\ \\/ / | | | | | | / __/ _ \\| '_ ` _ \\  / __| '_ \\ \n" +
                " | | | |   <  / /  >  <| |_| | |_| || (_| (_) | | | | | || (__| | | |\n" +
                " |_| |_|_|\\_\\/___|/_/\\_\\\\__, |\\__, (_)___\\___/|_| |_| |_(_)___|_| |_|\n" +
                "                         __/ | __/ |                                 \n" +
                "                        |___/ |___/                                  ");
    }
}