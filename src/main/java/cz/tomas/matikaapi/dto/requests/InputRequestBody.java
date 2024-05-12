package cz.tomas.matikaapi.dto.requests;

import cz.tomas.matikaapi.dto.Element;
import cz.tomas.matikaapi.dto.MathOperationTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

/**
 * Base request body. Defines the basic elements in a request body.
 */
@Getter
@Setter
@Builder
@Jacksonized
public class InputRequestBody {

    protected MathOperationTypes taskType;
    protected Element firstValue;
    protected Element secondValue;
    protected int tasks;
    protected long maxResult;

    public int getTasks(){
        if(tasks == 0){
            return 20;
        }
        return tasks;
    }

    public long getMaxResult() {
        if(maxResult == 0) {
            return 100;
        }
        return maxResult;
    }

}
