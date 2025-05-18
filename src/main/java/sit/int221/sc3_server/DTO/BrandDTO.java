package sit.int221.sc3_server.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDTO {
    private int id;
    private String name;

    public void setName(String name){
        this.name = name.trim();
    }
}
