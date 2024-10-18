package be.pxl.services.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messaging")
@RequiredArgsConstructor
public class MessagingController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(){
        rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
    }
}
