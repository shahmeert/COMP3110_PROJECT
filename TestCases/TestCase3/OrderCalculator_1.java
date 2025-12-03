public class OrderCalculator_1 {

    private static final double TAX_RATE = 0.13;
    private static final double DISCOUNT_THRESHOLD = 100.0;
    private static final double DISCOUNT_RATE = 0.10;

    public double calculateSubtotal(double[] prices) {
        double subtotal = 0.0;
        for (double p : prices) {
            subtotal += p;
        }
        return subtotal;
    }

    public double applyDiscount(double subtotal) {
        if (subtotal >= DISCOUNT_THRESHOLD) {
            return subtotal - (subtotal * DISCOUNT_RATE);
        }
        return subtotal;
    }

    public double applyTax(double amount) {
        return amount + (amount * TAX_RATE);
    }

    public double calculateFinalTotal(double[] prices) {
        double subtotal = calculateSubtotal(prices);
        double discounted = applyDiscount(subtotal);
        return applyTax(discounted);
    }

    public static void main(String[] args) {
        OrderCalculator_1 calc = new OrderCalculator_1();
        System.out.println(calc.calculateFinalTotal(new double[]{40, 70}));
    }
}
