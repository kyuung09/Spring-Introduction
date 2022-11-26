package hanghae99.helloSpring.repository;

import hanghae99.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // save하면 회원 정보가 저장소에 저장이 됨
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // 저장 후 findById, findByName으로 id,Name을 찾을 수 있음
    List<Member> findAll();
    // 지금까지 저장된 모든 회원 리스트를 리턴해줌


}
