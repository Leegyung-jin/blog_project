package com.lee.blog.repository;

import com.lee.blog.dto.ReplySaveRequestDto;
import com.lee.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying  // select로 걸리지 않게
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content);   // 업데이트된 행의 개수를 리턴해준다.
}
