package Project.TravelBusan.controller;

import Project.TravelBusan.request.Board.BoardModifyRequestDto;
import Project.TravelBusan.request.Board.BoardRequestDto;
import Project.TravelBusan.response.BoardListResponseDto;
import Project.TravelBusan.response.BoardResponseDto;
import Project.TravelBusan.response.BoardSaveResponseDto;
import Project.TravelBusan.response.ResponseDto;
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
}
