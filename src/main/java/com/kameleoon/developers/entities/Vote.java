package com.kameleoon.developers.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Setter
@Getter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "score")
    private long score;

    @Lob
    @Column(name = "graph")
    private String graph;
}