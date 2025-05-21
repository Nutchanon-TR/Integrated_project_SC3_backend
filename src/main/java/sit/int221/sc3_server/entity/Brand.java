package sit.int221.sc3_server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

//    @Size(max = 30)
    @NotNull
//    @Column(name = "name", nullable = false, length = 30)
    private String name;

//    @Size(max = 40)
//    @Column(name = "webSiteUrl", length = 40)
    private String webSiteUrl;

    @NotNull
    @Column(name = "isActive", nullable = false)
    private Boolean isActive = false;

    @Size(max = 80)
    @Column(name = "countryOfOrigin", length = 80)
    private String countryOfOrigin;


    @CreationTimestamp
    @Column(name = "createdOn", nullable = false, updatable = false)
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updatedOn", nullable = false)
    private Instant updatedOn;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new LinkedHashSet<>();

//    @CreationTimestamp
//    @Column(name = "createdOn", nullable = false, updatable = false)
//    private Timestamp createdOn;
//
//    @UpdateTimestamp
//    @Column(name = "updatedOn", nullable = false)
//    private Timestamp updatedOn;
}