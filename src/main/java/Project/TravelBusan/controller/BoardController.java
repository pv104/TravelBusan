package Project.TravelBusan.controller;

import Project.TravelBusan.request.Board.BoardCommentRequestDto;
import Project.TravelBusan.request.Board.BoardModifyRequestDto;
import Project.TravelBusan.request.Board.BoardRequestDto;
import Project.TravelBusan.response.Board.BoardListResponseDto;
import Project.TravelBusan.response.Board.BoardResponseDto;
import Project.TravelBusan.response.Board.BoardSaveResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.BoardCommentService;
import Project.TravelBusan.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseDto<BoardSaveResponseDto> boardAdd(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.addBoard(boardRequestDto, 1L);
    }

    @GetMapping
    public ResponseDto<List<BoardListResponseDto>> boardList(){
        return boardService.listBoard();
    }

    @GetMapping("/{board-id}")
    public ResponseDto<BoardListResponseDto> boardDetails(@PathVariable("board-id") Long boardId){
        return boardService.detailBoard(boardId);
    }

    @DeleteMapping("/{board-id}")
    public ResponseDto<Void> boardRemove(@PathVariable("board-id") Long boardId) {
        return boardService.removeBoard(boardId);
    }

    @PatchMapping("/{board-id}")
    public ResponseDto<BoardResponseDto> boardModify(@PathVariable("board-id") Long boardId, @RequestBody BoardModifyRequestDto boardModifyRequestDto) {
        return boardService.modifyBoard(boardModifyRequestDto, boardId);
    }

    @GetMapping("/{board-id}/boardLike")
    public ResponseDto<Void> boardLike(@PathVariable("board-id") Long boardId) {
        Long userId = 1L;
        return boardService.likeBoard(userId,boardId);
    }

/*    @PostMapping("/{board-id}/comment")
    public ResponseDto<?> addComment(@PathVariable("board-id") Long boardId, @RequestBody BoardCommentRequestDto boardCommentRequestDto) {
        Long userId = 1L;
        return boardCommentService.saveBoardComment(boardId,boardCommentRequestDto, userId);
    }*/
}
