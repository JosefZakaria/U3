package Model;

/**
 * Enum klass för att skapa olika fyllningar till tårtor.
 * Hanterar getter och setter för namn och pris.
 * @Author Josef Zakaria
 * @Author Alper Eken
 */
public enum Filling {
    Chocolate("Chocolate", 20),
    Vanilla("Vanilla", 50),
    Strawberry("Strawberry", 30),
    Lemon("Lemon", 60),
    Raspberry("Raspberry", 40);


    private String name;
    private double price;


    /**
     * Konstruktor för att skapa en fyllning med namn och pris.
     * @param name namn på fyllning.
     * @param price pris på fyllning.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    private Filling(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Metod för att hämta namn på fyllning.
     * @return name på fyllning.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public String getName() {
        return name;
    }

    /**
     * Metod för att hämta pris på fyllning.
     * @return price på fyllning.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public double getPrice() {
        return price;
    }




}
