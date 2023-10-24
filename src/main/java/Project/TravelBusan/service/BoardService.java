package Project.TravelBusan.service;


import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.repository.BoardRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.BoardModifyRequestDto;
import Project.TravelBusan.request.BoardRequestDto;
import Project.TravelBusan.response.BoardListResponseDto;
import Project.TravelBusan.response.BoardResponseDto;
import Project.TravelBusan.response.BoardSaveResponseDto;
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
    public ResponseDto<BoardListResponseDto> detailBoard(Long boardId) {
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        boardRepository.increaseVisit(board.getId()); // 조회수 증가
        return ResponseDto.success("게시글 상세 조회",
                BoardListResponseDto.builder()
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
    public ResponseDto<Void> boardRemove(Long boardId) {
        Board board = boardRepository.findByBoardOrElseThrow(boardId);
        // 작성자 검증 로직 필요
        boardRepository.deleteById(board.getId());
        return ResponseDto.success("게시글 삭제 성공", null);
    }
}
