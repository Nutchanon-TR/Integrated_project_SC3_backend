package sit.int221.sc3_server.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SaleItemCreateDTO {
    @NotEmpty
    private String model;
    @NotNull
    private BrandDTO brand;
    @NotEmpty(message = "Name is required and must not be Empty")
    private String description;
    @NotNull
    private int price;
    private Integer ramGb;
    private BigDecimal screenSizeInch;
    @Min(0)
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    private Integer storageGb;
    private String color;

    public void setQuantity(Integer quantity) {
        if (quantity == null || quantity < 0) {
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }
    }

    public void setColor(String color) {
        if (color != null && color.trim().isEmpty()) {
            this.color = null;
        } else if (color == null || color.trim().isEmpty()) {
            this.color = null;
        } else {
            this.color = color.trim();
        }
    }

    public void setModel(String model) {
        this.model = model != null ? model.trim() : null;
    }

    public void setDescription(String description) {
        this.description = (description != null && !description.trim().isEmpty()) ? description.trim() : null;
    }
}
