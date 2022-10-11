package practice.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository {

    //동시성 이슈에 있을수 있음 원래는 concurrent hashmap 사용 해야함
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
