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
                "                                    _                                      _             _                                     \n" +
                "                                   (_)                                    (_)           (_)                                    \n" +
                "__  ___   ___      _____ _ __ __  ___  __ _ _ __  _ __ ___ _ __  _ __ ___  _ _ __  _   _ _ _   _ _   _  __ _ _ __    ___ _ __  \n" +
                "\\ \\/ / | | \\ \\ /\\ / / _ \\ '_ \\\\ \\/ / |/ _` | '_ \\| '__/ _ \\ '_ \\| '_ ` _ \\| | '_ \\| | | | | | | | | | |/ _` | '_ \\  / __| '_ \\ \n" +
                " >  <| |_| |\\ V  V /  __/ | | |>  <| | (_| | | | | | |  __/ | | | | | | | | | | | | |_| | | |_| | |_| | (_| | | | || (__| | | |\n" +
                "/_/\\_\\\\__,_| \\_/\\_/ \\___|_| |_/_/\\_\\_|\\__,_|_| |_|_|  \\___|_| |_|_| |_| |_|_|_| |_|\\__, |_|\\__, |\\__,_|\\__,_|_| |_(_)___|_| |_|\n" +
                "                                                                                    __/ |   __/ |                              \n" +
                "                                                                                   |___/   |___/                               ");
    }
}