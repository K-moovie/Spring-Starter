package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring Container가 해당 객체를 생성한 후 관리
@Controller
public class MemberController {

    // Spring Container에 등록하면 해당 객체는 하나만 생성한다.
    private final MemberService memberService;

    // memberService는 Spring Container에 등록된 memberService로 주입.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
