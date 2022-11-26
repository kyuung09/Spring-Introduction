package hanghae99.helloSpring.service;

import hanghae99.helloSpring.domain.Member;
import hanghae99.helloSpring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //  private final MemberRepository memberRepository = new MemoryMemberRepository();  해당 부분을 아랫줄 12~15줄 처럼 바꾼다.
    // 변경하는 이유는, MemberService에 있는 memberRepository값과, MemoryMemberRepositoryTest의 new로 선언된 부분은 실제로는 다른 부분을 참조하고 있는것이기 때문임

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;               // 외부에서 넣어주도록 변경한다.
    }


    /**
     * 회원가입
     **/
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원이 있으면 안된다.

//        Optional<Member> result = memberRepository.findByName(member.getName());          // 수정 전 코드
//        result.ifPresent( m -> {}

        validateDuplicateMember(member);                // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())                              // findName을 하면 그 결과는 옵셔널 결과니까, 바로 아래줄으로 이동해서
                .ifPresent(m -> {                                                 // 옵셔널 멤버.ifPresent = > 멤버에 값이 있으면 "이미 존재하는 회원입니다."이라는 동작을 함
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
