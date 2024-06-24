package com.nithish.library_management_system.Repositories;

import com.nithish.library_management_system.Model.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
