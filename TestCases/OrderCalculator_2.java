
public class OrderCalculator_2 {

    // small change: slightly different tax rate and discount threshold
    private static final double TAX_RATE = 0.12;
    private static final double DISCOUNT_THRESHOLD = 120.0;
    private static final double DISCOUNT_RATE = 0.10;

    public double calculateSubtotal(double[] prices) {
        double subtotal = 0.0;
        for (double price : prices) {
            subtotal += price;
        }
        return subtotal;
    }

    // reordered methods and added a comment
    public double applyTax(double amount) {
        // tax is applied after any discounts
        return amount + (amount * TAX_RATE);
    }

    public double applyDiscount(double subtotal) {
        if (subtotal >= DISCOUNT_THRESHOLD) {
            return subtotal - (subtotal * DISCOUNT_RATE);
        }
        return subtotal;
    }

    public double calculateFinalTotal(double[] prices) {
        double subtotal = calculateSubtotal(prices);
        double afterDiscount = applyDiscount(subtotal);
        return applyTax(afterDiscount);
    }

    public static void main(String[] args) {
        OrderCalculator_2 calc = new OrderCalculator_2();
        System.out.println(calc.calculateFinalTotal(new double[]{40, 70}));
    }
}
