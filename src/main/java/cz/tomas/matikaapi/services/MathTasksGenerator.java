package cz.tomas.matikaapi.services;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTasks;

public interface MathTasksGenerator {

    /**
     * Generates the math tasks. It is able to generate all types of basic arithmetic tasks (addition / subtraction /
     * multiplication / division)
     * @param mathOperationType type of {@link MathOperationTypes} that we want to get
     * @param numberOfTasks is a number of tasks
     * @param maxResult is the max result; parameter might not be applicable to all types of tasks
     * @return new instance of {@link MathTasks} object populated with the generated tasks
     */
    MathTasks buildTasks(MathOperationTypes mathOperationType, int numberOfTasks, long maxResult);

}
