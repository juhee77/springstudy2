package practice.core.order;

import practice.core.discount.DiscountPolicy;
import practice.core.discount.FixDiscountPolicy;
import practice.core.discount.RateDiscountPolicy;
import practice.core.member.Member;
import practice.core.member.MemberRepository;
import practice.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 할인정책 변경 (DIP 위반, OCP(변경하지 않고 확장) 위반) --> 추상 클래스와 구체 클래스 모두에의존
    //어떻게 해결? 추상(인터페이스에만 의존하게 변환)
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; //추상화에만 의존(dip에는 의존)... --> null포인트 익셉션
    // 누군가가 클라이언트인 impl에 discountpolicy의 구현객체를 대신 생성하여 넣어주면 된다.

    //생성자를 통해서 주입된다.(이 클래스는 구체적 클래스를 모른다)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
