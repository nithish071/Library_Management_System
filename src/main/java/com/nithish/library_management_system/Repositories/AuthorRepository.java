package com.nithish.library_management_system.Repositories;

import com.nithish.library_management_system.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
