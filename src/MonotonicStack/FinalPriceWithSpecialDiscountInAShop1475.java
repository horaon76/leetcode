package MonotonicStack;

import java.util.*;

/**
 * You are given an integer array prices where prices[i] is the price of the ith item in a shop.
 * <p>
 * There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.
 * <p>
 * Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [8,4,6,2,3]
 * Output: [4,2,4,2,3]
 * Explanation:
 * For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
 * For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
 * For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
 * For items 3 and 4 you will not receive any discount at all.
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: [1,2,3,4,5]
 * Explanation: In this case, for all items, you will not receive any discount at all.
 * Example 3:
 * <p>
 * Input: prices = [10,1,1,6]
 * Output: [9,0,1,6]
 **/

public class FinalPriceWithSpecialDiscountInAShop1475 {

    public static List<Integer> finalPrices(int[] prices) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int price : prices) {
            while (!stack.isEmpty() && price <= stack.peek()) {
                map.put(stack.pop(), price);
            }
            stack.add(price);
        }
        //some checks needs to be added fails for 3rd case
        for (int price : stack) {
            result.add(price - map.getOrDefault(price, 0));
        }
        return result;
    }

    public static int[] finalPrices1(int[] prices) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < prices.length; i++) {

            while (!st.isEmpty() && prices[st.peek()] >= prices[i]) {
                prices[st.peek()] = prices[st.peek()] - prices[i];
                st.pop();
            }

            st.push(i); //Push indexes instead of values
        }

        return prices;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{8,4,6,2,3};
//        System.out.println(finalPrices(nums));
//        int[] nums1 = new int[]{1,2,3,4,5};
//        System.out.println(finalPrices(nums1));
        int[] nums2 = new int[]{10, 1, 1, 1, 6};
        System.out.println(finalPrices(nums2));
    }
}
