package cz.tomas.matikaapi.configuration;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "matika")
@Component
@Data
public class AppConfiguration {

    private List<MathOperationTypes> availableTypes;

    public List<MathOperationTypes> getAvailableTaskTypes(){
        if(availableTypes == null || availableTypes.isEmpty()){
            availableTypes = new ArrayList<>();
            availableTypes.add(MathOperationTypes.ADDITION);
        }
        return availableTypes;
    }
}
