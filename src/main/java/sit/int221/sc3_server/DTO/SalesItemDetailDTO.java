package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SalesItemDetailDTO {
    private int id;
    private String model;
    private String brandName;
    private String description;
    private int price;
    private int ramGb;
    private double screenSizeInch;
    private int quantity;
    private int storageGb;
    private String color;
}
