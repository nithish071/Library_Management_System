package com.nithish.library_management_system.Services;

import com.nithish.library_management_system.Enum.CardStatus;
import com.nithish.library_management_system.Model.LibraryCard;
import com.nithish.library_management_system.Model.Student;
import com.nithish.library_management_system.Repositories.CardRepository;
import com.nithish.library_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String addNewCard(){
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardStatus(CardStatus.NEW);
        libraryCard.setNoOfBooksIssued(0);
        libraryCard = cardRepository.save(libraryCard);
        return "The card has been generated with the cardId "+libraryCard.getCardId();
    }

    public String associateCardAndStudent(Integer cardId, Integer studentId){
        LibraryCard libraryCard = cardRepository.findById(cardId).get();
        Student student = studentRepository.findById(studentId).get();
        libraryCard.setStudent(student);
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        cardRepository.save(libraryCard);
        return "Associating card and Student with cardId "+cardId+" and studentId "+studentId;
    }
}
