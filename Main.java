import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение (например 2 + 2 или II + II): ");
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
        Converter converter = new Converter();
        String[] actions = {"+", "-", "*", "/"};
        String[] regexActions = {"\\+", "-", "\\*", "/"};
        String[] data = input.split(" ");

        if (data.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение - должно быть два операнда и один оператор");
        }

        boolean isRoman = converter.isRoman(data[0]) && converter.isRoman(data[2]);

        int a, b;
        if (isRoman) {
            a = converter.romanToInt(data[0]);
            b = converter.romanToInt(data[2]);
        } else {
            try {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некорректные числа");
            }
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10");
        }

        int result;
        switch (data[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректная арифметическая операция");
        }

        return isRoman ? converter.intToRoman(result) : String.valueOf(result);
    }
}
