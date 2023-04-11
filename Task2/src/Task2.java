import TokenUtils.Token;
import TokenUtils.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Task2 {
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
    private static void processInput(List<Token> input, Stack<Double> stack) {
        for (Token tk: input ) {
            try {
                Double doubleInput = Double.parseDouble(tk.lexeme);
                stack.push(doubleInput);
            } catch (NumberFormatException e) {
                doOperations(tk.lexeme, stack);
            }
        }
    }

    private static boolean isNumeric(String input) {
        try {
            Double doubleInput = Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }

    private static TokenType determineType(String input) {
        if (input.equals("+")) {
            return TokenType.PLUS;
        } else if (input.equals("-")) {
            return TokenType.MINUS;
        } else if (input.equals("*")) {
            return TokenType.STAR;
        } else if (input.equals("/")) {
            return TokenType.SLASH;
        } else if (isNumeric(input)) {
            return TokenType.NUM;
        } else {
            return TokenType.NOTVALID;
        }
    }

    private static List<Token> scanInput(String source) {
        List<Token> listOfTokens = new ArrayList<Token>();
        try {
            File expressionFile = new File(source);
            Scanner reader = new Scanner(expressionFile);
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                TokenType typeOfInput = determineType(input);
                if (typeOfInput != TokenType.NOTVALID) {
                    listOfTokens.add(new Token(typeOfInput, input));
                } else {
                    System.out.println("Error: Unexpected character: "+input);
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return listOfTokens;
    }

    public static void main(String[] args) {
        System.out.println("Insira o caminho do arquivo:");
        Scanner scan = new Scanner(System.in);
        String filePath = scan.next();
        Stack<Double> stack = new Stack<Double>();

        List<Token> listOfTokens = scanInput(filePath);
        processInput(listOfTokens, stack);
        System.out.println(stack.pop());
    }
}
