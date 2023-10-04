package Project.TravelBusan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloController {
    @GetMapping("/hello")
    ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("test ok");
    }
}
