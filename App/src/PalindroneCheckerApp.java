import java.util.Scanner;

public class UC10CaseInsensitivePalindrome {

    // Method to normalize string: remove spaces & convert to lowercase
    static String normalize(String str) {
        // Remove all non-alphanumeric characters (spaces, punctuation)
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    // Recursive palindrome check
    static boolean isPalindrome(String str, int start, int end) {
        if (start >= end) return true; // base condition
        if (str.charAt(start) != str.charAt(end)) return false; // mismatch
        return isPalindrome(str, start + 1, end - 1); // recursive call
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String normalized = normalize(input);

        boolean result = isPalindrome(normalized, 0, normalized.length() - 1);

        if (result) {
            System.out.println("Palindrome (case-insensitive, spaces ignored)");
        } else {
            System.out.println("Not Palindrome");
        }

        sc.close();
    }
}