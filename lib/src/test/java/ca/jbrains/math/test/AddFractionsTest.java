package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assertions.assertEquals(new Fraction(0), sum);
    }

    @Test
    void notZeroPlusZero() {
        Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assertions.assertEquals(new Fraction(4), sum);
    }

    @Test
    void zeroPlusNotZero() {
        Fraction sum = new Fraction(0).plus(new Fraction(7));
        Assertions.assertEquals(new Fraction(7), sum);
    }

    @Test
    void notZeroPlusNotZero() {
        Fraction sum = new Fraction(3).plus(new Fraction(9));
        Assertions.assertEquals(new Fraction(12), sum);
    }

    @Test
    void nonIntegerFractions() {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));
        Assertions.assertEquals(new Fraction(3, 5), sum);
    }

    @Test
    void coprimeDenominators() {
        Fraction sum = new Fraction(2, 7).plus(new Fraction(3, 4));
        Assertions.assertEquals(new Fraction(29, 28), sum);
    }

    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            if (denominator != 1 && this.denominator == that.denominator)
                return new Fraction(this.numerator + that.numerator, this.denominator);
            else if (denominator != 1 && this.denominator != that.denominator)
                return new Fraction(
                        this.numerator * that.denominator + this.denominator * that.numerator,
                        this.denominator * that.denominator);
            else
                return new Fraction(this.numerator + that.numerator);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator == that.numerator
                        && this.denominator == that.denominator;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return -762;
        }

        @Override
        public String toString() {
            return "%d/%d".formatted(numerator, denominator);
        }
    }
}
