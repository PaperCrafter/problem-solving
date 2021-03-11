package leetCode.bestToimeToBuyAndSellStock;

public class BestTimeToBuyAndSellStock2 {
    public int solution(int[] prices) {
        int priceBefore = prices[0];
        int result = 0;
        for (int price : prices) {
            if (price - priceBefore > 0) {
                result += price - priceBefore;
            }
            priceBefore = price;
        }
        return result;
    }
}
