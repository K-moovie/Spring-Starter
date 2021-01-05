package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // View Resolver 대신 HttpMessageConverter가 동작
    // 기본 문자처리: StringHttpMessageConverter
    // 기본 객체처리: MappingJackson2HttpMessageConverter
    // REF. 클라이언트의 HTTP Accept 헤더와 서버의 Controller 반환 타입 정보 들을 조합해서 HttpMessageConverter가 결정된다.

    // 기본 문자처리 방식: String 형식으로 반환
    @GetMapping("hello-string")
    // HTTP Body부에 직접 값을 넣는다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 기본 객체 처리 방식: JSON 방식으로 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 객체를 반환하고 @ResponseBody
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
