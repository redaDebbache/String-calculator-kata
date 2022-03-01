package com.debbache.calculator;

import java.util.Optional;
import java.util.function.*;
import java.util.stream.Stream;

public class StringCalculator {
    private static final Function<String, String> DELIMITER = expression -> Optional.of(expression)
            .filter(s -> s.matches("^//\\W\n.*"))
            .map(s -> s.replaceFirst(".*//(.*?)\n.*", "$1"))
            .orElse("(,|\n)");

    private static final IntConsumer NEGATIVE_NUMBER_CHECKER = number -> Optional.of(number).filter(n -> n >= 0).orElseThrow(() -> new IllegalArgumentException("negatives not allowed"));

    public static int add(String numbers) {
        return Optional.ofNullable(numbers)
                .filter(number -> !number.isEmpty())
                .stream()
                .map(number -> number.split(DELIMITER.apply(numbers)))
                .flatMap(Stream::of)
                .map(String::trim)
                .filter(s -> s.matches("(-?[0-9]*)"))
                .mapToInt(Integer::valueOf)
                .peek(NEGATIVE_NUMBER_CHECKER)
                .sum();
    }

}
