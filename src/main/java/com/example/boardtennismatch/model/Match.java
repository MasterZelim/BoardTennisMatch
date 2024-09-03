package com.example.boardtennismatch.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "playerFirst_id", nullable = false)
    private Player playerFirst;
    @ManyToOne()
    @JoinColumn(name = "playerSecond_id", nullable = false)
    private Player playerSecond;
    @ManyToOne()
    @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;



}
