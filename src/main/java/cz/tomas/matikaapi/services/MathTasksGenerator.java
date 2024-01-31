package cz.tomas.matikaapi.services;

import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;

public interface MathTasksGenerator {

    /**
     * Generates the math tasks. It is able to generate all types of basic arithmetic tasks (addition / subtraction /
     * multiplication / division)
     *
     * @param numberOfTasks is a number of tasks
     * @param maxResult     is the max result; parameter might not be applicable to all types of tasks
     * @return new instance of {@link MathTasks} object populated with the generated tasks
     */
    MathTasks buildAdditionTasks(int numberOfTasks, long maxResult);

    /**
     * Generates the math tasks. It is able to generate all types of basic calculation tasks (addition / subtraction /
     * multiplication / division). Similar to {@link MathTasksGenerator#buildAdditionTasks(int, long)}, but those values are
     * wrapped in {@link MathTaskInstructions} object
     * @param instructions is an instance of {@code MathTaskInstructions} that controls how are the tasks generated
     * @return new instance of {@link MathTasks} object that we expect to return to calling code (usually http request)
     */
    MathTasks buildTask(MathTaskInstructions instructions);

}
