package Basic;

public class Palindrome {

    public static Boolean isPalindrome(String input){
        int left = 0;
        int right = input.length() - 1;
        Boolean result = true;
        while(left <= right){
                if(input.charAt(left) != input.charAt(right)){
                    result = false;
                    break;
                }
                left++;
                right--;
        }
        return result;
    }

    public static void main(String[] args){
        String input = "baabdab";
        System.out.print(isPalindrome(input));
    }
}
