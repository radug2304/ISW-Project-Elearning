package com.example.isw.BookManagement.restcontroller;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.Issue;
import com.example.isw.BookManagement.model.IssuedBook;
import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.service.BookService;
import com.example.isw.BookManagement.service.IssueService;
import com.example.isw.BookManagement.service.IssuedBookService;
import com.example.isw.BookManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/issue")
public class IssueRestController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private IssuedBookService issuedBookService;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> payload) {
		
		String memberIdStr = (String)payload.get("member");
		String[] bookIdsStr = payload.get("books").toString().split(",");
		
		Integer memberId = null;
		List<Integer> bookIds = new ArrayList<Integer>();
		try {
			memberId = Integer.parseInt( memberIdStr );
			for(int ind=0 ; ind<bookIdsStr.length ; ind++) {
				bookIds.add( Integer.parseInt(bookIdsStr[ind]) );
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}
		
		Member member = memberService.getMemberById( memberId );
		List<Book> books = bookService.getAllBooksById(bookIds);
		
		Issue issue = new Issue();
		issue.setMember(member);
		issue = issueService.addNewIssue(issue);
		
		for( int ind=0 ; ind<books.size() ; ind++ ) {
			Book book = books.get(ind);
			book.setStatus( Constants.BOOK_ISSUED );
			book = bookService.saveBook(book);
			
			IssuedBook ib = new IssuedBook();
			ib.setBook( book );
			ib.setIssue( issue );
			issuedBookService.addNewIssuedBook( ib );
			
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String returnAll(@PathVariable(name = "id") Integer id) {
		Issue issue = issueService.getIssueById(id);
		if( issue != null ) {
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int ind=0 ; ind<issuedBooks.size() ; ind++ ) {
				IssuedBook ib = issuedBooks.get(ind);
				ib.setReturned( Constants.BOOK_RETURNED );
				issuedBookService.saveIssuedBook( ib );
				
				Book book = ib.getBook();
				book.setStatus( Constants.BOOK_AVAILABLE );
				bookService.saveBook(book);
			}
			
			issue.setReturned( Constants.BOOK_RETURNED );
			issueService.saveIssue(issue);
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Integer id) {
		Issue issue = issueService.getIssueById(id);
		String[] issuedBookIds = payload.get("ids").split(",");
		if( issue != null ) {
			
			List<IssuedBook> issuedBooks = issue.getIssuedBooks();
			for( int ind=0 ; ind<issuedBooks.size() ; ind++ ) {
				IssuedBook ib = issuedBooks.get(ind);
				if( Arrays.asList(issuedBookIds).contains( ib.getId().toString() ) ) {
					ib.setReturned( Constants.BOOK_RETURNED );
					issuedBookService.saveIssuedBook( ib );
					
					Book book = ib.getBook();
					book.setStatus( Constants.BOOK_AVAILABLE );
					bookService.saveBook(book);
				}
			}
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
}
