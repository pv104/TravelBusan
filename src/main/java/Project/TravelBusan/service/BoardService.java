package Project.TravelBusan.service;


import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.Member;
import Project.TravelBusan.repository.BoardRepository;
import Project.TravelBusan.repository.MemberRepository;
import Project.TravelBusan.request.BoardRequestDto;
import Project.TravelBusan.response.BoardSaveResponseDto;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public ResponseDto<?> saveBoard(BoardRequestDto boardRequestDto, Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        boardRequestDto.updateCreateBy(1L, 0L, member);

        Board board = Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .nickname(member.getNickname())
                .visit(boardRequestDto.getVisit())
                .likeCount(boardRequestDto.getLikeCount())
                .member(boardRequestDto.getMember())
                .build();

        boardRepository.save(board);

        return ResponseDto.success(
                "게시글 생성 성공",
                BoardSaveResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .nickname(member.getNickname())
                        .visit(board.getVisit())
                        .like(board.getLikeCount())
                        .creDate(board.getCreDate())
                        .build()
        );


    }
}
