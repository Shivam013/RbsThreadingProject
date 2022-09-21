package com.Rbs.packages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "package")
@NamedNativeQueries(value = {
        @NamedNativeQuery(
                name = "Package.getPackagesDetailed",
                query = "select p.id as id, s.description as service_desc,s.price as service_price,p.num_paid_service as num_paid_services, " +
                        "p.num_free_service as num_free_services, p.package_expiration_months as expiration_months, " +
                        "p.package_status as package_status from package p left join service s on p.service_id = s.id ",
                resultSetMapping = "packageMap"
        )
})
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "packageMap",
        classes = {
                @ConstructorResult(targetClass = PackageMap.class,columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "service_desc", type = String.class),
                        @ColumnResult(name = "service_price", type = Integer.class),
                        @ColumnResult(name = "num_paid_services", type = Integer.class),
                        @ColumnResult(name = "num_free_services", type = Integer.class),
                        @ColumnResult(name = "expiration_months", type = Integer.class),
                        @ColumnResult(name = "package_status", type = Boolean.class)
                })
        })
})
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(name = "package_status", nullable = false)
    private Boolean packageStatus;

    @Column(name = "num_paid_service", nullable = false)
    private Integer numPaidService;

    @Column(name = "num_free_service", nullable = false)
    private Integer numFreeService;

    @Column(name = "package_expiration_months", nullable = false)
    private Integer packageExpirationMonths;
}
