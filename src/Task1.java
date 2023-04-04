import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Task1 {
    private static void doOperations(String input, Stack<Double> stack) {
        Double sndOperand = stack.pop();
        Double fstOperand = stack.pop();
        switch (input) {
            case "+":
                stack.push(fstOperand + sndOperand);
                break;
            case "-":
                stack.push(fstOperand - sndOperand);
                break;
            case "*":
                stack.push(fstOperand * sndOperand);
                break;
            case "/":
                stack.push(fstOperand / sndOperand);
                break;
            case "^":
                stack.push(Math.pow(fstOperand, sndOperand));
                break;
        }
    }
    private static void processInput(String input, Stack<Double> stack) {
        try {
            Double doubleInput = Double.parseDouble(input);
            stack.push(doubleInput);
        } catch (NumberFormatException e) {
            doOperations(input, stack);
        }
    }

    public static void main(String[] args) {
        System.out.println("Insira o caminho do arquivo:");
        Scanner scan = new Scanner(System.in);
        String filePath = scan.next();
        Stack<Double> stack = new Stack<Double>();

        try {
            File expressionFile = new File(filePath);
            Scanner reader = new Scanner(expressionFile);
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                processInput(input, stack);
            }
            reader.close();
            System.out.println(stack.pop());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
