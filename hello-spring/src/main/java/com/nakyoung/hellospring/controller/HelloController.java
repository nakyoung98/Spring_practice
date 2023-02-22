package com.nakyoung.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//spring은 @Controrller 노테이션을 적어야함
@Controller
public class HelloController {

    /**
     * GetMapping Annotation은 web에서 /hello페이지에 진입하면 아래의 함수를 호출한다는 표시
     * hello.html 파일에서
     * ${data}에 model.addAttribute에 넘겨진 attributeName "data"의 attributeValue "hello!!"가 올라감
     * **/
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");

        // 아래의 return "hello"는 hello.html을 찾아가 렌더링하라는 것
        return "hello";
    }

    /**
     * required = false의 경우에는 해당 param을 안념겨도 된다는 뜻
     * **/
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, @RequestParam("age") String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello-template";
    }


    //HTTP의 BODY부에 해당 데이터를 직접 넣겠다라는 뜻
    //페이지 소스보기 시 html데이터가 아니라 그냥 hello name만 덩그러니 들어있음을 알 수 있음
    //물론 이렇게만 쓸일은 별로..
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        return "hello " + name; //hello spring 등으로 바뀔 것 뷰가 없이 해당 문자가 그대로 내려감
    }


    //api를 쓰는 진짜 이유
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        //객체가 json 형식으로 반환됨
        //클래스의 멤버변수 값들이 key-value 방식으로 전달되는 듯
        //html은 무겁지만 json은 가볍고 심플함
       return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
