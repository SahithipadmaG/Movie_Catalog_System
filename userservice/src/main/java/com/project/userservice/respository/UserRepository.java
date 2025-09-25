package com.project.userservice.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.project.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA provides basic CRUD operations
	List<User> findAll();
    // Custom query method
    User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);



	boolean existsById(Long id);

	void deleteById(Long id);
}