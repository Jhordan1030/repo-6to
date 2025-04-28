import java.math.BigInteger;

// Clase para representar Fracciones con BigInteger
class Fraction {
    private BigInteger numerator;
    private BigInteger denominator;

    // Constructor y simplificación usando Máximo Común Divisor (GCD)
    public Fraction(BigInteger num, BigInteger den) {
        if (den.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        // Manejar signos
        if (den.signum() < 0) {
            num = num.negate();
            den = den.negate();
        }

        // Simplificar
        BigInteger commonDivisor = num.gcd(den);
        this.numerator = num.divide(commonDivisor);
        this.denominator = den.divide(commonDivisor);
    }

    // Constructor para enteros (denominador 1)
    public Fraction(long num) {
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.ONE;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    // Suma de fracciones: a/b + c/d = (ad + bc) / bd
    public Fraction add(Fraction other) {
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
                .add(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    // Multiplicación de fracciones: (a/b) * (c/d) = ac / bd
    public Fraction multiply(Fraction other) {
        BigInteger newNumerator = this.numerator.multiply(other.numerator);
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new Fraction(newNumerator, newDenominator);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString(); // Si es entero, mostrar solo numerador
        }
        return numerator + "/" + denominator;
    }

    // Necesario para comparar fracciones si se quisiera ordenar o usar en Sets/Maps
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        // Las fracciones se simplifican en el constructor, así que la comparación directa es suficiente
        return numerator.equals(fraction.numerator) && denominator.equals(fraction.denominator);
    }

    @Override
    public int hashCode() {
        // Un hash simple basado en los componentes simplificados
        int result = numerator.hashCode();
        result = 31 * result + denominator.hashCode();
        return result;
    }
}