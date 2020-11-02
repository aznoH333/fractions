public class Fraction implements IFraction {

    private final Integer numerator;
    private final Integer denominator;

    public Fraction(Integer numerator, Integer denominator) {
        this.numerator = numerator;
        this.denominator = denominator;

        if (denominator == 0) throw new ArithmeticException("Cannot Divide by 0");
    }

    @Override
    public Integer getNumerator() {
        return numerator;
    }

    @Override
    public Integer getDenominator() {
        return denominator;
    }

    @Override
    public IFraction plus(IFraction other) {
        int num = 0;
        int den = 0;
        if (denominator == other.getDenominator()){
            den = denominator;
            num = numerator + other.getNumerator();
        }else {
            den = findLowestCommonMultiple(denominator,other.getDenominator());
            num = (numerator * (den/denominator)) + (other.getNumerator() * (den/ other.getDenominator()));
        }

        return new Fraction(num,den);
    }

    @Override
    public IFraction minus(IFraction other) {
        int num = 0;
        int den = 0;
        if (denominator == other.getDenominator()){
            den = denominator;
            num = numerator + other.getNumerator();
        }else {
            den = findLowestCommonMultiple(denominator,other.getDenominator());
            num = (numerator * (den/denominator)) - (other.getNumerator() * (den/ other.getDenominator()));
        }

        return new Fraction(num,den);
    }

    @Override
    public IFraction times(IFraction other) {
        return new Fraction(numerator * other.getNumerator(),denominator * other.getDenominator());
    }

    @Override
    public IFraction dividedBy(IFraction other) {
        return new Fraction(denominator * other.getDenominator(),numerator * other.getNumerator());
    }

    public static Fraction createNormalised(Integer numerator, Integer denominator) {
        for(int i = denominator;i > 0;i--){
            if (denominator % i == 0 && numerator % i == 0){
                return new Fraction(numerator / i,denominator / i);
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * @link https://www.baeldung.com/java-greatest-common-divisor
     */
    private static Integer findGreatestCommonDenominator(Integer i1, Integer i2) {
        if (i1 < i2) return findGreatestCommonDenominator(i2, i1);
        if (i2 == 0) return i1;
        return findGreatestCommonDenominator(i2, i1 % i2);
    }

    private static Integer findLowestCommonMultiple(Integer i1, Integer i2) {
        if (i1 == 0 || i2 == 0) return 0;
        else {
            int gcd = findGreatestCommonDenominator(i1, i2);
            return Math.abs(i1 * i2) / gcd;
        }
    }

    @Override
    public String toString() {
        return "Fraction " + numerator + "|" + denominator;
    }
}
