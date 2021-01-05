package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Spring에서 Controller Class는 @Controller가 필요하다.
@Controller
public class HelloController {

    // Web Application에서 /hello가 입력 시 해당 메소드 실행
    // Get 통신 방법
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        // Controller에서 리턴 값으로 문자를 반환하면 View Resolver가 화면을 찾아서 처리.
        // resources/templates/hello.html로 해당 로직 수행.
        // REF. spring-boot-devtools 라이브러리를 추가하면, html 파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경 가능.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
