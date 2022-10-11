package practice.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import practice.core.member.Member;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        ///호출 안된다.관리 안되는것 //메소드 호출 자체가 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ //nullaber있으면 호출은 된다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public  void setNoBean3(Optional<Member> noBean3){ //java8의 optional
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
