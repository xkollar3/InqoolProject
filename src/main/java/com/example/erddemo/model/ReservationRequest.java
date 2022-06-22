package com.example.erddemo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

/**
 * Class for representing Full reservation requests
 *
 * @author Filip Kollar
 */
@Builder
@Getter
@ToString
public class ReservationRequest {
    private final String phone;
    private final String name;
    private final Long courtID;
    private final Date from;
    private final Date to;
    private final int players;
}
