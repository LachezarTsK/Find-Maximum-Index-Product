package JavaJava.maximumIndexProduct.gitHub;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfIntegers = scanner.nextInt();
    int[] inputIntegers = new int[numberOfIntegers];
    for (int i = 0; i < numberOfIntegers; i++) {
      inputIntegers[i] = scanner.nextInt();
    }
    scanner.close();

    long result = findMaximumIndexProduct(inputIntegers);
    System.out.println(result);
  }

  private static long[] calculateAndStore_productOfIndexes(int[] inputIntegers) {

    long[] productOfIndexes = new long[inputIntegers.length];
    Stack<Integer> indexes_leftHalf = new Stack<Integer>();
    Stack<Integer> indexes_rightHalf = new Stack<Integer>();

    for (int i = 0; i < inputIntegers.length - 1; i++) {

      while (!indexes_leftHalf.isEmpty()
          && inputIntegers[i] >= inputIntegers[indexes_leftHalf.peek()]) {
        indexes_leftHalf.pop();
      }
      // The condition: (j<i && inputIntegers[j]<inputIntegers[i]) is fulfilled.
      if (!indexes_leftHalf.isEmpty()) {
        productOfIndexes[i] = indexes_leftHalf.peek() + 1;
      }
      indexes_leftHalf.push(i);
    }

    for (int i = inputIntegers.length - 1; i >= 0; i--) {

      // If the multiplicant to the left is zero, no need to check to the right of the element.
      if (productOfIndexes[i] > 0) {

        while (!indexes_rightHalf.isEmpty()
            && inputIntegers[i] >= inputIntegers[indexes_rightHalf.peek()]) {
          indexes_rightHalf.pop();
        }

        // The condition: (i<j && inputIntegers[i]<inputIntegers[j]) is fulfilled.
        if (!indexes_rightHalf.isEmpty()) {
          productOfIndexes[i] = productOfIndexes[i] * (1 + indexes_rightHalf.peek());

        }
        // Otherwise, the product of indexes has to be set to 0.
        else {
          productOfIndexes[i] = 0;
        }
      }
      indexes_rightHalf.push(i);
    }
    return productOfIndexes;
  }

  private static long findMaximumIndexProduct(int[] inputIntegers) {

    long[] productOfIndexes = calculateAndStore_productOfIndexes(inputIntegers);
    long maximumIndexProduct = 0;

    for (long product : productOfIndexes) {
      if (product > maximumIndexProduct) {
        maximumIndexProduct = product;
      }
    }
    return maximumIndexProduct;
  }
}
