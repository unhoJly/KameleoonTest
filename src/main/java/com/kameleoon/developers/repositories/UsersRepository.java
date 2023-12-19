package com.kameleoon.developers.repositories;

import com.kameleoon.developers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}