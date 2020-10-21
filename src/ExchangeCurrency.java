
import javafx.scene.chart.ScatterChart;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExchangeCurrency {
    public static void main(String[] args){
        ExchangeAPI obj = new ExchangeAPI();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("### Currency Exchange System ###");
            System.out.print("Please,enter youz current currency: ");
            String currentCurrency = scanner.nextLine();
            if (obj.getConnection(currentCurrency) == false)
                System.out.println("Sorry,wa can't find this currency rate.");
            else {
                System.out.print("Amoun:");
                double amount = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter new currency: ");
                String newcurrency = scanner.nextLine();

                double rate = obj.getEachRate(newcurrency);

                System.out.println("\nExchange" + currentCurrency + " to " + newcurrency);
                System.out.println("\t" + currentCurrency+": " + amount);
                System.out.println("\t"+newcurrency+":"+(amount*rate));

            }

        }catch (InputMismatchException e ){
            e.printStackTrace();

        }

        }//class
    }
