package com.geaby.aplus.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geaby.aplus.BaseTest;
import com.geaby.aplus.dto.AppointExecution;
import com.geaby.aplus.entity.Book;
import com.geaby.aplus.service.BookService;

public class BookServiceImplTest extends BaseTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testAppoint() throws Exception {
		long bookId = 1001;
		long studentId = 12345678910L;
		AppointExecution execution = bookService.appoint(bookId, studentId);
		System.out.println(execution);
	}
	
	@Test
	public void testGetById() throws Exception {
		long bookId = 1001;
		System.out.println("11111");
		Book book1 = bookService.getById(bookId);
		System.out.println(book1.getName());
		System.out.println("22222");
		Book book2 = bookService.getById(bookId);
		System.out.println(book2.getName());
	}

}
