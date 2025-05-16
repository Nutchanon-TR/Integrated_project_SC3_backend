package sit.int221.sc3_server.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.int221.sc3_server.DTO.SalesItemAllDataDTO;
import sit.int221.sc3_server.entity.Product;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Product.class, SalesItemAllDataDTO.class)
                .addMapping(src -> src.getBrand().getName(), SalesItemAllDataDTO::setBrandName);


//        modelMapper.getConfiguration()
//                .setSkipNullEnabled(true)
//                .setAmbiguityIgnored(true);;

        return modelMapper;
    }
}