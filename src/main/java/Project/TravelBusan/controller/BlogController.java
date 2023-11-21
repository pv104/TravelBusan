package Project.TravelBusan.controller;

import Project.TravelBusan.request.Blog.BlogSaveRequestDto;
import Project.TravelBusan.response.Blog.BlogDetailResponseDto;
import Project.TravelBusan.response.Blog.BlogListResponseDto;
import Project.TravelBusan.response.Blog.BlogSaveResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<BlogSaveResponseDto> blogAdd(@RequestPart String title,
                                                    @RequestPart String content,
                                                    @RequestPart(value = "img") MultipartFile image) throws IOException {
        BlogSaveRequestDto blogSaveRequestDto = new BlogSaveRequestDto();
        blogSaveRequestDto.setContent(content);
        blogSaveRequestDto.setTitle(title);
        return blogService.addBlog(blogSaveRequestDto, image);
    }

    @GetMapping
    public ResponseDto<List<BlogListResponseDto>> blogList() {
        return blogService.listBlog();
    }

    @GetMapping("/{blog-id}")
    public ResponseDto<BlogDetailResponseDto> blogDetails(@PathVariable("blog-id") Long blogId) {
        return blogService.detailBlog(blogId);
    }

    @DeleteMapping("/{blog-id}")
    public ResponseDto<Void> blogRemove(@PathVariable("blog-id") Long blogId) {
        return blogService.removeBlog(blogId);
    }

    @GetMapping("/get-image-url")
    public ResponseDto<Map<String, String>> getImageUrl() {
        // 이미지 URL을 생성 또는 데이터베이스에서 가져와서 전달
        String imageUrl = "/Users/moon/Projects/TravelBusan/src/main/resources/static/img/84f24d80-b09a-491a-a0da-0c9ae7fe752e_IMG_1515.JPG";

        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);

        return ResponseDto.success("이미지 조회",response);
    }
}

/*
    formData.append("blogSaveRequestDto",JSON.stringify({title: title,content: content,}));
 */
