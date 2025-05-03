package mylab.user.di.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testRegisterUserSuccess() {
        // UserService 빈이 제대로 주입되었는지 확인
        Assertions.assertNotNull(userService);
        // UserService 내의 의존성들도 제대로 주입되었는지 확인
        Assertions.assertNotNull(userService.getUserRepository());
        Assertions.assertNotNull(userService.getSecurityService());

        // UserRepository의 dbType이 XML에서 설정된 값인지 확인
        Assertions.assertEquals("MySQL", userService.getUserRepository().getDbType());

        // registerUser 메소드 실행 (성공 케이스: password가 null이나 empty가 아님)
        boolean result = userService.registerUser("testUser", "테스트 사용자", "validPassword123");

        Assertions.assertTrue(result, "사용자 등록이 성공해야 합니다.");

        System.out.println("UserService Bean: " + userService);
        System.out.println("UserRepository in UserService: " + userService.getUserRepository());
        System.out.println("SecurityService in UserService: " + userService.getSecurityService());
    }

     @Test
    void testRegisterUserFailureDueToPassword() {
        Assertions.assertNotNull(userService);

        // registerUser 메소드 실행 (실패 케이스: password가 null임)
        boolean resultNullPassword = userService.registerUser("userWithNullPwd", "널 비밀번호 사용자", null);
        Assertions.assertFalse(resultNullPassword, "비밀번호가 null이면 사용자 등록이 실패해야 합니다.");

         // registerUser 메소드 실행 (실패 케이스: password가 empty string임)
        boolean resultEmptyPassword = userService.registerUser("userWithEmptyPwd", "빈 비밀번호 사용자", "");
        Assertions.assertFalse(resultEmptyPassword, "비밀번호가 비어있으면 사용자 등록이 실패해야 합니다.");

        System.out.println("UserService Bean: " + userService);
    }
}