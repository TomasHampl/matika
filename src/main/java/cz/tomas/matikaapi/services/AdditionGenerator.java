package cz.tomas.matikaapi.services;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTask;
import cz.tomas.matikaapi.dto.MathTaskInstructions;
import cz.tomas.matikaapi.dto.MathTasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple bean responsible for generating the 'addition' tasks
 */
@Component
@Slf4j
public class AdditionGenerator implements MathTasksGenerator{

    @Autowired
    public AdditionGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    NumberGenerator numberGenerator;

    @Override
    public MathTasks buildAdditionTasks(int numberOfTasks, long maxAdditionResult){
        int counter = 0;
        List<MathTask> mathTaskList = new ArrayList<>();
        while(counter < numberOfTasks){
            MathTask mathTask = buildAddition(maxAdditionResult);
            mathTaskList.add(mathTask);
            counter++;
        }
        return MathTasks.builder()
                .mathTaskList(mathTaskList)
                .maxResultValue(maxAdditionResult)
                .numberOfTasks(numberOfTasks)
                .operationType(MathOperationTypes.ADDITION)
                .build();
    }

    public MathTasks buildSubtractionTasks(int numberOfTasks, long maxFirstValue, long maxSecondValue){
        int counter = 0;
        List<MathTask> mathTaskList = new ArrayList<>();
        while(counter < numberOfTasks){
            MathTask mathTask = buildSubtraction(maxFirstValue,maxSecondValue);
            mathTaskList.add(mathTask);
            counter++;
        }
        return MathTasks.builder()
                .operationType(MathOperationTypes.SUBTRACTION)
                .numberOfTasks(numberOfTasks)
                .mathTaskList(mathTaskList)
                .build();
    }

    @Override
    public MathTasks buildTask(MathTaskInstructions instructions) {
        return switch (instructions.getMathOperationType()) {
            case ADDITION -> buildAdditionTasks(instructions.getNumberOfTasks(), instructions.getMaxLimit());
            case SUBTRACTION ->
                    buildSubtractionTasks(instructions.getNumberOfTasks(), instructions.getMaxFirstValue(), instructions.getMaxSecondValue());
            case MULTIPLICATION ->
                    buildMultiplicationTasks(
                            instructions.getNumberOfTasks(),
                            instructions.getMaxFirstValue(),
                            instructions.getMaxSecondValue(),
                            instructions.getMaxLimit());
            default -> {
                log.error("Not implemented yet");
                throw new IllegalArgumentException("Not implemented yet");
            }
        };
    }

    private MathTasks buildMultiplicationTasks(int numberOfTasks,long maxFirstValue, long maxSecondValue, long maxResult){
        List<MathTask> tasks = new ArrayList<>();
        int counter = 0;
        while (counter < numberOfTasks){
            MathTask mathTask = buildMultiplication(maxFirstValue, maxSecondValue, maxResult);
            tasks.add(mathTask);
            counter++;
        }
        return MathTasks.builder()
                .mathTaskList(tasks)
                .operationType(MathOperationTypes.MULTIPLICATION)
                .maxResultValue(maxResult)
                .build();
    }

    private MathTask buildMultiplication(long maxFirstValue, long maxSecondValue, long maxResult){
        long firstValue = numberGenerator.getRandomLong(1,maxFirstValue);
        long secondValue = numberGenerator.getRandomLong(1, maxSecondValue);
        if (firstValue * secondValue <= maxResult){
            return MathTask.builder()
                    .firstValue(firstValue)
                    .secondValue(secondValue)
                    .result(firstValue * secondValue)
                    .operationType(MathOperationTypes.MULTIPLICATION)
                    .build();
        }

        return buildMultiplication(maxFirstValue,maxSecondValue,maxResult);
    }

    private MathTask buildSubtraction(long maxFirstValue, long maxSecondValue){
        long firstValue = getFirstNumber(maxFirstValue);
        long secondValue = getSecondSubtractionNumber(firstValue, maxSecondValue);
        return MathTask.builder()
                .firstValue(firstValue)
                .secondValue(secondValue)
                .result(firstValue - secondValue)
                .operationType(MathOperationTypes.SUBTRACTION)
                .build();
    }

    private MathTask buildAddition(long maxAdditionResult){
        long firstValue = getFirstNumber(maxAdditionResult);
        long secondValue = getSecondNumber(maxAdditionResult,firstValue);
        return MathTask.builder()
                .firstValue(firstValue)
                .secondValue(secondValue)
                .operationType(MathOperationTypes.ADDITION)
                .result(firstValue + secondValue)
                .build();
    }

    private long getSecondSubtractionNumber(long firstValue, long maxSecondValue){
        if(maxSecondValue >= firstValue){
            maxSecondValue = firstValue -1;
        }
        return numberGenerator.getRandomLong(1,maxSecondValue);
    }

    private long getFirstNumber(long maxValue){
        return numberGenerator.getRandomLong(1, maxValue);
    }

    private long getSecondNumber(long maxResult, long firstNumber){
        long maxPermittedMaxValue = maxResult - firstNumber;
        if(maxPermittedMaxValue == 1){
            return 1;
        }
        return numberGenerator.getRandomLong(1, maxResult - firstNumber);
    }


}
