package Project.TravelBusan.service;

import Project.TravelBusan.domain.Blog;
import Project.TravelBusan.domain.BoardComment;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.repository.BlogRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.Blog.BlogSaveRequestDto;
import Project.TravelBusan.response.Blog.BlogDetailResponseDto;
import Project.TravelBusan.response.Blog.BlogListResponseDto;
import Project.TravelBusan.response.Blog.BlogSaveResponseDto;
import Project.TravelBusan.response.Blog.BlogSimplelResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.Sights.SightsListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * 블로그 작성
     */
    @Transactional
    public ResponseDto<BlogSaveResponseDto> addBlog(BlogSaveRequestDto blogSaveRequestDto, MultipartFile image) throws IOException {
        String projectPath = System.getProperty("user.dir") // 프로젝트 경로
                + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "img";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + image.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        image.transferTo(saveFile);

        User user = userRepository.findByIdOrElseThrow(userService.getMyUserWithAuthorities().getId());

        blogSaveRequestDto.updateCreateBy(1L, 0L, user);

        Blog blog = Blog.builder()
                .title(blogSaveRequestDto.getTitle())
                .content(blogSaveRequestDto.getContent())
                .nickname(blogSaveRequestDto.getUser().getNickname())
                .img("/files/" + fileName)
                .visit(blogSaveRequestDto.getVisit())
                .likeCount(blogSaveRequestDto.getLikeCount())
                .user(blogSaveRequestDto.getUser())
                .build();

        blogRepository.save(blog);

        return ResponseDto.success("블로그 작성", new BlogSaveResponseDto(blog));
    }

    /**
     * 블로그 전체 리스트
     */
    public ResponseDto<List<BlogListResponseDto>> listBlog() {
        List<Blog> blog = blogRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BlogListResponseDto> blogListResponseDto = blog.stream()
                .map(o -> BlogListResponseDto.builder()
                        .id(o.getId())
                        .title(o.getTitle())
                        .content(o.getContent())
                        .nickname(o.getNickname())
                        .visit(o.getVisit())
                        .likeCount(o.getLikeCount())
                        .creDate(o.getCreDate())
                        .img(o.getImg())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.success("블로그 전체 조회", blogListResponseDto);
    }

    public ResponseDto<BlogDetailResponseDto> detailBlog(Long blogId) {
        Blog blog = blogRepository.findByBlogOrElseThrow(blogId);
        return ResponseDto.success("블로그 상세 조회", new BlogDetailResponseDto(blog));
    }

    @Transactional
    public ResponseDto<Void> removeBlog(Long blogId) {
        Blog blog = blogRepository.findByBlogOrElseThrow(blogId);
        User user = userRepository.findByIdOrElseThrow(userService.getMyUserWithAuthorities().getId());

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("해당 블로그 작성자가 아닙니다.");
        }

        blogRepository.delete(blog);
        return ResponseDto.success("블로그 삭제 성공",null);
    }

}
