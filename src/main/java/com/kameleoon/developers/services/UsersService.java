package com.kameleoon.developers.services;

import com.kameleoon.developers.entities.Quote;
import com.kameleoon.developers.entities.User;
import com.kameleoon.developers.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findUserById(long id) {
        Optional<User> foundUser = usersRepository.findById(id);

        return foundUser.orElse(null);
    }

    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    public void addQuoteToUser(User user, Quote quote) {
        List<Quote> quotes = user.getQuotes();

        if (quotes == null) {
            quotes = new ArrayList<>();
        }

        quotes.add(quote);
        quote.setUser(user);
    }
}