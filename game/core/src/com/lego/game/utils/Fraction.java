package com.lego.game.utils;

import java.math.BigInteger;

/**
 * Created by sargis on 5/1/15.
 */
final public class Fraction extends Number {
    private final BigInteger gcd;
    private final int canonicalNumerator;
    private final int canonicalDenominator;
    private BigInteger numerator;
    private BigInteger denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
        gcd = this.numerator.gcd(this.denominator);
        canonicalNumerator = this.numerator.intValue() / gcd.intValue();
        canonicalDenominator = this.denominator.intValue() / gcd.intValue();
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public String getCanonical() {
        return canonicalNumerator + "|" + canonicalDenominator;
    }

    public int getNumerator() {
        return this.numerator.intValue();
    }

    public int getDenominator() {
        return this.denominator.intValue();
    }

    public byte byteValue() {
        return (byte) this.doubleValue();
    }

    public double doubleValue() {
        return ((double) numerator.intValue()) / ((double) denominator.intValue());
    }

    public float floatValue() {
        return (float) this.doubleValue();
    }

    public int intValue() {
        return (int) this.doubleValue();
    }

    public long longValue() {
        return (long) this.doubleValue();
    }

    public short shortValue() {
        return (short) this.doubleValue();
    }

    public boolean equals(Fraction frac) {
        return this.compareTo(frac) == 0;
    }

    @Override
    public int hashCode() {
        return canonicalNumerator * canonicalDenominator;
    }

    public int compareTo(Fraction frac) {
        long t = this.getNumerator() * frac.getDenominator();
        long f = frac.getNumerator() * this.getDenominator();
        int result = 0;
        if (t > f) {
            result = 1;
        } else if (f > t) {
            result = -1;
        }
        return result;
    }
}
