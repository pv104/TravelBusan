package Project.TravelBusan.request;

import Project.TravelBusan.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardModifyRequestDto {
    private String title;
    private String content;
}
