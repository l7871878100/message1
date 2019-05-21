package com.midsk.message.controllers;

import com.midsk.message.models.Message;
import com.midsk.message.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ManController {


    @Autowired
    private IMessageService messageService;

    @GetMapping("/messages")
    public Page<Message> index(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "15") int size

    ) {
        return messageService.findAllByTheme(PageRequest.of(page, size));
    }

    @PostMapping("/message/publish")
    public Message publishMessage(@RequestBody Message message, ServerHttpRequest request) {
        message.setIpAddress(getIp(request));

        messageService.saveMessage(message);

        if (message.getReplyId() > 0) {
            messageService.findById(message.getReplyId()).ifPresent(it -> {
                it.setReadNum(it.getReadNum() + 1);
                it.setReplyNum(it.getReplyNum() + 1);
                messageService.saveMessage(it);
            });
        }

        return message;
    }

    @GetMapping("/message/{id}/reply")
    public boolean reolyMessage(@PathVariable("id") Message message
            , @RequestBody MessageForm messageForm
            , ServerHttpRequest request) {

        Message replyMessage = toMessage(messageForm);
        replyMessage.setIpAddress(getIp(request));
        replyMessage.setReplyId(message.getId());
        message.setReplyNum(message.getReplyNum() + 1);
        messageService.saveMessage(replyMessage);
        messageService.saveMessage(message);

        return true;
    }

    @GetMapping("admin/messages")
    public Page<Message> adminMessages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return messageService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/messages/{id}/replyList")
    public Page<Message> replyMessageList(@PathVariable("id") long id,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return messageService.findAllByReplyList(id, PageRequest.of(page, size));
    }

    @PostMapping("/admin/delete/{id}")
    public Message deleteMessage(@PathVariable("id") Message message) {
        message.setDeleted(true);
        return messageService.saveMessage(message);
    }


    @PostMapping("/admin/display/{id}")
    public Message displayMessage(@PathVariable("id") Message message) {
        message.setDisplay(true);
        return messageService.saveMessage(message);
    }


    private Message toMessage(MessageForm form) {
        Message message = new Message();
        message.setTheme(form.getTheme());
        message.setContent(form.getMessage());
        message.setName(form.getUsername());
        message.setReplyId(form.getReplyId());
        return message;
    }

    public static String getIp(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        Map<String, String> valueMap = headers.toSingleValueMap();
        if (valueMap.containsKey("X-FORWARDED-FOR")) {
            String[] ips = valueMap.get("X-FORWARDED-FOR").split(".");
            if (ips.length > 0) {
                return ips[0];
            }
        }
        return request.getRemoteAddress().getAddress().getHostAddress();
    }

}
