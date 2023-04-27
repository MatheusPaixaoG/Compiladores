import TokenUtils.Token;
import Scanner.RPNScanner;
import inputProcessing.InputProcessing;

import java.util.List;

public class Task3 {
    private static RPNScanner scanner = new RPNScanner();
    private static InputProcessing inputProcessing = new InputProcessing();

    public static void main(String[] args) {
        List<Token> listOfTokens = scanner.scanPipeline();
        inputProcessing.processInput(listOfTokens);
        System.out.println(inputProcessing.popStack());
    }
}
