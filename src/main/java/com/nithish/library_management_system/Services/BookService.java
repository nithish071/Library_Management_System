package com.nithish.library_management_system.Services;

import com.nithish.library_management_system.Model.Author;
import com.nithish.library_management_system.Model.Book;
import com.nithish.library_management_system.Repositories.AuthorRepository;
import com.nithish.library_management_system.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book){
        book = bookRepository.save(book);
        return "Book has been saved with bookId: "+ book.getBookId();
    }

    public String associateAuthorAndBook(Integer authorId,Integer bookId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw new Exception("Entered authorId is invalid");
        }
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new Exception("Entered bookId is invalid");
        }

        Author author = optionalAuthor.get();
        Book book = optionalBook.get();

        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);
        bookRepository.save(book);
        authorRepository.save(author);
        return "Book and author have been associated";
    }
}
