package com.nithish.library_management_system.Controllers;

import com.nithish.library_management_system.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("issueBook")
    public ResponseEntity issueBook(@RequestParam("cardId") Integer cardId,
                                    @RequestParam("bookId") Integer bookId){
        try {
            return new ResponseEntity(transactionService.issueBook(cardId,bookId),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("returnBook")
    public ResponseEntity returnBook(@RequestParam("cardId") Integer cardId,
                                    @RequestParam("bookId") Integer bookId){
        try {
            return new ResponseEntity(transactionService.returnBook(cardId,bookId),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
