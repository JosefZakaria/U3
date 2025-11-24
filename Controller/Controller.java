package Controller;

import View.MainFrame;
import View.ButtonType;
import Model.*;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Denna klass är Controllern för bageriapplikationen.
 * Den är ansvarig för att hantera logiken i applikationen.
 * @Author: Josef Zakaria
 * @Author: Alper Eken
 */
public class Controller {
    private MainFrame view;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private ArrayList<Cake> cakes;
    private ArrayList<String> currentOrder;
    private double costCurrentOrder = 0;
    private String [] currentOrderArray;
    private ArrayList<ArrayList<String>> orderHistory;
    private String[] perUnitItemMenuString;
    private String[] orderHistoryMenuString;

    /**
     * Konstruktorn för Controller-klassen.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public Controller() {
        view = new MainFrame(1000, 500, this);
        initializeCakes();
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
        currentOrderArray = new String[0];
        currentOrder = new ArrayList<>();
        perUnitItemMenuString = Arrays.stream(PerUnitItem.values())
                .map(item -> item.getName() + " - Price: " + item.getPrice())
                .toArray(String[]::new);
        orderHistory = new ArrayList<>();
    }

    /**
     * Denna metod skapar ett antal tårtor och lägger till dem i en lista.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    private void initializeCakes(){
        cakes = new ArrayList<Cake>();

        ArrayList<Filling> fillings1 = new ArrayList<Filling>();
        cakes.add(new Cake("Chocolate cake", 100, fillings1, 5, 10));
        fillings1.add(Filling.Chocolate);


        ArrayList<Filling> fillings2 = new ArrayList<Filling>();
        cakes.add(new Cake("Strawberry cake", 100, fillings2, 5, 10));
        fillings2.add(Filling.Strawberry);

        ArrayList<Filling> fillings3 = new ArrayList<Filling>();
        cakes.add(new Cake("Vanilla cake", 150, fillings3, 3, 12));
        fillings3.add(Filling.Vanilla);

        ArrayList<Filling> fillings4 = new ArrayList<Filling>();
        cakes.add(new Cake("Lemon cake", 130, fillings4, 4, 14));
        fillings4.add(Filling.Lemon);

        ArrayList<Filling> fillings5 = new ArrayList<Filling>();
        cakes.add(new Cake("Raspberry cake", 80, fillings5, 6, 16));
        fillings5.add(Filling.Raspberry);


    }

    /**
     * Denna metod anropas när en knapp trycks i GUI:t.
     * @param button Knappen som trycktes.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void buttonPressed(ButtonType button){

        switch (button) {
            case Add:
                addItemToOrder(view.getSelectionLeftPanel());
                break;

            case Cake:
                setToCakeMenu();
                break;

            case PerUnitItem:
                setToPerUnitItemMenu();
                break;

            case MakeCake:
               // addNewCake();
                break;

            case OrderHistory:
                setToOrderHistoryMenu();
                break;

            case Order:
                placeOrder();
                break;

            case ViewOrder:
                viewSelectedOrder(view.getSelectionLeftPanel());
                break;
        }
    }

    /**
     * Denna metod lägger till en vara i ordern och uppdaterar priset.
     * Den hanterar också felhantering om ingen vara är vald.
     * @param selectionIndex Indexet för den valda varan.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void addItemToOrder(int selectionIndex) {
        if (selectionIndex != -1) {
            if (currentLeftMenu == ButtonType.Cake) {
                Cake selectedCake = cakes.get(selectionIndex);
                double totalPrice = selectedCake.calculatePrice();

                StringBuilder fillings = new StringBuilder();
                for (Filling filling : selectedCake.getFillings()) {
                    if (fillings.length() > 0) {
                        fillings.append(", ");
                    }
                    fillings.append(filling.toString());
                }

                int numberOfPieces = selectedCake.getNumberOfPieces();
                String cakeDetails = selectedCake.getName() + " - Price: " + totalPrice + " - Fillings: " + fillings + " - Pieces: " + numberOfPieces;
                currentOrder.add(cakeDetails);
                costCurrentOrder += totalPrice;
            } else if (currentLeftMenu == ButtonType.PerUnitItem) {
                PerUnitItem selectedItem = PerUnitItem.values()[selectionIndex];
                currentOrder.add(selectedItem.getName() + " - Price: " + selectedItem.getPrice());
                costCurrentOrder += selectedItem.getPrice();
            }
            currentOrderArray = currentOrder.toArray(new String[0]);
            view.populateRightPanel(currentOrderArray);
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder));
        }else {
            JOptionPane.showMessageDialog(null, "You need to select an item to add it to the order.");
        }
    }

    /**
     * Denna metod visar en vald order i högerpanelen.
     * Den hanterar också felhantering om ingen order är vald.
     * @param selectionIndex Indexet för den valda ordern.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void viewSelectedOrder(int selectionIndex) {

        if (selectionIndex == -1) {
            JOptionPane.showMessageDialog(null, "You need to select an order to view it.");
            return;
        }

        if ((selectionIndex != -1) && currentLeftMenu == ButtonType.OrderHistory) {
            if (selectionIndex < orderHistory.size()) {
                try {
                    ArrayList<String> selectOrder = orderHistory.get(selectionIndex);
                    double totalCost = calculateTotalCost(selectOrder);
                    view.populateRightPanel(selectOrder.toArray(new String[0]));
                    view.setTextCostLabelRightPanel("Total cost of order: " + totalCost);
                }
                catch (Exception e) {
                    System.err.println("Ett oväntat fel inträffade: " + e.getMessage());
                }
            }
        }
    }


    /**
     * Denna metod beräknar den totala kostnaden för en order.
     * @param order Ordern som ska beräknas.
     * @return Den totala kostnaden för ordern.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    private double calculateTotalCost(ArrayList<String> order) {
        double totalCost = 0;
        Pattern pattern = Pattern.compile("Price: (\\d+\\.\\d+)");
        for (String item : order) {
            Matcher matcher = pattern.matcher(item);
            if (matcher.find()) {
                totalCost += Double.parseDouble(matcher.group(1));
            }
        }
        return totalCost;
    }


    /**
     * Denna metod uppdaterar vyn till att visa tårtmenyn.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;
        String[] cakeMenuString = new String[cakes.size()];

        for (int i = 0; i < cakes.size(); i++) {
            Cake cake = cakes.get(i);
            StringBuilder fillings = new StringBuilder();
            for (Filling filling : cake.getFillings()) {
                if (fillings.length() > 0) {
                    fillings.append(", ");
                }
                fillings.append(filling.toString());
            }
            int numberOfPieces = cake.getNumberOfPieces();
            cakeMenuString[i] = cake.getName() + " - Price: " + cake.getPrice() + " - Fillings: " + fillings + " - Pieces: " + numberOfPieces;
        }

        view.populateLeftPanel(cakeMenuString);
        view.populateRightPanel(currentOrderArray);
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder));
        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }


    /**
     * Denna metod uppdaterar vyn till att visa menyn för stycksaker.
     * Metoden uppdaterar också priset för den valda ordern.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;
        view.populateLeftPanel(perUnitItemMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    /**
     * Denna metod uppdaterar vyn till att visa menyn för orderhistorik.
     * Den hanterar också felhantering om det inte finns några ordrar i historiken.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();

        if (orderHistory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no orders in the order history.");
            view.enableAllButtons();
            return;
        }

        List<String> formattedOrderHistory = new ArrayList<>();

        int totalPrice = 0;
        for (ArrayList<String> order : orderHistory) {
            totalPrice += calculateTotalCost(order);
        }
        int orderNumber = 1;
        for (ArrayList<String> order : orderHistory) {
            formattedOrderHistory.add("Order " + orderNumber + ":" + " - Total cost: " + calculateTotalCost(order));
            orderNumber++;
        }

        orderHistoryMenuString = formattedOrderHistory.toArray(new String[0]);
        view.populateLeftPanel(orderHistoryMenuString);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();

    }


    /**
     * Denna metod skapar en ny tårta och lägger till den i listan.
     * Den hanterar också felhantering om något fält är tomt.
     * @Author: Josef Zakaria
     * @Author: Alper Eken
     */
    public void placeOrder() {

        if (!currentOrder.isEmpty()) {
            orderHistory.add(new ArrayList<>(currentOrder));



            costCurrentOrder = 0;
            currentOrder.clear();
            currentOrderArray = new String[0];
            view.clearRightPanel();
            view.setTextCostLabelRightPanel("TOTAL COST: 0");
            view.enableAllButtons();
            view.disableAddMenuButton();
            view.disableViewSelectedOrderButton();

        } else {
            JOptionPane.showMessageDialog(null, "You need to add items to the order before you can place it.");
        }

    }

}
