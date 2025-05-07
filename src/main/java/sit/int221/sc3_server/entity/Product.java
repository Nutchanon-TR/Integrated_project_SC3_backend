package sit.int221.sc3_server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import sit.int221.sc3_server.entity.Brand;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;


    @Size(max = 60)
    @NotNull
    @Column(name = "model", nullable = false, length = 60)
    private String model;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "screenSizeInch", precision = 3, scale = 1)
    private BigDecimal screenSizeInch;

    @Column(name = "ramGb")
    private Integer ramGb;

    @Column(name = "storageGb")
    private Integer storageGb;

    @Size(max = 60)
    @Column(name = "color", length = 60)
    private String color;

    @NotNull
    @Column(name = "createdOn", nullable = false)
    private Instant createdOn;

    @NotNull
    @Column(name = "updatedOn", nullable = false)
    private Instant updatedOn;

//    @PrePersist
//    public void onCreate() {
//        this.createdOn = Instant.now();
//        this.updatedOn = Instant.now();
//    }
//
//    @PreUpdate
//    public void onUpdate() {
//        this.updatedOn = Instant.now();
//    }
}