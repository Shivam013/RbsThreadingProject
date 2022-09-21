package com.Rbs.customer;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@ToString
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birthday_day", nullable = false)
    private int day;
    @Column(name = "birthday_month", nullable = false)
    private int month;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "can_sms", nullable = false)
    private boolean canSms;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;
    @Column(name = "referal_source", nullable = true)
    private String referalSource;
    @Column(name="last_visited", nullable = false)
    private LocalDateTime lastVisited;

    public Customer(String firstName, String lastName, int day, int month, String email, String phoneNumber, boolean canSms, LocalDate creationDate, String referalSource, LocalDateTime lastVisited) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.day = day;
        this.month = month;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.canSms = canSms;
        this.creationDate = creationDate;
        this.referalSource = referalSource;
        this.lastVisited = lastVisited;
    }




}
