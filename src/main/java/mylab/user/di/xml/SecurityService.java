package mylab.user.di.xml;

import org.springframework.stereotype.Service; // @Service 추가

@Service // 이 클래스를 스프링 빈으로 자동 등록하도록 지정
public class SecurityService {

    public boolean authenticate(String userId, String password) {
        System.out.println("인증: " + userId);
        return password != null && !password.isEmpty();
    }

    public boolean authorize(String userId, String resource) {
        System.out.println("권한 부여: " + userId + " for " + resource);
        return true;
    }
}