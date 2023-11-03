package Project.TravelBusan.service;


import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.BoardComment;
import Project.TravelBusan.domain.BoardLike;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.repository.BoardCommentRepository;
import Project.TravelBusan.repository.BoardLikeRepository;
import Project.TravelBusan.repository.BoardRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.Board.BoardModifyRequestDto;
import Project.TravelBusan.request.Board.BoardRequestDto;
import Project.TravelBusan.response.Board.*;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public ResponseDto<BoardSaveResponseDto> addBoard(BoardRequestDto boardRequestDto, Long userId) {
        // 작성자 정보 받아와야됨
        User user = userRepository.findByIdOrElseThrow(userId);

        boardRequestDto.updateCreateBy(1L, 0L, user);

        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .nickname(boardRequestDto.getUser().getNickname())
                .visit(boardRequestDto.getVisit())
                .likeCount(boardRequestDto.getLikeCount())
                .user(boardRequestDto.getUser())
                .build();

        boardRepository.save(board);

        return ResponseDto.success(
                "게시글 생성 성공",
                BoardSaveResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .nickname(board.getUser().getNickname())
                        .visit(board.getVisit())
                        .likeCount(board.getLikeCount())
                        .creDate(board.getCreDate())
                        .build()
        );
    }

    /**
     * 게시글 전체 조회
     */
    public ResponseDto<List<BoardListResponseDto>> listBoard() {
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BoardListResponseDto> boardListResponseDto = boards.stream()
                .map(o -> BoardListResponseDto.builder()
                        .id(o.getId())
                        .title(o.getTitle())
                        .content(o.getContent())
                        .nickname(o.getNickname())
                        .visit(o.getVisit())
                        .likeCount(o.getLikeCount())
                        .creDate(o.getCreDate())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.success("게시물 전체 조회", boardListResponseDto);
    }

    /**
     * 게시글 상세 조회
     */
    @Transactional
    public ResponseDto<BoardDetailResponseDto> detailBoard(Long boardId) {
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        boardRepository.increaseVisit(board.getId()); // 조회수 증가
        return ResponseDto.success("게시글 상세 조회", new BoardDetailResponseDto(board));
    }


    /**
     * 게시글 수정
     */
    @Transactional
    public ResponseDto<BoardResponseDto> modifyBoard(BoardModifyRequestDto boardModifyRequestDto, Long boardId) {
        // 작성자 검증 필요
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        board.modifyBoard(boardModifyRequestDto.getTitle(), boardModifyRequestDto.getContent());

        boardRepository.save(board);

        return ResponseDto.success(
                "게시글 수정 성공",
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .nickname(board.getNickname())
                        .visit(board.getVisit())
                        .likeCount(board.getLikeCount())
                        .creDate(board.getCreDate())
                        .build()
        );
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public ResponseDto<Void> removeBoard(Long boardId) {
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        boardRepository.deleteById(board.getId());
        return ResponseDto.success("게시글 삭제 성공", null);
    }


    /**
     * 게시글 좋아요
     */
    @Transactional
    public ResponseDto<Void> likeBoard(Long userId, Long boardId) {
        // 로그인 정보 필요
        User user = userRepository.findByIdOrElseThrow(userId);
        Board board = boardRepository.findByBoardOrElseThrow(boardId);

        if (boardLikeRepository.findByUserAndBoard(user, board).isPresent()) {
            throw new IllegalStateException("이미 좋아요 누른 게시글입니다.");
        }

        BoardLike boardLike = BoardLike.builder()
                .user(user)
                .board(board)
                .build();

        boardLikeRepository.save(boardLike);

        // 좋아요 수 증가
        board.increaseLike(board.getLikeCount() + 1L);
        boardRepository.save(board);

        return ResponseDto.success("좋아요 성공", null);
    }
}
