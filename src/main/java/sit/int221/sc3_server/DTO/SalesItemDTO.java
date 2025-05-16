package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesItemDTO {
    private int id;
    private String model;
    private String brandName;
    private int price;
    private Integer storageGb;
    private Integer ramGb;
    private String color;
}