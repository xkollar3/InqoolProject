package com.example.erddemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Class used to store client data, only exists to avoid storing
 * name many times over for the same phone number
 * Phone number uniquely defines it
 * @author Filip Kollar
 */
@Table(name = "clients")
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Client implements Serializable {
    @Id
    @Column(name = "phone")
    private String phone;

    @Column(name = "full_name")
    private String full_name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "clientPhone")
    private Set<Reservation> reservationSet;

    public Client(ReservationRequest req) {
        this.phone = req.getPhone();
        this.full_name = req.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Client client = (Client) obj;

        return this.phone.equals(client.phone);
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }
}
