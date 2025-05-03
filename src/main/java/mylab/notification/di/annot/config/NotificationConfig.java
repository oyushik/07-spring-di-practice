package mylab.notification.di.annot.config;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.NotificationService;
import mylab.notification.di.annot.SmsNotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
// @ComponentScan(basePackages = {"mylab.notification.di.annot"}) // @Bean으로 직접 빈을 등록하므로 ComponentScan은 필요 없습니다.
public class NotificationConfig {

    @Bean("emailServiceBean")
    @Qualifier("emailService")
    public NotificationService emailService() {
        // EmailNotificationService 객체를 생성하고 필요한 생성자 인자를 전달합니다.
        return new EmailNotificationService("smtp.example.com", 587);
    }

    @Bean("smsServiceBean")
    @Qualifier("smsService")
    public NotificationService smsService() {
        // SmsNotificationService 객체를 생성하고 필요한 생성자 인자를 전달합니다.
        return new SmsNotificationService("TelecomCorp");
    }

    // NotificationManager는 EmailNotificationService와 SmsNotificationService에 의존합니다.
    // Spring은 이 메서드의 매개변수 타입을 보고 컨테이너에서 해당하는 빈을 찾아 주입합니다.
    // 동일 타입(NotificationService)의 빈이 여러 개 있으므로, @Qualifier를 사용하여 어떤 빈을 주입할지 명시합니다.
    @Bean
    public NotificationManager notificationManager(@Qualifier("emailServiceBean") NotificationService emailService,
                                                   @Qualifier("smsServiceBean") NotificationService smsService) {
        // 주입받은 EmailNotificationService와 SmsNotificationService를 사용하여 NotificationManager 객체를 생성합니다.
        return new NotificationManager(emailService, smsService);
    }
}