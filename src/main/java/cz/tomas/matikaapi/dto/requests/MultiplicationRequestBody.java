package cz.tomas.matikaapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiplicationRequestBody extends RequestBody {

    @Override
    public long getMaxResult(){
        if(maxResult == 0){
            return 100;
        }
        return maxResult;
    }
}
