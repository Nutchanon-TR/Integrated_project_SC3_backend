package sit.int221.sc3_server.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class SalesItemAllDataDTO {
    private int id;
    private String model;
//    @JsonIgnore
//    private BrandDTO brandDTO;
    private String brandName;
    @NotBlank(message = "Name is required and must not be blank")
    private String description;
    private int price;
    private Integer ramGb;
    private BigDecimal screenSizeInch;
    @Min(0)
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    private Integer storageGb;
    private String color;
    private Instant createdOn;
    private Instant updatedOn;
//    private String createdOn;
//    private String updatedOn;


//    private String getBrandName(){
//        return brandDTO.getName();
//    }

}
