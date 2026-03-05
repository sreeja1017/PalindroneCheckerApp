import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


interface PalindromeStrategy {
    boolean check(String input);
}


class StackStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : normalized.toCharArray()) {
            stack.push(c);
        }
        for (char c : normalized.toCharArray()) {
            if (stack.pop() != c) return false;
        }
        return true;
    }
}


class DequeStrategy implements PalindromeStrategy {
    @Override
    public boolean check(String input) {
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        Deque<Character> deque = new LinkedList<>();
        for (char c : normalized.toCharArray()) {
            deque.addLast(c);
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }
}


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

public class UC12StrategyPalindromeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();


        PalindromeContext context = new PalindromeContext(new StackStrategy());
        boolean resultStack = context.checkPalindrome(input);

        context.setStrategy(new DequeStrategy());
        boolean resultDeque = context.checkPalindrome(input);

        System.out.println("Using Stack strategy: " + (resultStack ? "Palindrome" : "Not Palindrome"));
        System.out.println("Using Deque strategy: " + (resultDeque ? "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}

