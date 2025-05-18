package sit.int221.sc3_server.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandDTO {
    @NotBlank(message = "name is required")
    private String Name;
    private String websiteUrl;
    private String countryOfOrigin;
    private Boolean isActive;

    public void setName(String name) {
        this.Name = name != null ? name.trim() : null;
    }
}
