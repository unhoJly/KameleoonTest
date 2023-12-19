package com.kameleoon.developers.services;

import com.kameleoon.developers.entities.Quote;
import com.kameleoon.developers.entities.User;
import com.kameleoon.developers.entities.Vote;
import com.kameleoon.developers.repositories.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuotesService {
    private final QuotesRepository quotesRepository;
    private final UsersService usersService;

    @Autowired
    public QuotesService(QuotesRepository quotesRepository, UsersService usersService) {
        this.quotesRepository = quotesRepository;
        this.usersService = usersService;
    }

    public List<Quote> findAllQuotes() {
        return quotesRepository.findAll();
    }

    public Quote findQuoteById(long id) {
        Optional<Quote> foundQuote = quotesRepository.findById(id);

        return foundQuote.orElse(null);
    }

    public Quote findRandomQuote() {
        return quotesRepository.findRandomQuote();
    }

    public List<Quote> findTop10Quotes() {
        return quotesRepository.findFirst10Quotes();
    }

    public List<Quote> findWorse10Quotes() {
        return quotesRepository.findLast10Quotes();
    }

    @Transactional
    public void saveQuote(Quote quote, long userId) {
        Timestamp date = new Timestamp(new Date().getTime());
        Vote vote = new Vote();
        User user = usersService.findUserById(userId);
        vote.setScore(0);
        vote.setGraph("0=" + date);
        quote.setDate(date);
        quote.setVote(vote);
        usersService.addQuoteToUser(user, quote);
        usersService.saveUser(user);
        quotesRepository.save(quote);
    }

    @Transactional
    public Quote updateQuote(long id, Quote quote) {
        Quote quoteToUpdate = findQuoteById(id);
        quoteToUpdate.setContent(quote.getContent());
        quoteToUpdate.setDate(new Timestamp(new Date().getTime()));
        quotesRepository.save(quoteToUpdate);

        return quoteToUpdate;
    }

    @Transactional
    public void deleteQuoteById(long id) {
        quotesRepository.deleteById(id);
    }

    public Quote upvoteOrDownvoteQuoteById(long id, int vote) {
        Quote quote = findQuoteById(id);
        long newScore = quote.getVote().getScore() + vote;
        quote.getVote().setScore(newScore);
        StringBuilder sb = new StringBuilder();
        String newGraph = sb
                .append(quote.getVote().getGraph())
                .append(";")
                .append(newScore)
                .append("=")
                .append(new Timestamp(new Date().getTime()))
                .toString();
        quote.getVote().setGraph(newGraph);

        return quote;
    }
}