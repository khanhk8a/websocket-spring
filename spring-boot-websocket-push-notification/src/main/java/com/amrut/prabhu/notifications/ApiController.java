package com.amrut.prabhu.notifications;

import com.amrut.prabhu.notifications.entity.Message;
import com.amrut.prabhu.notifications.entity.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // save message
    @GetMapping("/message")
    public void sendMes(@RequestParam String text, @RequestParam String to) {
        Message mes =  new Message();
        mes.setText(text);
        mes.setRecipient(to);
        messageRepository.save(mes);

     // code giúp tôi   gửi thông báo đến trang notice
        simpMessagingTemplate.convertAndSend("/all/messages", mes);


    }
}
