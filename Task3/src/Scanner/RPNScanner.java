package Scanner;

import TokenUtils.Token;
import TokenUtils.TokenType;
import inputProcessing.InputProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RPNScanner {
    private Scanner scanner;
    private InputProcessing inputProcessing;
    private Scanner fileScanner;
    public RPNScanner () {
        this.scanner = new Scanner(System.in);
        this.inputProcessing = new InputProcessing();
    }

    public String getFilePath() {
        System.out.println("Insira o caminho do arquivo:");
        String filePath = this.scanner.next();
        return filePath;
    }

    private File getFileFromSource(String source) {
        File expressionFile = new File(source);
        return expressionFile;
    }

    private void setScannerForFile(File file) throws FileNotFoundException {
        try {
            this.fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public List<Token> scanPipeline() {
        boolean validPath = false;
        while (!validPath) {
            try {
                String filePath = this.getFilePath();
                File file = getFileFromSource(filePath);
                this.setScannerForFile(file);
                validPath = true;
            } catch (FileNotFoundException e) {
                System.out.println("Esse caminho não existe!");
                System.out.println("Digite um novo caminho: ");
            }
        }
        List<Token> tokens = this.scanInput();
        return tokens;
    }

    public List<Token> scanInput() {
        List<Token> listOfTokens = new ArrayList<Token>();
        while (this.fileScanner.hasNextLine()) {
            String input = this.fileScanner.nextLine();
            TokenType typeOfInput = this.inputProcessing.determineType(input);
            if (typeOfInput != TokenType.NOTVALID) {
                listOfTokens.add(new Token(typeOfInput, input));
            } else {
                System.out.println("Error: Unexpected character: "+input);
                break;
            }
        }
        this.fileScanner.close();
        return listOfTokens;
    }
}
