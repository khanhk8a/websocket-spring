package com.amrut.prabhu.notifications;

import com.amrut.prabhu.notifications.entity.Message;
import com.amrut.prabhu.notifications.entity.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public MessageDTO send(final MessageDTO messageDTO) {
        return messageDTO;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload MessageDTO messageDTO) {
        simpMessagingTemplate.convertAndSendToUser(messageDTO.getTo(), "/specific", messageDTO);
    }

    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }
}