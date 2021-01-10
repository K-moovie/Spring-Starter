package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// Memory를 사용한 저장소
// @Repository
public class MemoryMemberRepository implements MemberRepository{
    // Java 11 버전에서 선언
    // var store = new HashMap<Long, Member>();
    private static Map<Long, Member> store = new HashMap<>();

    // Key 값 생성을 위한 sequence 생성
    private  static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Java 8에서 도입된 기능으로
        // 반환 값이 null일 경우 객체를 감싸 Client 단에서 처리할 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Lambda 표현식
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
