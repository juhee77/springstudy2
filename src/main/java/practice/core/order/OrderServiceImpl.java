package practice.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.core.discount.DiscountPolicy;
import practice.core.discount.FixDiscountPolicy;
import practice.core.discount.RateDiscountPolicy;
import practice.core.member.Member;
import practice.core.member.MemberRepository;
import practice.core.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    /*필드 주입
    @Autowired private MemberRepository memberRepository;
    @Autowired  private DiscountPolicy discountPolicy;
    수정자 주입
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;
    */
    private final MemberRepository memberRepository;
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 할인정책 변경 (DIP 위반, OCP(변경하지 않고 확장) 위반) --> 추상 클래스와 구체 클래스 모두에의존
    //어떻게 해결? 추상(인터페이스에만 의존하게 변환)
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; //추상화에만 의존(dip에는 의존)... --> null포인트 익셉션
    // 누군가가 클라이언트인 impl에 discountpolicy의 구현객체를 대신 생성하여 넣어주면 된다.

    /*수정자 주입
    @Autowired(required = false)//선택적 변경
    public void setMemberRepository(MemberRepository memberRepository) {
        //System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        //System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */

    //생성자를 통해서 주입된다.(이 클래스는 구체적 클래스를 모른다)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
