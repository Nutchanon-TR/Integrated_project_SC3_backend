package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sc3_server.entity.Product;

@Getter
@Setter

public class SalesItemDetailDTO {
    private int id;
    private String model;
    private String brandName;
    private String description;
    private int price;
    private Integer ramGb;
    private Double screenSizeInch;
    private int quantity;
    private Integer storageGb;
    private String color;

}
