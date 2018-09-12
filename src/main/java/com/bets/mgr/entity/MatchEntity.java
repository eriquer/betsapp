package com.bets.mgr.entity;

import com.bets.mgr.model.MatchResult;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MGR_MATCHES")
public class MatchEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "local", nullable = false)
    private String local;

    @NotEmpty
    @Column(name = "visitor", nullable = false)
    private String visitor;

    @Column(name = "rel_local", nullable = false)
    private int relLocal;

    @Column(name = "rel_visitor", nullable = false)
    private int relVisitor;

    @Column(name = "rel_draw", nullable = false)
    private int relDraw;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "open", nullable = false)
    private LocalDateTime open;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "close", nullable = false)
    private LocalDateTime close;

    @Enumerated(EnumType.ORDINAL)
    private MatchResult result;
}







