package com.debbache.calculator;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    @Test
    public void string_calculator_should_return_0_if_string_is_null(){
        //Given
        String numbers = null;
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(0);
    }

    @Test
    public void string_calculator_should_return_0_if_string_is_empty(){
        //Given
        String numbers = "";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(0);
    }

    @Test
    public void string_calculator_should_return_the_number_if_string_contains_a_valid_number(){
        //Given
        String numbers = "1";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(1);
    }

    @Test
    public void string_calculator_should_return_the_sum_if_string_contains_two_numbers_separated_by_coma(){
        //Given
        String numbers = "1,2";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(3);
    }

    @Test
    public void string_calculator_should_handle_an_unknown_amount_of_numbers(){
        //Given
        String numbers = "1,2,3,4,5";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(15);
    }

    @Test
    public void string_calculator_should_handle_a_new_line_as_delimiter(){
        //Given
        String numbers = "1\n2,3";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(6);
    }

    @Test
    public void string_calculator_should_accept_a_custom_delimiter(){
        //Given
        String numbers = "//;\n1;2";
        //When
        var sum = StringCalculator.add(numbers);
        //Then
        Assertions.assertThat(sum).isEqualTo(3);
    }

    @Test
    public void string_calculator_should_throw_an_exception_if_numbers_contains_a_negative_number(){
        //Given
        String numbers = "1,2,-3";
        //Then
        Assertions.assertThatThrownBy(() -> StringCalculator.add(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("negatives not allowed [-3]");
    }

    @Test
    public void string_calculator_should_show_all_negative_numbers_in_thrown_exception_if_numbers_contains_several_negative_numbers(){
        //Given
        String numbers = "1,2,-3,-5,-1000";
        //Then
        Assertions.assertThatThrownBy(() -> StringCalculator.add(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("negatives not allowed [-3, -5, -1000]");
    }

}