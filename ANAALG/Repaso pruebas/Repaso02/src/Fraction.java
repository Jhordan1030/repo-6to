
import java.math.BigInteger;
import java.util.Objects;

    public class Fraction {
        private BigInteger numerator;
        private BigInteger denominator;

        // Constructor
        public Fraction(BigInteger num, BigInteger den) {
            if (den.equals(BigInteger.ZERO)) {
                throw new IllegalArgumentException("Denominator cannot be zero.");
            }

            // Handle sign: denominator should always be positive
            if (den.compareTo(BigInteger.ZERO) < 0) {
                num = num.negate();
                den = den.negate();
            }

            // Simplify the fraction
            BigInteger commonDivisor = num.gcd(den);
            this.numerator = num.divide(commonDivisor);
            this.denominator = den.divide(commonDivisor);
        }

        public Fraction(long num, long den) {
            this(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }

        // --- Getters ---
        public BigInteger getNumerator() {
            return numerator;
        }

        public BigInteger getDenominator() {
            return denominator;
        }

        // --- Arithmetic Operations ---
        public Fraction add(Fraction other) {
            BigInteger newNumerator = this.numerator.multiply(other.denominator)
                    .add(other.numerator.multiply(this.denominator));
            BigInteger newDenominator = this.denominator.multiply(other.denominator);
            return new Fraction(newNumerator, newDenominator); // Constructor handles simplification
        }

        public Fraction multiply(Fraction other) {
            BigInteger newNumerator = this.numerator.multiply(other.numerator);
            BigInteger newDenominator = this.denominator.multiply(other.denominator);
            return new Fraction(newNumerator, newDenominator); // Constructor handles simplification
        }

        // --- Utility Methods ---
        @Override
        public String toString() {
            if (denominator.equals(BigInteger.ONE)) {
                return numerator.toString(); // Integer output if denominator is 1
            }
            return numerator + "/" + denominator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fraction fraction = (Fraction) o;
            // Fractions are simplified in constructor, so direct comparison works
            return numerator.equals(fraction.numerator) &&
                    denominator.equals(fraction.denominator);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numerator, denominator);
        }

        // Method to convert to double (for potential approximation)
        public double toDouble() {
            if (denominator.equals(BigInteger.ZERO)) return Double.NaN; // Should not happen with check
            // Use BigDecimal for better precision during conversion
            java.math.BigDecimal num = new java.math.BigDecimal(numerator);
            java.math.BigDecimal den = new java.math.BigDecimal(denominator);
            return num.divide(den, 15, java.math.RoundingMode.HALF_UP).doubleValue(); // 15 decimal places
        }
    }
