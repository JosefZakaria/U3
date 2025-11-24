package Model;

/**
 * Interface för beställningsbara objekt.
 * Hanterar getter för namn och pris.
 * @Author Josef Zakaria
 * @Author Alper Eken
 */
public interface Orderable {
    String getName();
    double calculatePrice();
}
