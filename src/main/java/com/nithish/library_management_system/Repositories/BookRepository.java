package com.nithish.library_management_system.Repositories;

import com.nithish.library_management_system.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
