package cz.tomas.matikaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AvailableMathTaskTypes {

    @JsonProperty("availableMathTasks")
    List<MathOperationTypes> types;

}
