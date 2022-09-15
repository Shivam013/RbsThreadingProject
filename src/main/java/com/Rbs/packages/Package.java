package com.Rbs.packages;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package")
public class Package {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "customer_id", nullable = false)
    private UUID customerID;
    @Column(name = "services_left", nullable = false)
    private Integer servicesLeft;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    public Package(Integer price, String description, UUID customerID, Integer servicesLeft, LocalDate creationDate){
        this.price = price;
        this.description = description;
        this.customerID = customerID;
        this.servicesLeft = servicesLeft;
        this.creationDate = creationDate;
    }

}
