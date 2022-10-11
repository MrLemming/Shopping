import java.io.*;

public class Basket {

    protected String[] products;
    protected int[] prices;
    protected long[] basket;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        basket = new long[products.length];
    }

    public void addToCart(int productNum, int amount) {
        basket[productNum] += amount;
    }

    public void printCart() {
        int sumProduct = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] != 0) {
                System.out.println(products[i] + " " + basket[i] + " шт " + prices[i] + " руб/шт " + (basket[i] * prices[i]) + " руб в сумме");
                sumProduct += basket[i] * prices[i];
            }
        }
        System.out.println("Итого: " + sumProduct + " руб");
    }

    public void saveTxt(File textFile) throws Exception {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String s : getProducts())
                out.print(s + " ");
            out.print("\n");
            for (int i : getPrices())
                out.print(i + " ");
            out.print("\n");
            for (long e : getProductBasket())
                out.print(e + " ");
        }
    }

    static Basket loadFromTxtFile(File textFile) throws Exception {
        if (textFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(textFile));) {

                String[] products = reader.readLine().strip().split(" ");
                String[] pricesStr = reader.readLine().strip().split(" ");
                int[] prices = new int[pricesStr.length];
                for (int i = 0; i < prices.length; i++) {
                    prices[i] = Integer.parseInt(pricesStr[i]);
                }

                Basket basket = new Basket(products, prices);
                String[] amountsStr = reader.readLine().strip().split(" ");
                for (int i = 0; i < amountsStr.length; i++) {
                    basket.basket[i] = Integer.parseInt(amountsStr[i]);
                }
                return basket;
            }
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко"};
            int[] prices = {100, 200, 300};
            Basket basket = new Basket(products, prices);
            return basket;
        }
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public long[] getProductBasket() {
        return basket;
    }
}