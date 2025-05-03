package mylab.notification.di.annot.config;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.NotificationService;
import mylab.notification.di.annot.SmsNotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class, loader = AnnotationConfigContextLoader.class)
// 테스트에 사용할 Spring 컨텍스트의 설정을 지정합니다.
// loader 속성은 classes를 사용할 때 종종 생략 가능하지만, 명시적으로 AnnotationConfigContextLoader를 지정합니다.
public class NotificationConfigTest {

    // @Autowired 어노테이션을 사용하여 Spring 컨텍스트로부터 NotificationManager 빈을 주입받습니다.
    // NotificationConfig에서 @Bean 메서드로 등록한 NotificationManager 객체가 여기에 주입됩니다.
    @Autowired
    private NotificationManager notificationManager;

    // @Autowired와 @Qualifier를 사용하여 Spring 컨텍스트로부터 "emailServiceBean" 빈을 주입받습니다.
    @Autowired
    @Qualifier("emailServiceBean") // NotificationConfig의 @Bean 메서드 이름 또는 @Qualifier 값 사용
    private NotificationService emailServiceBean;

    @Autowired
    @Qualifier("smsServiceBean")
    private NotificationService smsServiceBean;

    @Test
    public void testNotificationManagerNotNull() {
        assertNotNull(notificationManager, "NotificationManager should be created and injected.");
        System.out.println("NotificationManager 주입 확인 완료.");
    }

    // NotificationManager 내부에 주입된 emailService가 올바른 타입(EmailNotificationService)인지 확인합니다.
    @Test
    public void testEmailServiceInjectedCorrectly() {
        // notificationManager에서 getEmailService() 메서드를 통해 주입된 EmailService를 가져옵니다.
        NotificationService emailService = notificationManager.getEmailService();
        
        assertNotNull(emailService, "EmailService should be injected into NotificationManager.");
        
        // 가져온 emailService 객체가 EmailNotificationService 클래스의 인스턴스인지 확인합니다.
        assertTrue(emailService instanceof EmailNotificationService, "Injected emailService should be an instance of EmailNotificationService.");

        // 또한, 테스트 클래스에 직접 @Autowired로 주입받은 emailServiceBean과 동일한 객체인지도 확인합니다.
        // Spring은 기본적으로 싱글톤 빈을 생성하므로, 같은 빈을 여러 곳에 주입하면 동일한 객체 인스턴스가 주입됩니다.
        assertSame(emailServiceBean, emailService, "Autowired emailServiceBean should be the same instance as the one in NotificationManager.");

        // 추가적으로 EmailNotificationService의 속성이 설정 클래스에서 설정한 값과 일치하는지 확인합니다.
        EmailNotificationService emailServiceImpl = (EmailNotificationService) emailService;
        assertEquals("smtp.example.com", emailServiceImpl.getSmtpServer(), "EmailService SMTP server should match configuration.");
        assertEquals(587, emailServiceImpl.getPort(), "EmailService port should match configuration.");

        System.out.println("EmailService 주입 및 타입/속성 확인 완료.");
    }

    // NotificationManager 내부에 주입된 smsService가 올바른 타입(SmsNotificationService)인지 확인합니다.
    @Test
    public void testSmsServiceInjectedCorrectly() {
        // notificationManager에서 getSmsService() 메서드를 통해 주입된 SmsService를 가져옵니다.
        NotificationService smsService = notificationManager.getSmsService();

        assertNotNull(smsService, "SmsService should be injected into NotificationManager.");
        // 가져온 smsService 객체가 SmsNotificationService 클래스의 인스턴스인지 확인합니다.
        assertTrue(smsService instanceof SmsNotificationService, "Injected smsService should be an instance of SmsNotificationService.");

        // 또한, 테스트 클래스에 직접 @Autowired로 주입받은 smsServiceBean과 동일한 객체인지도 확인합니다.
        assertSame(smsServiceBean, smsService, "Autowired smsServiceBean should be the same instance as the one in NotificationManager.");

        // 추가적으로 SmsNotificationService의 속성이 설정 클래스에서 설정한 값과 일치하는지 확인합니다.
        SmsNotificationService smsServiceImpl = (SmsNotificationService) smsService;
        assertEquals("TelecomCorp", smsServiceImpl.getProvider(), "SmsService provider should match configuration.");

        System.out.println("SmsService 주입 및 타입/속성 확인 완료.");
    }

    // NotificationManager의 메서드를 실행하여 서비스 호출이 잘 되는지 확인하는 테스트입니다.
    @Test
    public void testSendNotifications() {
        System.out.println("\n알림 전송 메서드 실행 테스트 시작:");
        notificationManager.sendNotificationByEmail("테스트 이메일 메시지");
        notificationManager.sendNotificationBySms("테스트 SMS 메시지");
        System.out.println("알림 전송 메서드 실행 테스트 완료.");
    }
}