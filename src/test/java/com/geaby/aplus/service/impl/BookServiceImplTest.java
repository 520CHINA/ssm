package com.geaby.aplus.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.geaby.aplus.BaseTest;
import com.geaby.aplus.dto.AppointExecution;
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

}
