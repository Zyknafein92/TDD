package test;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


//For numbers 1 through 100,
//
//        if the number is divisible by 3 print Fizz;
//        if the number is divisible by 5 print Buzz;
//        if the number is divisible by 3 and 5 (15) print FizzBuzz;
//        else, print the number.

public class FizzBuzz {

    @Test
    public void giveNumber_returnNumber () {
        assertEquals("1", getFizzBuzz(1));
    }

    @Test
    public void giveDivisibleBy3_returnFizz() {
        assertEquals("Fizz", getFizzBuzz(3));
    }

    @Test
    public void giveDivisibleBy5_returnBuzz(){
        assertEquals("Buzz", getFizzBuzz(5) );
    }

    @Test
    public void giveDivisibleBy3And5_returnFizzBuzz(){
        assertEquals("FizzBuzz", getFizzBuzz(15));
    }

    private String getFizzBuzz(int number) {
        if(number % 3==0 && number % 5==0){
            return "FizzBuzz";
        }else if(number % 3==0) {
            return "Fizz";
        }else if(number % 5 ==0) {
            return "Buzz";
        }

        return "" + number;
    }

}
