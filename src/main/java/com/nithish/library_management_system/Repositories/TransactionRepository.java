package com.nithish.library_management_system.Repositories;

import com.nithish.library_management_system.Enum.TransactionStatus;
import com.nithish.library_management_system.Model.Book;
import com.nithish.library_management_system.Model.LibraryCard;
import com.nithish.library_management_system.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    public Transaction findByCardAndBookAndTransactionStatus(LibraryCard card, Book book, TransactionStatus transactionStatus);
}
