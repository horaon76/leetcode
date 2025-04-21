package Basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraysTest {

    public static void main(String[] args){
        int nums[] = {11, 2, 5, 3, 84, 41};
        Arrays.sort(nums); // Sorts in ascending order
        System.out.println(Arrays.toString(nums)); // [2, 3, 5, 11, 41, 84]

        // Step 2: Reverse the sorted array
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
        //O(nlogn)
        System.out.println("Reverse Sorted: " + Arrays.toString(nums));

        //Reverse
        int[] nums2 = Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> b - a) // Reverse order
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println("Reverse Sorted: " + Arrays.toString(nums2));

        Integer[] numList = new Integer[]{11, 2, 5, 3, 84, 41};
        System.out.println("numList " + Arrays.toString(numList));

        Arrays.sort(numList);
        System.out.println("numList increasing" + Arrays.toString(numList));

        Arrays.sort(numList, Collections.reverseOrder());
        System.out.println("numList reverse" + Arrays.toString(numList));

        //Using collections
        List<Integer> numListAsList = Arrays.asList(numList);
        Collections.sort(numListAsList, Collections.reverseOrder());

        System.out.println("numList reverse2" +Arrays.toString(numList));

        Integer[] numList2 = new Integer[]{1, 2, 3, 4, 5};

        Arrays.sort(numList2, (a, b) -> b - a); // Lambda for reverse sorting

        System.out.println(Arrays.toString(numList2));
    }
}
