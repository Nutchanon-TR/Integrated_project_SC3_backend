package sit.int221.sc3_server.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class BrandDetailDTO  {
    private Integer id;
    @NotNull
    @Size(max = 30)
    private String name;
//    @Size(max = 40)
    private String websiteUrl;
    @NotNull
    private Boolean isActive;
    @Size(max = 80)
    private String countryOfOrigin;
    private Integer noOfSaleItems;
//    @JsonIgnore
//    private Set<SalesItemDetailDTO> products;



}

