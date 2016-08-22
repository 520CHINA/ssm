package com.soecode.lyf;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.soecode.lyf.entity.Book;
import com.soecode.lyf.service.BookService;
import com.soecode.lyf.web.BookController;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BaseTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController = new BookController();
	
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }
	
	@Test
	public void testDetail() throws Exception {
		
		when(bookService.getById(1003)).thenReturn(new Book(1003,"编译原理",10));

        mockMvc.perform(get("/book/1003/detail"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(is("编译原理")));

        verify(bookService).getById(1003);
	}
}
