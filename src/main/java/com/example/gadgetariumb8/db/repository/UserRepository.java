package com.example.gadgetariumb8.db.repository;

import com.example.gadgetariumb8.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}