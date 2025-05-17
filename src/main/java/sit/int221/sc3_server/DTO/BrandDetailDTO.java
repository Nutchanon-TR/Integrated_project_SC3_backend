package sit.int221.sc3_server.DTO;

import lombok.Data;

@Data
public class BrandDetailDTO {
    private int id;
    private String name;
    private String websiteUrl;
    private String countryOfOrigin;
    private boolean isIsActive;
    private int noOfSaleItems;
//    private String createdOn;
//    private String updatedOn;
    private int noOfSaleItem;
    public void setIsActive(boolean isActive){
        this.isIsActive = isActive;
    }
}
