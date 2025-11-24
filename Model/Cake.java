package Model;

import java.util.ArrayList;

/**
 * Klassen Cake representerar en tårta.
 * Den innehåller en lista av fyllningar, ett pris, antal bitar och pris per bit.
 * Den ärver från BakeryItem och implementerar metoden calculatePrice.
 * @Author Josef Zakaria
 * @Author Alper Eken
 */
public class Cake implements Orderable {
    ArrayList<Filling> fillings = new ArrayList<Filling>();
    private double basePrice;
    private int pricePerPiece;
    private int numberOfPieces;
    private String name;


    /**
     * Konstruktor för att skapa en tårta med namn, pris, fyllningar, pris per bit och antal bitar.
     * @param name namn på tårta.
     * @param price pris på tårta.
     * @param fillings fyllningar på tårta.
     * @param pricePerPiece pris per bit.
     * @param numberOfPieces antal bitar.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public Cake (String name, double price, ArrayList<Filling> fillings, int pricePerPiece, int numberOfPieces){
        this.name = name;
        this.basePrice = price;
        this.fillings = fillings;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
    }


    /**
     * Metod för att hämta fyllningar på tårta.
     * @return fillings på tårta.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public ArrayList<Filling> getFillings(){
        return fillings;
    }


    /**
     * Metod för att sätta baspriset.
     * @param price på tårta.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public void setPrice(double price){
        this.basePrice = price;
    }

    /**
     * Metod för att hämta baspriset.
     * @return basePrice.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public double getPrice(){
        return basePrice;
    }


    /**
     * Metod för att sätta antal bitar på tårta.
     * @param numberOfPieces på tårta.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public void setNumberOfPieces(int numberOfPieces){
        this.numberOfPieces = numberOfPieces;
    }

    /**
     * Metod för att hämta antal bitar på tårta.
     * @return numberOfPieces på tårta.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    public int getNumberOfPieces(){
        return numberOfPieces;
    }

    /**
     * Metod för att hämta namnet på tårtan.
     * @return name på tårtan.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Metod för att beräkna totalpriset på tårta.
     * @return totalPrice på tårta.
     * @Author Josef Zakaria
     * @Author Alper Eken
     */
    @Override
    public double calculatePrice() {
        double totalPrice = basePrice;

        for (Filling filling : fillings) {
            totalPrice += filling.getPrice();
        }

        totalPrice += numberOfPieces * pricePerPiece;

        return totalPrice;
    }
}
