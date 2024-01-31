package cz.tomas.matikaapi.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MathTaskInstructions {

    /**
     * Type of {@link MathOperationTypes}
     */
    private MathOperationTypes mathOperationType;
    /**
     * Number of tasks, defaults to 20, if not used otherwise
     */
    private int numberOfTasks;
    /**
     * Upper limit of the tasks...in case of addition & multiplication, this would be the max achievable result
     */
    private long maxLimit;
    /**
     * Maximum permitted value of the first number (in case of subtraction, max value we can start subtracting from)
     */
    private long maxFirstValue;
    /**
     * Maximum permitted value of the second number (in case of subtraction, max value we can remove from the first value)
     */
    private long maxSecondValue;
}
