package com.kameleoon.developers.services;

import com.kameleoon.developers.entities.Vote;
import com.kameleoon.developers.repositories.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VotesService {
    private final VotesRepository votesRepository;

    @Autowired
    public VotesService(VotesRepository votesRepository) {
        this.votesRepository = votesRepository;
    }

    @Transactional(readOnly = true)
    public Vote getVote(long id) {
        Optional<Vote> foundVote = votesRepository.findById(id);

        return foundVote.orElse(null);
    }

    @Transactional
    public void saveVote(Vote vote) {
        votesRepository.save(vote);
    }
}