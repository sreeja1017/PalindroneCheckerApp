import java.util.Scanner;
public class PalindroneCheckerApp {
    public static void main(String[] args)
    {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Input text: ");
                String input = scanner.nextLine();

                boolean isPalindrome = true;
                for (int i = 0; i < input.length() / 2; i++) {
                    if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                        isPalindrome = false;
                        break;
                    }
                }

                System.out.println("Is it a Palindrome? : " + isPalindrome);

                scanner.close();

            }
        }

