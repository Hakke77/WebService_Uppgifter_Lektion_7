package com.abdilhakim.WebService_Uppgifter_Lektion_7.repository;

import com.abdilhakim.WebService_Uppgifter_Lektion_7.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Student> findByEmail(String email);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

}