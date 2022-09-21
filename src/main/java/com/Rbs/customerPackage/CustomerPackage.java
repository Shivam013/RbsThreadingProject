package com.Rbs.customerPackage;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

//CREATE TABLE customer_package (
//        id uuid default uuid_generate_v4(),
//        customer_id uuid not null,
//        package_id int not null,
//        services_left int not null,
//        creation_date date not null,
//        expiry_date date not null,
//        primary key(id)
//        );

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "customer_package")
public class CustomerPackage {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "package_id", nullable = false)
    private int packageId;

    @Column(name = "services_left", nullable = false)
    private int servicesLeft;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    public CustomerPackage(UUID customerId, int packageId, int servicesLeft, LocalDate creationDate, LocalDate expiryDate) {
        this.customerId = customerId;
        this.packageId = packageId;
        this.servicesLeft = servicesLeft;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
    }


    public Integer usePackage(){
        if(this.servicesLeft >= 1 && !isExpired()){
            return --this.servicesLeft;
        }
        return -1;
    }
    private boolean isExpired(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth());
        return !today.minusYears(1).isBefore(this.creationDate);
    }

}
