package com.daishuai.wechatsell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/9 23:03
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
