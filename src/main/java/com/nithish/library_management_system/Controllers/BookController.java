package com.nithish.library_management_system.Controllers;

import com.nithish.library_management_system.Model.Book;
import com.nithish.library_management_system.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("add")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("associateAuthorAndBook")
    public ResponseEntity associateAuthorAndBook(@RequestParam("authorId") Integer authorId,
                                                 @RequestParam("bookId") Integer bookId){
        try {
            return new ResponseEntity(bookService.associateAuthorAndBook(authorId,bookId),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
