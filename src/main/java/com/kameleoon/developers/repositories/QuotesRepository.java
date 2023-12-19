package com.kameleoon.developers.repositories;

import com.kameleoon.developers.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM 'quotes' ORDER BY RAND() LIMIT 1")
    Quote findRandomQuote();

    @Query(nativeQuery = true, value = "SELECT * FROM 'quotes' ORDER BY 'id' LIMIT 10")
    List<Quote> findFirst10Quotes();

    @Query(nativeQuery = true, value = "SELECT * FROM 'quotes' ORDER BY 'id' DESC LIMIT 10")
    List<Quote> findLast10Quotes();
}