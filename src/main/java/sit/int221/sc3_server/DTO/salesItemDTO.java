package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class salesItemDTO {
    private int id;
    private String model;
    private String brandName;
    private int price;
    private Integer ramGb;
    private Integer storageGb;
    private String color;
}
