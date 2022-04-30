package com.example.isw.BookManagement;

import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.Category;
import com.example.isw.BookManagement.model.Issue;
import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





import java.util.List;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookManagementApplicationTests {
	@Autowired
	ValidationService validationService;


	@Autowired
	private TestRestTemplate template;

	@Autowired
	MemberService memberService;

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	IssueService issueService;



	@Test
	void contextLoads() {
	}

	@Test
	void testValidEmail(){

		String email1 = "test";
		Assertions.assertEquals("Wrong email format!",validationService.validateEmail(email1));

		String email2 = "test@";
		Assertions.assertEquals("Wrong email format!",validationService.validateEmail(email2));

		String email3 = "test@test";
		Assertions.assertEquals("Wrong email format!",validationService.validateEmail(email3));

		String email4 = "@test.com";
		Assertions.assertEquals("Wrong email format!",validationService.validateEmail(email4));

		String email5 = "test@test.com";
		Assertions.assertEquals("",validationService.validateEmail(email5));

	}


	@Test
	void testMemberService(){
		List<Member> memberList = memberService.getAllMembers();
		Assertions.assertTrue(memberList.size() > 0);
	}

	@Test
	void testIssueService(){
		List<Issue> issueList = issueService.getAllIssues();
		Assertions.assertTrue(issueList.size() > 0);
	}

	@Test
	void testCategoryService(){
		List<Category> categoryList = categoryService.getAllCategories();
		Assertions.assertTrue(categoryList.size() > 0);
	}

	@Test
	void testBookService(){
		List<Book> bookList = bookService.getAllBooks();
		Assertions.assertTrue(bookList.size() > 0);
	}



	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		ResponseEntity<String> result = template.withBasicAuth("admin", "admin")
				.getForEntity("http://localhost:8080/login", String.class);
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
