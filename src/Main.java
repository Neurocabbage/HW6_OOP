
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;
import java.util.logging.*;

public class Main {
    /**
     * 1) Рефакторинг и\или оптимизация проекта предыдущего дз с учетом теоретических основ SOLID’а
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());

        try {
            FileHandler fh = new FileHandler("calc.log");
            fh.setFormatter(new Format());
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter an expression: ");
                String request = scanner.nextLine();
                if (request.equals("end")) break;
                System.out.println("We got the expression: " + request);
                logger.log(Level.INFO, request);

                Object result = Calc.eval(request);
                System.out.println("Expression result= " + result);
                logger.log(Level.INFO, String.valueOf(result));
            }
        }
    }
}