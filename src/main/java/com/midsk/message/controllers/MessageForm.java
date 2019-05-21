package com.midsk.message.controllers;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageForm implements Serializable {

    private String username;
    private String message;
    private String theme;
    private long replyId;

}
