package cz.tomas.matikaapi.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Jacksonized
@Builder
@JsonPropertyOrder(alphabetic = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MathTasks {

    @JsonProperty("Number of tasks")
    private Integer numberOfTasks;
    @JsonProperty("Max result")
    private Long maxResultValue;
    @JsonProperty("Tasks")
    private List<MathTask> mathTaskList;
    @JsonProperty("Type of math operation")
    private MathOperationTypes operationType;
    @JsonProperty("Error")
    private String errorMessage;
}
