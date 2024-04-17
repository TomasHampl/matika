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
            log.error(String.valueOf(iae.getCause()));
        }
        return 0;
    }

    /**
     * Returns the random number between the min & max values, that is not a prime number (is divisible by 2 without remainder)
     * @param min is a long representing the lower limit
     * @param max is a long representing the upper limit
     * @return a long that is a prime number
     */
    public long getRandomNonPrimeLong(long min, long max){
        long interim = getRandomLong(min,max);
        if(interim % 2 == 0){
            return interim;
        }
        return getRandomNonPrimeLong(min,max);
    }

    private Random getRandom(){
        if(this.random == null){
            this.random = new SecureRandom();
        }
        return this.random;
    }

    /**
     * Returns the random number, that meets the criteria that it is between the min & max number and that
     * the 'first number' (provided as parameter) is divisible by this randomly generated number without any remainder.
     * @param firstNumber is the first number that we try the 'divisible' test
     * @param min is a min value of the random number
     * @param max is a max value of the random number
     * @return new long that should serve as number that you can use to divide the firstNumber with
     */
    public long getSecondDivision(long firstNumber,long min, long max){
        long interim = getRandomLong(min,max);
        if(interim > 0 && interim < firstNumber && firstNumber % interim == 0){
            return interim;
        }
        return getSecondDivision(firstNumber,min,max);
    }

}
