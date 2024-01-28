package cz.tomas.matikaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
public class MathTasks {

    @JsonProperty("Number of tasks")
    private int numberOfTasks;
    @JsonProperty("Max result")
    private long maxResultValue;
    @JsonProperty("Tasks")
    private List<MathTask> mathTaskList;
    @JsonProperty("Type of math operation")
    private MathOperationTypes operationType;
}
