package sit.int221.sc3_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Size(max = 40)
    @Column(name = "webSiteUrl", length = 40)
    private String webSiteUrl;

    @NotNull
    @Column(name = "isActive", nullable = false)
    private Boolean isActive = false;

    @Size(max = 80)
    @Column(name = "countryOfOrigin", length = 80)
    private String countryOfOrigin;

////    @CreatedDate
//    @CreationTimestamp
//    @Column(name = "createdOn")
//    private LocalDateTime createdOn;
//
////    @LastModifiedDate
//    @UpdateTimestamp
//    @Column(name = "updatedOn")
//    private LocalDateTime updatedOn;

//    @CreationTimestamp
//    @Column(name = "createdOn", nullable = false, updatable = false)
//    private Instant createdOn;
//
//    @UpdateTimestamp
//    @Column(name = "updatedOn", nullable = false )
//    private Instant  updatedOn;


    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private Set<Product> products = new LinkedHashSet<>();

//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "createdOn", nullable = false)
//    private Instant createdOn;
//
//    @ColumnDefault("CURRENT_TIMESTAMP")
//    @Column(name = "updatedOn", nullable = false)
//    private Instant updatedOn;
//
//    @OneToMany(mappedBy = "brand")
//    private Set<Product> saleItems = new LinkedHashSet<>();

}