package sit.int221.sc3_server.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class BranByIdDTO {
    private int id;
    private String name;
    private String websiteUrl;
    private Boolean isActive;
    private String countryOfOrigin;
    private ZonedDateTime createdOn;
    private ZonedDateTime updatedOn;
}
