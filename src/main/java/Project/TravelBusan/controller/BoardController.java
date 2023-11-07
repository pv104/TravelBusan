package Project.TravelBusan.controller;

import Project.TravelBusan.request.Board.BoardCommentRequestDto;
import Project.TravelBusan.request.Board.BoardModifyRequestDto;
import Project.TravelBusan.request.Board.BoardRequestDto;
import Project.TravelBusan.response.Board.*;
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
    private final BoardCommentService boardCommentService;

    @PostMapping
    public ResponseDto<BoardSaveResponseDto> boardAdd(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.addBoard(boardRequestDto);
    }

    @GetMapping
    public ResponseDto<List<BoardListResponseDto>> boardList(){
        return boardService.listBoard();
    }

    @GetMapping("/{board-id}")
    public ResponseDto<BoardDetailResponseDto> boardDetails(@PathVariable("board-id") Long boardId){
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
        return boardService.likeBoard(boardId);
    }

    @PostMapping("/{board-id}/comment")
    public ResponseDto<CommentSaveResponseDto> addComment(@PathVariable("board-id") Long boardId, @RequestBody BoardCommentRequestDto boardCommentRequestDto) {
        return boardCommentService.commentAdd(boardId,boardCommentRequestDto);
    }

    @DeleteMapping("/{board-id}/comment/{comment-id}")
    public ResponseDto<Void> deleteComment(@PathVariable("comment-id") Long commentId, @PathVariable("board-id") Long boardId) {
        return boardCommentService.commentDelete(commentId);
    }
}