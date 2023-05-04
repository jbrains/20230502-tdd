package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assertions.assertEquals(0, sum.intValue());
    }

    @Test
    void notZeroPlusZero() {
        Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assertions.assertEquals(4, sum.intValue());
    }

    @Test
    void zeroPlusNotZero() {
        Fraction sum = new Fraction(0).plus(new Fraction(7));
        Assertions.assertEquals(7, sum.intValue());
    }

    @Test
    void notZeroPlusNotZero() {
        Fraction sum = new Fraction(3).plus(new Fraction(9));
        Assertions.assertEquals(12, sum.intValue());
    }

    @Test
    void nonIntegerFractions() {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));

        Assertions.assertEquals(3, sum.getNumerator());
        Assertions.assertEquals(5, sum.getDenominator());
    }

    public static class Fraction {
        private int numerator;
        private int denominator;
        private int integerValue;

        public Fraction(int integerValue) {
            this(integerValue, 1);
            this.integerValue = integerValue;
        }

        public Fraction(int numerator, int denominator) {
            this.integerValue = numerator;
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            if (denominator != 1 && this.denominator == that.denominator)
                return new Fraction(this.numerator + that.numerator, this.denominator);
            else
                return new Fraction(this.integerValue + that.integerValue);
        }

        public int intValue() {
            return integerValue;
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }
    }
}
