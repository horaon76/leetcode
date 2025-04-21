package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class rotateMatrix {

    public static void spiralPrint(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right){
            for(int toprow = left; toprow <= right; toprow++){
                System.out.print(matrix[top][toprow] + " ");
            }
            top++;
            System.out.println("");
            for(int rightcol = top; rightcol <= bottom; rightcol++){
                System.out.print(matrix[rightcol][right] + " ");
            }
            right--;
            System.out.println("");
            if(top <= bottom){
                for(int bottomrow = right; bottomrow >= left; bottomrow--){
                    System.out.print(matrix[bottom][bottomrow] + " ");
                }
                bottom--;
                System.out.println("");
            }
            if(left <= right){
                for(int leftcol = bottom; leftcol >= top; leftcol--){
                    System.out.print(matrix[leftcol][left] + " ");
                }
                left++;
                System.out.println("");
            }
        }


    }

    public static void main(String[] args){
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6,7,8,9,10},
                {12,13,14,15,16},
                {17,18,19,20,21},
                {21,22,23,24,25}
        };
        int[] temp = new int[5];
        spiralPrint(matrix);
    }
}
