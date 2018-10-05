package com.bets.mgr.entity;

import com.bets.mgr.model.MatchResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "MGR_MATCHES")
public class Match implements Serializable {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public int getRelLocal() {
        return relLocal;
    }

    public void setRelLocal(int relLocal) {
        this.relLocal = relLocal;
    }

    public int getRelVisitor() {
        return relVisitor;
    }

    public void setRelVisitor(int relVisitor) {
        this.relVisitor = relVisitor;
    }

    public int getRelDraw() {
        return relDraw;
    }

    public void setRelDraw(int relDraw) {
        this.relDraw = relDraw;
    }

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }
}







