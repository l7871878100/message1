package com.midsk.message.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String theme;

    @Column(columnDefinition = "longtext not null")
    private String content;

    private boolean gender;
    private int avatar = 1;
    private String name;
    private boolean display = false;
    private boolean deleted = false;
    private String ipAddress;
    private long replyId;
    private int readNum = 0;
    private int replyNum = 0;

    @CreatedDate
    private LocalDate createDateTime;

    @LastModifiedDate
    private LocalDate latestDateTime;

    private String latestName;

    public Message() {
    }

    public Message(String theme, String content, boolean gender, int avatar, String name, boolean display, boolean deleted, String ipAddress, long replyId, int readNum, int replyNum, String latestName) {
        this.theme = theme;
        this.content = content;
        this.gender = gender;
        this.avatar = avatar;
        this.name = name;
        this.display = display;
        this.deleted = deleted;
        this.ipAddress = ipAddress;
        this.replyId = replyId;
        this.readNum = readNum;
        this.replyNum = replyNum;
        this.latestName = latestName;
    }
}
