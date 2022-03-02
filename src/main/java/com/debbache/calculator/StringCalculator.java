package com.debbache.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Stream;

public class StringCalculator {
    private static final Function<String, String> DELIMITER = expression -> Optional.of(expression)
            .filter(s -> s.matches("^//\\W\n.*"))
            .map(s -> s.replaceFirst(".*//(.*?)\n.*", "$1"))
            .orElse("(,|\n)");

    public static int add(String numbers) {
        var negativeNumbers = new ArrayList<Integer>();

        var sum = Optional.ofNullable(numbers)
                .filter(number -> !number.isEmpty())
                .stream()
                .map(number -> number.split(DELIMITER.apply(numbers)))
                .flatMap(Stream::of)
                .map(String::trim)
                .filter(s -> s.matches("(-?[0-9]*)"))
                .mapToInt(Integer::valueOf)
                .peek(n -> cacheNegativeNumbers(negativeNumbers, n))
                .sum();

        checkForNegativeNumbers(negativeNumbers);
        return sum;
    }

    private static void cacheNegativeNumbers(List<Integer> negativeNumbers, int number) {
        if (number < 0) {
            negativeNumbers.add(number);
        }
    }

    private static void checkForNegativeNumbers(List<Integer> negativeNumbers) {
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException(String.format("negatives not allowed %s", negativeNumbers));
        }
    }

}
