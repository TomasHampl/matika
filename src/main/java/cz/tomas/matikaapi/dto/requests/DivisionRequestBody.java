package cz.tomas.matikaapi.dto.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DivisionRequestBody extends RequestBody{

    @Override
    public long getMaxResult(){
        if(maxResult == 0){
            return 10;
        }
        return maxResult;
    }
}
