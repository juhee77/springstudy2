package practice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.core.discount.DiscountPolicy;
import practice.core.discount.FixDiscountPolicy;
import practice.core.discount.RateDiscountPolicy;
import practice.core.member.MemberRepository;
import practice.core.member.MemberService;
import practice.core.member.MemberServiceImpl;
import practice.core.member.MemoryMemberRepository;
import practice.core.order.OrderService;
import practice.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
    //마치 공연 기획자
    //실제 동작에 필요한 구현 객체를 연결 // 그 이후 생성자를 통해서 주입(연결) 해준다.

    @Bean
    public MemberService memberService() {
        //System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    //리팩토링을 통해서 각 역할은 구체화 할 수 있다.
    @Bean
    public OrderService orderService() {
        //System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    //할인 정책 변경시에 discount policy만 수정하면 된다.
    @Bean
    public DiscountPolicy discountPolicy() {
//       return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
