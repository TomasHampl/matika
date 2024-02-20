package cz.tomas.matikaapi.dto.requests;

import cz.tomas.matikaapi.dto.Element;
import cz.tomas.matikaapi.dto.MathOperationTypes;
import lombok.Getter;
import lombok.Setter;

/**
 * Base request body. Defines the basic elements in a request body. Meant to be extended.
 */
@Getter
@Setter
public abstract class RequestBody {

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
}
