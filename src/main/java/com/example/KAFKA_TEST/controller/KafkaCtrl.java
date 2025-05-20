package com.example.KAFKA_TEST.controller;

import com.example.KAFKA_TEST.config.KafkaConfig;
import com.example.KAFKA_TEST.dto.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class KafkaCtrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCtrl.class);

    @Autowired
    private KafkaTemplate<String, UserVo> kafkaTemplateJson;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateStr;

    @GetMapping("/publish/{name}/{password}")
    public String sign(@PathVariable("name") final String name, @PathVariable("password") final String password) {

        UserVo userVo = new UserVo(name, password,"bb123");
        LOGGER.info(userVo.toString());
        kafkaTemplateStr.send(KafkaConfig.BUILD_ORDER_TOPIC, userVo.toString());

        kafkaTemplateJson.send(KafkaConfig.NOTICE_TOPIC, userVo);

        return "Published done";
    }
}
