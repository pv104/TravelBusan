package Project.TravelBusan.controller;

import Project.TravelBusan.request.BoardRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseDto<?> boardWrite(@RequestBody BoardRequestDto boardRequestDto){
        return boardService.saveBoard(boardRequestDto, 1L);
    }

}
