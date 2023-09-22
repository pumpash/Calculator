import java.util.Scanner;

public class CalcTimurlan {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();

        try {
            String result = calc(exp);
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) {
        Convert converter = new Convert();
        String[] actions = {"+", "-", "*", "/"};
        String[] regexActions = {"\\+", "-", "\\*", "/"};
        String[] data = input.split(regexActions[0]);

        if (data.length != 2) {
            throw new IllegalArgumentException("Некорректное выражение - должно быть два операнда");
        }

        boolean isRoman = converter.isRoman(data[0]) && converter.isRoman(data[1]);

        int a, b;
        if (isRoman) {
            a = converter.romanToInt(data[0]);
            b = converter.romanToInt(data[1]);
        } else {
            try {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некорректные числа");
            }
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result;
        switch (input.charAt(data[0].length())) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректная арифметическая операция");
        }

        return isRoman ? converter.intToRoman(result) : String.valueOf(result);
    }
}
