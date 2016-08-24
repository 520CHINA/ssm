package com.geaby.aplus.web;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geaby.aplus.dto.AppointExecution;
import com.geaby.aplus.dto.Result;
import com.geaby.aplus.entity.Book;
import com.geaby.aplus.service.BookService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;



@Controller
@RequestMapping("/book/")
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET , produces = {"application/json; charset=utf-8"})
	@ResponseBody
	private String list(Model model) {
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list";// WEB-INF/jsp/"list".jsp
	}
	
	
	@RequestMapping(value = "/test/v1", method = RequestMethod.GET)
	@ResponseBody
	private String list1(Model model) {
		return "test1";
	}
	
	
	@RequestMapping(value = "/test/v2", method = RequestMethod.GET)
	@ResponseBody
	private String list2(Model model) {
		return "list2";
	}

	// ajax json
	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET , produces = {"application/json; charset=utf-8"} )
	@ResponseBody
	@ApiOperation(value = "根据id获取图书信息", httpMethod = "GET", response = String.class, notes = "根据id获取图书信息")
	private String detail(@ApiParam(required = true, name = "bookId", value = "书id") @PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		logger.info("获取图书信息:" + book.getName());
		//JSONObject json = JSONObject.fromObject(model);
		System.out.print(book.getName());
		return book.getName();
	}

	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {"application/json; charset=utf-8" })
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @Param("studentId") Long studentId) {
		if (studentId == null || studentId.equals("")) {
			return new Result<>(false, "学号不能为空");
		}
		AppointExecution execution = bookService.appoint(bookId, studentId);
		return new Result<AppointExecution>(true, execution);
	}

}
