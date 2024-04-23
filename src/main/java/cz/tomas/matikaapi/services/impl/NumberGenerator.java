package cz.tomas.matikaapi.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;

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
        if(isNotPrime(interim)){
            return interim;
        }
        return getRandomNonPrimeLong(min,max);
    }

    private boolean isNotPrime(long number){
        if(number < 2){
            return false;
        }

        for (int i = 2; i < number; i++) {
            if(number % i == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Provides a list of possible 'divisors' - numbers that can divide the provided {@code number}. Uses {@code brute force}
     * approach - goes through numbers that are greater than 1 but smaller than provided max value and tests if
     * they're divisible without any remainder.
     * @param number is the number that we want to find possible division values from
     * @param maxValue is the max value we want to find
     * @return a {@link List} of {@link Long}s that represents the group of numbers that meet the provided criteria
     */
    private List<Long> getPossibleDivisors(long number, long maxValue){
        List<Long> longList = new ArrayList<>();

        if(!isNotPrime(number)){
            return longList;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0){
                longList.add((long) i);
            }
            if (i >= maxValue) {
                break;
            }
        }
        return longList;
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
     * @param max is a max value of the random number
     * @return new long that should serve as number that you can use to divide the firstNumber with
     */
    public long getSecondDivision(long firstNumber, long max){
        List<Long> possibleDivisors = getPossibleDivisors(firstNumber, max);
        if(possibleDivisors.isEmpty()){
            throw new IllegalArgumentException("There are no elements to choose random divisor from...");
        }

        return possibleDivisors.get((int) getRandom().nextLong(possibleDivisors.size()));
    }

}
