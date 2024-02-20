package cz.tomas.matikaapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple domain transfer object whose purpose is to store the 'addition' tasks
 */
@Getter
@Setter
public class AdditionRequestBody extends RequestBody {

    @Override
    public long getMaxResult(){
        if(maxResult == 0){
            return firstValue.getMaxValue() + secondValue.getMaxValue();
        }
        return maxResult;
    }

}
