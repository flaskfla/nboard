package com.example.board.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; //웹 api 테스프 할때 사용

    @Test
    public void hellosReturned() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //hello 주소로 GET 요청을 함
                .andExpect(status().isOk()) // HTTP Header의 status를 검증
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증 hello가 맞는지
    }

    @Test
    public void helloDtoIsReturned() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))    //값은 String만 허용 되므로 변경해줘야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    //jsonPath : JSON 응답값을 필드별로 검증할수있음 ($.필드명)
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
