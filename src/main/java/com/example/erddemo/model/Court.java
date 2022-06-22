package com.example.erddemo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

/**
 * Class used to represent a Court entity
 * id defines equality
 *
 * @author Filip Kollar
 */
@Entity
@Table(name = "courts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Court {
    @Id
    @SequenceGenerator(name = "court_sequence", sequenceName = "court_sequence", allocationSize = 1)
    @GeneratedValue(generator = "court_sequence", strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "court")
    private Set<Reservation> court_reservations;

    public Court(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        return ((Court) obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return (int) (31 * id);
    }
}
