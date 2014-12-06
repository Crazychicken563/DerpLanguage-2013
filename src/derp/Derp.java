package derp;

import java.io.File;
import java.util.Scanner;

public class Derp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Parser parser = new Parser();
        System.out.println("Inderpreter Activated");
        input = scanner.nextLine();
        Scanner fileIn = null;
        while (!(input.equalsIgnoreCase("exit"))) {
            fileIn = null;
            try {
                fileIn = new Scanner(new File(input + ".txt"));
            } catch (Exception e) {
                parser.reInit(input);
                parser.excecuteInstructionTree();
            }
            if (fileIn != null) {
                while (fileIn.hasNext()) {
                    input = fileIn.nextLine();
                    if (!(input.equals("#"))) {
                        //System.out.println(input);
                        parser.reInit(input);
                        parser.excecuteInstructionTree();
                    } else {
                        input = fileIn.nextLine();
                        while (!(input.equals("#"))) {
                            input = fileIn.nextLine();
                        }
                    }
                }
            }
            input = scanner.nextLine();
        }
    }
}