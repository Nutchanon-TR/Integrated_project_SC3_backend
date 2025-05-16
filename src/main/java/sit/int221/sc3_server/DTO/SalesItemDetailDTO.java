package sit.int221.sc3_server.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;


@Data

public class SalesItemDetailDTO {
    private int id;
    private String model;
    private String brandName;
    @NotBlank(message = "Name is required and must not be blank")
    private String description;
    private Integer price;
    private Integer ramGb;
    private Double screenSizeInch;

    @Min(0)
    @NotNull(message = "Quantity is required")
    private int quantity;

    private Integer storageGb;
    private String color;


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
