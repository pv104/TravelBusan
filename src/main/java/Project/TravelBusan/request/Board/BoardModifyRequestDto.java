package Project.TravelBusan.request.Board;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardModifyRequestDto {
    private String title;
    private String content;
}
