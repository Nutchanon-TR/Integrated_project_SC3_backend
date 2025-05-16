package sit.int221.sc3_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;
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

    @Size(max = 30)
    @NotNull
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Size(max = 40)
    @Column(name = "webSiteUrl", length = 40)
    private String websiteUrl;

    @NotNull
    @Column(name = "isActive", nullable = false)
    private Boolean isActive = false;

    @Size(max = 80)
    @Column(name = "countryOfOrigin", length = 80)
    private String countryOfOrigin;

    @NotNull
    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @NotNull
    @Column(name = "updatedOn", nullable = false)
    private LocalDateTime updatedOn;

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