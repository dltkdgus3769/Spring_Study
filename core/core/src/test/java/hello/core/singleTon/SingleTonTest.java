package hello.core.singleTon;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingleTonService singleTonService = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();
        System.out.println("singleTonService = " + singleTonService);
        System.out.println("singleTonService2 = " + singleTonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService).isSameAs(memberService2);
    }


}

