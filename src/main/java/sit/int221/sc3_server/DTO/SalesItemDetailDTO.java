package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sc3_server.entity.Product;

import java.time.Instant;

@Getter
@Setter

public class SalesItemDetailDTO {
    private int id;
    private String model;
    private String brandName;
    private String description;
    private Integer price;
    private Integer ramGb;
    private Double screenSizeInch;
    private Integer quantity;
    private Integer storageGb;
    private String color;
//    private Instant createdOn;
//    private Instant updatedOn;

    public void setModel(String model) {
        this.model = model.trim();
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName.trim();
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity == null || quantity < 0  ? 1: quantity;
    }
    public void setColor(String color){
        this.color = color != null ? color.trim():null;
    }

}
