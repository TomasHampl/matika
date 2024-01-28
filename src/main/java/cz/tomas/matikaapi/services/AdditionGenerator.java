package cz.tomas.matikaapi.services;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import cz.tomas.matikaapi.dto.MathTask;
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
    public MathTasks buildTasks(MathOperationTypes mathOperationType, int numberOfTasks, long maxAdditionResult){
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

    private long getFirstNumber(long maxAdditionResult){
        return numberGenerator.getRandomLong(1, maxAdditionResult);
    }

    private long getSecondNumber(long maxAdditionResult, long firstNumber){
        long maxPermittedMaxValue = maxAdditionResult - firstNumber;
        if(maxPermittedMaxValue == 1){
            return 1;
        }
        return numberGenerator.getRandomLong(1, maxAdditionResult - firstNumber);
    }


}
