package practice.core.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import practice.core.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        //xml으로 하는 방법 테스트
        MemberService memeberService = ac.getBean("memberService",MemberService.class);
        assertThat(memeberService).isInstanceOf(MemberService.class);
    }
}
