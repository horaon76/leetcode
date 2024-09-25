package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * <p>
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * <p>
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 * <p>
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 * <p>
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 **/
public class FruitIntoBasket904 {
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int j = 0;
        int i = 0;
        int res = 0;
        for (i = 0; i < fruits.length; i++) {
            basket.put(fruits[i], basket.getOrDefault(fruits[i], 0) + 1);
            while (basket.size() > 2) {
                basket.put(fruits[j], basket.get(fruits[j]) - 1);
                basket.remove(fruits[j], 0);
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    public static void main(String[] args){
        int[] fruits = new int[]{1,2,1};
        int[] fruits1 = new int[]{5,5,5,5,5,5,5, 1,2,3,2,2, 1,1,1,1, 4,4,4};
//        System.out.println(totalFruit(fruits));
        System.out.println(totalFruit(fruits1));
    }
}
