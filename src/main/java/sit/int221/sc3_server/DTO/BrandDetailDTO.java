package sit.int221.sc3_server.DTO;

import lombok.Data;

@Data
public class BrandDetailDTO {
    private int id;
    private String name;
    private String websiteUrl;
    private String countryOfOrigin;
    private boolean isActive;
    private int noOfSaleItems;
}
