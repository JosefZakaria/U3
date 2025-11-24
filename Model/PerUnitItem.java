package Model;

/**
 * Enum för objekt som säljs per styck.
 * @Author Josef Zakaria
 * @Author Alper Eken
 */
public enum PerUnitItem implements Orderable{
    BAGEL("Bagel", 15),
    BROWNIE("Brownie", 25),
    CUPCAKE("Cupcake", 15),
    DONUT("Donut", 10),
    MUFFIN("Muffin", 20);


    private String name;
    private double price;

    /**
     * Konstruktor för att skapa ett objekt som säljs per styck.
     * @param name namn på objekt.
     * @param price pris på objekt.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    PerUnitItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    /**
     * Metod för att hämta namn på objekt.
     * @return name på objekt.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public String getName(){
        return name;
    }

    /**
     * Metod för att hämta pris på objekt.
     * @return price på objekt.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public double getPrice(){
        return price;
    }

    /**
     * Metod för att räkna ut pris på objekt.
     * @return price på objekt.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    @Override
    public double calculatePrice() {
        return price;
    }

    /**
     * Metod för att returnera namn på objekt som sträng.
     * @return name på objekt.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    @Override
    public String toString() {
        return name;
    }
}
