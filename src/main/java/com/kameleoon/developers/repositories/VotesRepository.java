package com.kameleoon.developers.repositories;

import com.kameleoon.developers.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotesRepository extends JpaRepository<Vote, Long> {
}