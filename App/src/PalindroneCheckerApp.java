import java.util.Scanner;

public class UC11PalindromeServiceApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();


        PalindromeChecker checker = new PalindromeChecker();
        boolean result = checker.checkPalindrome(input);

        if (result) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }

        sc.close();
    }
}

class PalindromeChecker {

    public boolean checkPalindrome(String input) {
        if (input == null || input.isEmpty()) return true;

        String normalized = normalize(input);

        return isPalindromeRecursive(normalized, 0, normalized.length() - 1);
    }

    private String normalize(String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    private boolean isPalindromeRecursive(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return isPalindromeRecursive(str, start + 1, end - 1);
    }
}