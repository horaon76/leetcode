package Basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BinarySearchExample {

    public static void CharSearch(){
        char vowels[] = {'a', 'e', 'i', 'o', 'u'};

        char key = 'i';

//        int foundItemIndex = Arrays.binarySearch(vowels, key);
//
//        System.out.println("The given vowel is at index: " + foundItemIndex);

        int fromIndex = 0;
        int toIndex = 2;
        int foundItemIndex2 = Arrays.binarySearch(vowels, fromIndex, toIndex, key);

        System.out.println("The given vowel is at index foundItemIndex2: " + foundItemIndex2);
    }
    public static void main(String args[]){
        List<Integer> ids = IntStream.range(1, 101).boxed().toList(); // List of 100 IDs'
        CharSearch();
    }
}
