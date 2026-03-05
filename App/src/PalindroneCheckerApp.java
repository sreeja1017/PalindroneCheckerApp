import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

// Strategy interface
interface PalindromeStrategy {
    boolean check(String input);
}

// Stack-based strategy
class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : normalized.toCharArray()) stack.push(c);
        for (char c : normalized.toCharArray()) {
            if (stack.pop() != c) return false;
        }
        return true;
    }
}

// Deque-based strategy
class DequeStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new LinkedList<>();
        for (char c : normalized.toCharArray()) deque.addLast(c);
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }
}

// Recursive strategy
class RecursiveStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeRecursive(normalized, 0, normalized.length() - 1);
    }

    private boolean isPalindromeRecursive(String str, int start, int end) {
        if (start >= end) return true;
        if (str.charAt(start) != str.charAt(end)) return false;
        return isPalindromeRecursive(str, start + 1, end - 1);
    }
}

// Context class to inject strategy
class PalindromeContext {
    private PalindromeStrategy strategy;

    public PalindromeContext(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkPalindrome(String input) {
        return strategy.check(input);
    }
}

// Main app
public class UC13PerformanceComparison {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        PalindromeContext context = new PalindromeContext(new StackStrategy());

        // Stack
        long start = System.nanoTime();
        boolean stackResult = context.checkPalindrome(input);
        long stackTime = System.nanoTime() - start;

        // Deque
        context.setStrategy(new DequeStrategy());
        start = System.nanoTime();
        boolean dequeResult = context.checkPalindrome(input);
        long dequeTime = System.nanoTime() - start;

        // Recursive
        context.setStrategy(new RecursiveStrategy());
        start = System.nanoTime();
        boolean recursiveResult = context.checkPalindrome(input);
        long recursiveTime = System.nanoTime() - start;

        // Display results
        System.out.println("\nPerformance Comparison:");
        System.out.println("Stack Strategy: " + (stackResult ? "Palindrome" : "Not Palindrome") + " | Time: " + stackTime + " ns");
        System.out.println("Deque Strategy: " + (dequeResult ? "Palindrome" : "Not Palindrome") + " | Time: " + dequeTime + " ns");
        System.out.println("Recursive Strategy: " + (recursiveResult ? "Palindrome" : "Not Palindrome") + " | Time: " + recursiveTime + " ns");

        sc.close();
    }
}