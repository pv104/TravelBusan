package Project.TravelBusan.service;

import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.BoardComment;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.repository.BoardCommentRepository;
import Project.TravelBusan.repository.BoardRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.Board.BoardCommentRequestDto;
import Project.TravelBusan.response.Board.BoardCommentResponseDto;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 댓글 대댓글 작성
     */

    @Transactional
    public ResponseDto<?> saveBoardComment(Long boardId, BoardCommentRequestDto boardCommentRequestDto, Long userId) {
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        User user = userRepository.findByIdOrElseThrow(userId);

        if (boardCommentRequestDto.getParent() == null) {
            // 부모 댓글 작성
            BoardComment boardComment = BoardComment.builder()
                    .content(boardCommentRequestDto.getContent())
                    .writer(user.getNickname())
                    .user(user)
                    .board(board)
                    .parent(null) // 대댓글이 아니므로 parent 필드는 null로 설정x
                    .build();

            boardCommentRepository.save(boardComment);

            return ResponseDto.success(
                    "댓글 작성 성공",
                    BoardCommentResponseDto.toDto(boardComment)
            );
        } else {
            // 자식 댓글 작성
            BoardComment parentComment = boardCommentRepository.findById(boardCommentRequestDto.getParent().getId()).orElseThrow(() ->
                    new IllegalStateException("부모 댓글이 없습니다"));

            BoardComment boardComment = BoardComment.builder()
                    .writer(user.getUsername())
                    .user(user)
                    .board(board)
                    .parent(parentComment) // 대댓글의 경우 부모 댓글 설정
                    .content(boardCommentRequestDto.getContent())
                    .build();

            boardCommentRepository.save(boardComment);

            return ResponseDto.success(
                    "대댓글 작성 성공",
                    BoardCommentResponseDto.toDto(boardComment)
            );
        }
    }

}
