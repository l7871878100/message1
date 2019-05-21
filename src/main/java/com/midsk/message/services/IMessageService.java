package com.midsk.message.services;

import com.midsk.message.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMessageService {

    Message saveMessage(Message entity);

    Page<Message> findAllByTheme(Pageable pageable);

    Page<Message> findAllByReplyList(long id, Pageable pageable);

    List<Message> findAllByReplyList(long id);

    Page<Message> findAll(Pageable pageable);

    Optional<Message> findById(long id);
}


