package com.kameleoon.developers.controllers;

import com.kameleoon.developers.entities.Quote;
import com.kameleoon.developers.services.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuotesController {
    private final QuotesService quotesService;

    @Autowired
    public QuotesController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping("/quotes")
    public List<Quote> findAllQuotes() {
        return quotesService.findAllQuotes();
    }

    @GetMapping("/quotes/{id}")
    public Quote findQuoteById(@PathVariable long id) {
        return quotesService.findQuoteById(id);
    }

    @GetMapping("/quotes/random")
    public Quote findRandomQuote() {
        return quotesService.findRandomQuote();
    }

    @GetMapping("/quotes/top10")
    public List<Quote> findTop10Quotes() {
        return quotesService.findTop10Quotes();
    }

    @GetMapping("/quotes/worse10")
    public List<Quote> findWorse10Quotes() {
        return quotesService.findWorse10Quotes();
    }

    @PostMapping("/quotes/{userId}")
    public Quote createQuote(@RequestBody Quote quote, @PathVariable long userId) {
        quotesService.saveQuote(quote, userId);

        return quote;
    }

    @PatchMapping("/quotes/{id}")
    public Quote updateQuote(@RequestBody Quote quote, @PathVariable long id) {
        return quotesService.updateQuote(id, quote);
    }

    @DeleteMapping("/quotes/{id}")
    public void deleteQuoteById(@PathVariable long id) {
        quotesService.deleteQuoteById(id);
    }

    @GetMapping("/quotes/upvote{id}")
    public Quote upvoteQuoteById(@PathVariable long id) {
        return quotesService.upvoteOrDownvoteQuoteById(id, 1);
    }

    @GetMapping("/quotes/downvote{id}")
    public Quote downvoteQuoteById(@PathVariable long id) {
        return quotesService.upvoteOrDownvoteQuoteById(id, -1);
    }
}