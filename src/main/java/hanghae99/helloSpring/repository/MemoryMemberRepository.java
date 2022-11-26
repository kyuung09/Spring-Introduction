package hanghae99.helloSpring.repository;

import hanghae99.helloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null이였을때를 생각해서 Optional에 ofNullable이라는걸로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Java8의 람다식 사용
        return store.values().stream()                              // 반복문을 돌리면서 아래의 내용을 확인함
                .filter(member -> member.getName().equals(name))    // 람다식을 이용해서 member에서 파라미터로 넘어온 네임이 같은지 확인함
                .findAny();                                          // 같은경우에만 필터링, 끝까지 돌렸는데 없으면 null로 반환됨

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store의 멤버를 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
