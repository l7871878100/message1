package com.midsk.message.services;

import com.midsk.message.dao.MessageRepository;
import com.midsk.message.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {


    @Autowired
    private MessageRepository repository;


    @Override
    public Message saveMessage(Message entity) {
        return repository.save(entity);
    }

    @Override
    public Page<Message> findAllByTheme(Pageable pageable) {
        return repository.findAllByThemeIsNotNull(pageable);
    }

    @Override
    public Page<Message> findAllByReplyList(long id, Pageable pageable) {
        return repository.findAllByIdOrReplyId(id,pageable);
    }

    @Override
    public List<Message> findAllByReplyList(long id) {
        return repository.findAllByReplyId(id);
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Message> findById(long id) {
        return repository.findById(id);
    }
}
