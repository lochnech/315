package edu.missouristate;

// That's Odd
// @author Keegan Spell

public class week10Lab1 {
    // adds all odd integers and prints the result
    private static void printOddIntegers(int[] arr) {
        int res = 0;

        for (int a : arr) if (a%2 == 1) res += a;

        System.out.println(res);
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11};
        printOddIntegers(arr);
    }
}