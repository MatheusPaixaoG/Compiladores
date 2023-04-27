package inputProcessing;

import Regex.Regex;
import TokenUtils.Token;
import TokenUtils.TokenType;

import java.util.List;
import java.util.Stack;

public class InputProcessing {
    private Stack<Double> stack;

    public InputProcessing () {
         this.stack = new Stack<Double>();
    }

    public double popStack() {
        return stack.pop();
    }

    private void doOperations(String input) {
        Double sndOperand = this.stack.pop();
        Double fstOperand = this.stack.pop();
        switch (input) {
            case "+":
                this.stack.push(fstOperand + sndOperand);
                break;
            case "-":
                this.stack.push(fstOperand - sndOperand);
                break;
            case "*":
                this.stack.push(fstOperand * sndOperand);
                break;
            case "/":
                this.stack.push(fstOperand / sndOperand);
                break;
            case "^":
                this.stack.push(Math.pow(fstOperand, sndOperand));
                break;
        }
    }

    public void processInput(List<Token> input) {
        for (Token tk: input ) {
            try {
                Double doubleInput = Double.parseDouble(tk.lexeme);
                this.stack.push(doubleInput);
            } catch (NumberFormatException e) {
                doOperations(tk.lexeme);
            }
        }
    }

    public TokenType determineType(String input) {
        Regex regex = new Regex();
        return regex.getTokenType(input);
    }
}
