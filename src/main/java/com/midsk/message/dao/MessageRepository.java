package com.midsk.message.dao;

import com.midsk.message.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    Page<Message> findAllByThemeIsNotNull(Pageable pageable);
    List<Message> findAllByReplyId(long id);
    Page<Message> findAllByReplyId(long id,Pageable pageable);
    @Query("select message from Message message where message.id = :id or message.replyId = :id")
    Page<Message> findAllByIdOrReplyId(@Param("id") long id, Pageable pageable);
}
