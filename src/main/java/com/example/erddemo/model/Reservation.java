package com.example.erddemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class represents a single Reservation
 * @author Filip Kollar
 *
 * overlap in time on the same court means equality
 */
@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence")
    @Column(name = "id")
    private Long reservationID;

    @Column(name = "court_id", nullable = false)
    private Long courtID;

    @Column(name = "client_phone", nullable = false)
    private String clientPhone;

    @Column(name = "time_from", nullable = false)
    private Date from;

    @Column(name = "time_to", nullable = false)
    private Date to;
    @Column(name = "players", nullable = false)
    private int players;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "court")
    private Court court;

    public void setCourt(Court court) {
        this.court = court;
    }


    public Reservation(ReservationRequest request) {
        this.courtID = request.getCourtID();
        this.clientPhone = request.getPhone();
        this.to = request.getTo();
        this.from = request.getFrom();
        this.players = request.getPlayers();
        if (this.to.compareTo(this.from) <= 0) {
            throw new IllegalArgumentException("Wrong time interval");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Reservation res = (Reservation) obj;

        return this.courtID.equals(res.courtID) && (this.from.before(res.to) && res.from.before(this.to));
    }

    @Override
    public int hashCode() {
        return courtID.hashCode();
    }

    @JsonIgnore
    public Double getPrice() {
        return ((this.to.getTime() - this.from.getTime()) / 60000) * court.getPrice() * (players == 2 ? 1 : 1.5);
    }
}
