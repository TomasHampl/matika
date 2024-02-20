package cz.tomas.matikaapi.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.OptionalLong;
import java.util.Random;

@Component
@Slf4j
public class NumberGenerator {

    private SecureRandom random;

    /**
     * Provides a pseudo-random long. Uses {@link Random#longs(long, long)} under the hood.
     * @param min is a lower limit of the range from which the random long is generated
     * @param max is an upper limit of the range from which the random long is generated
     * @return a new long that is 'random' (what is truly random in life, eh?)
     */
    public long getRandomLong(long min, long max){
        try {
            OptionalLong optionalLong = getRandom().longs(min, max)
                    .findFirst();
            if(optionalLong.isPresent()){
                return optionalLong.getAsLong();
            }
        } catch (IllegalArgumentException iae){
            log.error("Method received invalid arguments. Min value: {}, Max value: {}", min, max);
        }
        return 0;
    }

    private Random getRandom(){
        if(this.random == null){
            this.random = new SecureRandom();
        }
        return this.random;
    }

}
