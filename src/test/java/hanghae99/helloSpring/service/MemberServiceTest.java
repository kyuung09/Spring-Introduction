package hanghae99.helloSpring.service;

import hanghae99.helloSpring.domain.Member;
import hanghae99.helloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //    MemberService memberService = new MemberService();
    MemberService memberService;


    //    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    // 각 테스트 시작 전에 실행함
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // MemoryMemberRepository를 만들어서 memberRepository에 넣어놓고
        memberService = new MemberService(memberRepository);
        // MemberService에 있는 memberRepository와 연결함
        // 그럼 같은 메모리의 같은 레포지토리를 사용함
        // 이런걸 DI 라고
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given : 무언가가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        //when : 이걸 실행했을떄
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야함 !
        Member findMember = memberService.findOne(saveId).get();

//        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); optin + enter 해서 static으로 넘김
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();          // Swift + F9
        member2.setName("spring");

        //when
        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));         // 이 분을 메세지로 보려면 Control + option + V
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // memberService.join(member2)를 하면, IllegalStateException이 터져야함
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}