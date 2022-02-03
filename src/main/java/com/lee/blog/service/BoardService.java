package com.lee.blog.service;

import com.lee.blog.dto.ReplySaveRequestDto;
import com.lee.blog.model.Board;
import com.lee.blog.model.Reply;
import com.lee.blog.model.User;
import com.lee.blog.repository.BoardRepository;
import com.lee.blog.repository.ReplyRepository;
import com.lee.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    // Autowired 없이 @RequiredArgsConstructor와 아래의 코드를 이용하여 간단하게 표현할 수 있다.
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Autowired
//    private ReplyRepository replyRepository;
//
//    @Autowired
//    private UserRepository userRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {  // title, content만 받는다.
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
            .orElseThrow(()-> { // option을 리턴한다.
                return new IllegalIdentifierException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
            });
    }

    @Transactional
    public void 삭제하기(int id) {
        System.out.println("삭제=====" + id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> {
                    return new IllegalIdentifierException("글 찾기 실패: 게시글을 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료 시(Service가 종료) 트랜잭션이 종료된다. 이 때 더티체킹을 진행한다.(영속화된 데이터(board)가 달라졌기 때문 > 자동 업데이트된다.)
    }

    @Transactional
//    public void 댓글작성(User user, int boardId, Reply requestReply){
    public void 댓글작성(ReplySaveRequestDto replySaveRequestDto){


//        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()-> {
//            return new IllegalIdentifierException("댓글 작성 실패: User ID를 찾을 수 없습니다.");
//        });
//
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()-> {
//            return new IllegalIdentifierException("댓글 작성 실패: 게시글 ID를 찾을 수 없습니다.");
//        });

//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();

//        requestReply.setUser(user);
//        requestReply.setBoard(board);

//        Reply reply = new Reply();
//        reply.update(user, board, replySaveRequestDto.getContent());

//        replyRepository.save(reply);
        int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
        System.out.println("BoardService: " + result);
    }

    @Transactional
    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
