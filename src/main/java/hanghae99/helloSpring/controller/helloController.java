package hanghae99.helloSpring.controller;

import hanghae99.helloSpring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.ServerEndpoint;

@Controller
public class helloController {

    // 웹어플리케이션에서 /hello라고 들어오면 호출해줌
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-templates";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // http의 헤더 body부분에 이 데이터를 직접 넣어주겠다~ 라는 뜻임
    public String helloMvc(@RequestParam("name") String name){
        return "hello" + name; // hello + spring(name)
        // 뷰(html) 없이 데이터를 그대로 내려주는 형식
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
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
