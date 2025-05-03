package mylab.user.di.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // UserRepository 의존성을 스프링으로부터 주입받도록 지정
    // UserRepository는 XML에 정의되어 있고, SecurityService는 @Service로 스캔됩니다.
    // @Autowired는 타입 기반으로 매칭하여 주입해줍니다.
    @Autowired
    private UserRepository userRepository;

    // SecurityService 의존성을 스프링으로부터 주입받도록 지정
    @Autowired
    private SecurityService securityService;

    // 기본 생성자는 @Autowired 사용 시 필수는 아니지만 유지해도 좋습니다.
    public UserService() {}


    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }

    public boolean registerUser(String userId, String name, String password) {
        // @Autowired로 주입된 securityService와 userRepository를 사용
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }
        return false;
    }
}