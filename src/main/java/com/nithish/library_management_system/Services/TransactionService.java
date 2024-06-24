package com.nithish.library_management_system.Services;

import com.nithish.library_management_system.Enum.TransactionStatus;
import com.nithish.library_management_system.Model.Book;
import com.nithish.library_management_system.Model.LibraryCard;
import com.nithish.library_management_system.Model.Transaction;
import com.nithish.library_management_system.Repositories.BookRepository;
import com.nithish.library_management_system.Repositories.CardRepository;
import com.nithish.library_management_system.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public String issueBook(int cardId,int bookId) throws Exception{
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();
        Transaction transaction = new Transaction();

        // FAILURE: if book is issued
        if(book.getIsIssued()){
            throw new Exception("Book is already issued");
        }
        // FAILURE: noOfBooksIssued in card has reached the Limit
        if(card.getNoOfBooksIssued()==3){
            throw new Exception("Card Book issue Limit is reached");
        }
        //SUCCESS
        // set transaction success
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setBook(book);
        transaction.setCard(card);
        // set book issued true
        book.setIsIssued(true);
        // set card limit
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);
        bookRepository.save(book);
        cardRepository.save(card);
        transactionRepository.save(transaction);
        return "The transaction is saved with the transactionId "+transaction.getTransactionId();
    }

    public String returnBook(Integer cardId, Integer bookId) throws Exception {
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();


        // FAILURE
        // if returned book which is not even issued
        if(!book.getIsIssued()){
            throw new Exception("Unauthorized!! Book is not issued to this card");
        }
        // Calculate fine Amount if any
        Transaction txn = transactionRepository.findByCardAndBookAndTransactionStatus(card,book,TransactionStatus.PENDING);
        if(txn == null){
            throw new Exception("Book and card has not been associated Or the book is already returned");
        }
        LocalDate issueDate = txn.getIssueDate();
        LocalDate todayDate = LocalDate.now();
        txn.setReturnDate(todayDate);
        long noOfDaysDifference = ChronoUnit.DAYS.between(issueDate,todayDate);
        String str = "";

        if(noOfDaysDifference > 10){
            txn.setFineAmt((int)(noOfDaysDifference - 10)*2);
            str = "Return date expired!! You have been fined with amount: "+(int)(noOfDaysDifference - 10)*2+"\nPlease pay the fine!! Thanks \n";
        }

        //SUCCESS
        // set transaction success
        txn.setTransactionStatus(TransactionStatus.SUCCESS);
        // set book issued true
        book.setIsIssued(false);
        // set card limit
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);
        txn.setBook(book);
        txn.setCard(card);
        bookRepository.save(book);
        cardRepository.save(card);
        transactionRepository.save(txn);
        return str+"Book is returned successfully";
    }
}
