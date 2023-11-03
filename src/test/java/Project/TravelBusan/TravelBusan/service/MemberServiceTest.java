package Project.TravelBusan.TravelBusan.service;

import Project.TravelBusan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

/*    @Test
    @Transactional
    public void 회원가입_(){
        //given
        User User = User.builder()
                .username("test")
                .password("123")
                .email("aaa")
                .build();

        //when
        Long UserId = UserService.join(User);

        //then
        User result = UserService.findById(UserId);
        Assertions.assertEquals(User, result);
    }*/
}