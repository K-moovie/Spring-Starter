package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // MemoryMemberRepository class에 대한 TestCase 작성
    // 각 TestCase는 순서에 종속적이면 안된다.
    // 따라서, 각 TestCase가 종료될 때 마다 메모리를 비운다.
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach Method");
        repository.clearStore();
    }

    @Test
    public void save() {
        // Member 객체 생성
        Member member = new Member();
        member.setName("spring");

        // save 메서드 실행
        repository.save(member);

        // save 메서드 후 검색
        Member result = repository.findById(member.getId()).get();
        // 1.
        //System.out.println("result = " + (result == member));
        // 2. assertEquals(기댓 값, 실제 값)
        //Assertions.assertEquals(member, result);
        // 3.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
