package leetCode.bestToimeToBuyAndSellStock;

public class BestTimeToBuyAndSellStock {
    public int solution(int[] prices) {
        int maxValue = 0;
        int currentMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - currentMin > maxValue) {
                maxValue = prices[i] - currentMin;
            }
            if (currentMin > prices[i]) {
                currentMin = prices[i];
            }
        }
        return maxValue;
    }
}
