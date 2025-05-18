package sit.int221.sc3_server.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

import java.time.Instant;


@Data
@NoArgsConstructor
public class SalesItemDetailDTO {
    private Integer id;
    private String model;
    private String brandName;
    @NotBlank(message = "Name is required and must not be blank")
    private String description;
    private Integer price;
    private Integer ramGb;
    private Integer storageGb;
    private BigDecimal screenSizeInch;
    private String color;
    @Min(0)
    @NotNull(message = "Quantity is required")
    private Integer quantity;

//    private String createdOn;
//    private String updatedOn;
    private Instant createdOn;
    private Instant updatedOn;


    public void setColor(String color) {
        if (color != null && color.trim().isEmpty()) {
            this.color = null;
        } else if (color == null || color.trim().isEmpty()) {
            this.color = null;
        } else {
            this.color = color.trim();
        }
    }
}
