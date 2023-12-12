package org.example;

public class LargeNumberMultiplier {
    public static String multiply(String num1, String num2) {
        // check for zero

        // check for neg numbers
        boolean isNegative = ((num1.charAt(0) == '-' && num2.charAt(0) != '-') || (num1.charAt(0) != '-' && num2.charAt(0) == '-'));

        num1 = num1.replaceAll("-", "");
        num2 = num2.replaceAll("-", "");

        if(num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String reversedNum1 = new StringBuilder(num1).reverse().toString();
        String reversedNum2 = new StringBuilder(num2).reverse().toString();

        int[] result = new int[num1.length() + num2.length()];
        // sign

        // mult

        for(int i = 0; i < reversedNum1.length(); ++i) {
            for(int j = 0; j < reversedNum2.length(); ++j) {
                int digit1 = reversedNum1.charAt(i) - '0';
                int digit2 = reversedNum2.charAt(j) - '0';

                result[i + j] += digit1 * digit2;

                // handle carry
                if(result[i + j] >= 10) {
                    result[i + j + 1] += result[i + j] / 10;
                    result[i + j] %= 10;
                }
            }
        }

        StringBuilder finalResult = new StringBuilder();

        for(int i = result.length - 1; i >= 0; --i) {
            if(result[i] != 0 || finalResult.length() > 0) {
                finalResult.append(result[i]);
            }
        }

        if(isNegative) {
            finalResult.insert(0, '-');
        }

        return finalResult.toString();

        // stringify
    }

    /**
     * Input:
     * -5609814095670785987450101057461987365715718375
     * -987654321234567898765432123456789
     * Output:
     * 5540557132911841478857543165820141898332916971365359892859689227044277405797875
     * Example 3:
     * Input:
     * 999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
     * -0
     * Output:
     * 0
     * @param args
     */

    public static void main(String[] args) {
        String num1 = "-5609814095670785987450101057461987365715718375";
        String num2 = "-987654321234567898765432123456789";
        String result1 = multiply(num1, num2);
        System.out.println("Example: " + result1);
    }


}