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

    @Test
    void zeroDenominator() {
        try {
            new Fraction(1, 0);
            Assertions.fail("How did you create a Fraction with a 0 denominator?!");
        } catch (IllegalArgumentException expected) {
        }
    }

    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            if (denominator == 0) throw new IllegalArgumentException("Denominator must not be 0.");
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(
                    this.numerator * that.denominator + this.denominator * that.numerator,
                    this.denominator * that.denominator);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator == that.numerator * this.denominator;
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
