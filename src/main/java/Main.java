import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));

        ReaderShopping load = new ReaderShopping(doc.getElementsByTagName("load").item(0));
        ReaderShopping save = new ReaderShopping(doc.getElementsByTagName("save").item(0));
        ReaderShopping log = new ReaderShopping(doc.getElementsByTagName("log").item(0));

        Basket basket = null;
        File textFile = new File("basket.txt");
        File jsonFile = new File("basket.json");

        try {
            basket = Basket.loadFromTxtFile(textFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            basket = Basket.loadFromJson(jsonFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (basket == null) {
            String[] products = {"Хлеб", "Яблоки", "Молоко"};
            int[] prices = {100, 200, 300};
            basket = new Basket(products, prices);
        }
        ClientLog clientLog = new ClientLog();

        if (load.enabled) {
            if (load.format.equals("txt")) {
                try {
                    basket = Basket.loadFromTxtFile(new File(load.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (load.format.equals("json")) {
                try {
                    basket = Basket.loadFromJson(new File(load.fileName));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < basket.getProducts().length; i++) {
            System.out.println((i + 1) + " " + basket.getProducts()[i] + " " + basket.getPrices()[i] + " руб/шт");
        }
        System.out.println("Выберите товар и его количество, или введите `end`");
        while (true) {

            String input = scanner.nextLine();

            if (input.equals("end")) {
                basket.printCart();
                break;

            } else {
                String[] count = input.split(" ");
                int productNumber = Integer.parseInt(count[0]) - 1;
                int productCount = Integer.parseInt(count[1]);

                basket.addToCart(productNumber, productCount);

                try {
                    basket.saveTxt(textFile);
                    basket.saveJson(jsonFile);
                    clientLog.exportAsCSV(new File("log.csv"));

                    if (save.enabled) {
                        if (save.format.equals("txt")) {
                            try {
                                basket.saveTxt(new File(save.fileName));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (save.format.equals("json")) {
                            try {
                                basket.saveJson(new File(save.fileName));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    if (log.enabled) {
                        try {
                            clientLog.exportAsCSV(new File(log.fileName));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println("Товар успешно добавлен!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
