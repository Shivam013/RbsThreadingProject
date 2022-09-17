package com.Rbs.packages;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    PackageType type;
    @Column(name = "customer_id", nullable = false)
    private UUID customerID;
    @Column(name = "services_left", nullable = false)
    private Integer servicesLeft;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    public Package(PackageType type, UUID customerID, Integer servicesLeft, LocalDate creationDate){
        this.type = type;
        this.customerID = customerID;
        this.servicesLeft = servicesLeft;
        this.creationDate = creationDate;
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
