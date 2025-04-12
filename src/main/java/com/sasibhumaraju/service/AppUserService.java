package com.sasibhumaraju.service;

import com.sasibhumaraju.App;
import com.sasibhumaraju.DAO.CoffeeDAO;
import com.sasibhumaraju.DAO.CoffeeOrderDAO;
import com.sasibhumaraju.DAO.UserDAO;
import com.sasibhumaraju.model.*;
import jakarta.persistence.criteria.Order;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AppUserService {


    private final UserDAO userDAO;
    private final CoffeeDAO coffeeDAO;
    private final CoffeeOrderDAO coffeeOrderDAO;
    private Scanner sc;

    AppUserService( UserDAO userDAO, CoffeeDAO coffeeDAO, CoffeeOrderDAO coffeeOrderDAO) {
        this.sc = new Scanner(System.in);
        this.userDAO = userDAO;
        this.coffeeDAO = coffeeDAO;
        this.coffeeOrderDAO = coffeeOrderDAO;
    }

    public void addNewAppUser(ApplicationContext context) {

        System.out.println("\n\nEnter Email:");
        String email = sc.next();
        sc.nextLine();
        System.out.println("Enter Name:");
        String name = sc.nextLine();
        System.out.println("Select Role: (1)-CoffeeMaker (2)-CoffeeTaker (default)");
        int role = sc.nextInt();

        AppUser newUser = context.getBean("new_user", AppUser.class);
        newUser.setEmail(email);
        newUser.setName(name);
        if (role == 1) newUser.setRole(Role.COFFEE_MAKER);
        else newUser.setRole(Role.COFFEE_TAKER);

        userDAO.registerUser(newUser);
        System.out.println("New user added..\n\n");
    }

    public void showAllUsers() {
        List<AppUser> appUsers = userDAO.getAllUsers();
        if(appUsers.isEmpty()) {
            System.out.println("No Users..");
            return;
        }
        System.out.println("--- All Coffee Shop Users ---");
        for(int i = 0; i<appUsers.size(); i++) {
            AppUser u = appUsers.get(i);
            System.out.println((i+1) + ") Name: " + u.getName() + ",\t\t Email: " + u.getEmail() + ",\t\t Role: " + u.getRole());
        }
        System.out.println("\n\n");
    }

    public void orderCoffee(ApplicationContext context) {

        List<Coffee> coffees = coffeeDAO.getAllCoffees();
        for(int i = 0; i<coffees.size(); i++) {
            System.out.println((i+1) + ") Name:" + coffees.get(i).getName() + ", Price: " + coffees.get(i).getPrice() + ", Description: " + coffees.get(i).getDescription());
        }
        int n = 0;
        do {
            System.out.println("Select valid Coffee (num) from 1 to " + coffees.size() + ": ");
            n = sc.nextInt();
        } while (n < 1 || n > coffees.size());

        Coffee coffee = coffees.get(n-1);

        CoffeeOrder coffeeOrder = context.getBean("order",CoffeeOrder.class);
        coffeeOrder.setCoffee(coffee);
        coffeeOrder.setUser(App.appUser);
        coffeeOrder.setStatus(Status.PLACED);
        coffeeOrderDAO.placeOrder(coffeeOrder);
        System.out.println("Order placed..\n\n");
    }

    public void seeActiveOrders() {

        List<CoffeeOrder> activeOrders = coffeeOrderDAO.getAllActiveOrdersByEmail(App.appUser.getEmail());
        System.out.println("List of your active coffee orders: " + "( count = " + activeOrders.size() + " )");
        for(int i = 0; i<activeOrders.size(); i++) {
            CoffeeOrder co = activeOrders.get(i);
            System.out.println((i+1) + ") Name: " + co.getCoffee().getName() + ", Price: " + co.getCoffee().getPrice() + ", Status: "  + co.getStatus());
        }
        System.out.println("\n");
    }

    public void seeHistoryOfOrders() {

        List<CoffeeOrder> activeOrders = coffeeOrderDAO.getAllOrdersHistoryByEmail(App.appUser.getEmail());
        System.out.println("List of all your history coffee orders: " + "( count = " + activeOrders.size() + " )");
        for(int i = 0; i<activeOrders.size(); i++) {
            CoffeeOrder co = activeOrders.get(i);
            System.out.println((i+1) + ") Name: " + co.getCoffee().getName() + ", Price: " + co.getCoffee().getPrice() + ", Status: "  + co.getStatus());
        }
        System.out.println("\n");

    }

    public void workOnCoffeeOrders() {

        System.out.println("Below are the active coffee orders in shop: ");
        List<CoffeeOrder> activeCoffeeOrdersInShop = coffeeOrderDAO.getAllActiveOrdersInShop();
        if(activeCoffeeOrdersInShop.isEmpty()) {
            System.out.println("No active coffee orders..");
            return;
        }
        for(int i = 0; i < activeCoffeeOrdersInShop.size(); i++) {
            CoffeeOrder co = activeCoffeeOrdersInShop.get(i);
            System.out.println((i+1) + ") Name: " + co.getCoffee().getName() +  ", User: " + co.getUser().getName() + " / " + co.getUser().getEmail() +  ", Price: " + co.getCoffee().getPrice() + ", Status: "  + co.getStatus());
        }

        int n = 0;
        do {
            System.out.println("To work on next state of order, Select valid Coffee order num from 1 to " + activeCoffeeOrdersInShop.size() + ": ");
            n = sc.nextInt();
        } while (n < 1 || n > activeCoffeeOrdersInShop.size());

        System.out.println("\nYou selected below order:");
        CoffeeOrder coffeeOrder1 = activeCoffeeOrdersInShop.get(n-1);
        System.out.println(n + ") Name: " + coffeeOrder1.getCoffee().getName() + ", User: " + coffeeOrder1.getUser().getName() + " / " + coffeeOrder1.getUser().getEmail() +  ", Price: " + coffeeOrder1.getCoffee().getPrice() + ", Status: "  + coffeeOrder1.getStatus());

        int i = coffeeOrder1.getStatus().ordinal();
        System.out.println("Change Order status to -> " + Status.values()[i+1]);
        System.out.println("Yes/No: (Y/N)");
        sc.nextLine();
        String s = sc.nextLine().toLowerCase().substring(0,1);
        if(s.equals("n")) return;
        coffeeOrder1.setStatus(Status.values()[i+1]);

        coffeeOrderDAO.updateOrder(coffeeOrder1);
        System.out.println("Status updated..");
        System.out.println(n + ") Name: " + coffeeOrder1.getCoffee().getName() + ", User: " + coffeeOrder1.getUser().getName() + " / " + coffeeOrder1.getUser().getEmail() +  ", Price: " + coffeeOrder1.getCoffee().getPrice() + ", Status: "  + coffeeOrder1.getStatus());
        System.out.println("\n");

    }

    public void seeHistoryOfOrdersInShop() {
        List<CoffeeOrder> activeOrders = coffeeOrderDAO.getAllOrdersHistoryInShop();
        System.out.println("List of all orders history: " + "( count = " + activeOrders.size() + " )");
        float income = 0;
        for(int i = 0; i<activeOrders.size(); i++) {
            CoffeeOrder co = activeOrders.get(i);
            System.out.println((i+1) + ") Name: " + co.getCoffee().getName() + ", User: " + co.getUser().getName() + " / " + co.getUser().getEmail() +  ", Price: " + co.getCoffee().getPrice() + ", Status: "  + co.getStatus());
            income += co.getCoffee().getPrice();
        }

        System.out.println("\n Total Gross Income: Rs. "+ income);
        System.out.println("\n\n");
    }

    public List<Coffee> showCoffeeMenu() {
        System.out.println("--- Below is the Coffee Menu in Shop ---");
        List<Coffee> coffees = coffeeDAO.getAllCoffees();
        if(coffees.isEmpty()) System.out.println("No Coffee Menu in shop");
        else {
            for(int i = 0; i<coffees.size(); i++) {
                Coffee c = coffees.get(i);
                System.out.println((i+1) + ") Name: " + c.getName() + ", Price: " + c.getPrice() + ", Description: " + c.getDescription() );
            }
        }
        return coffees;
    }

    public void modifyCoffeeMenu(ApplicationContext context) {
        showCoffeeMenu();
        boolean isLooping = true;
        while (isLooping) {
            System.out.println("\n Select an option: \n1) Add New Coffee \n2) Modify Existing Coffee \n3) Delete Coffee \n4) Exit");
            int option = sc.nextInt();
            switch (option) {

                case 1:{
                    sc.nextLine();
                    System.out.println("Enter Coffee Name: ");
                    String coffeeName = sc.nextLine();
                    System.out.println("Enter Coffee Description: ");
                    String description = sc.nextLine();
                    System.out.println("Enter Coffee Price: ");
                    float price = sc.nextFloat();


                    Coffee coffee = context.getBean("coffee", Coffee.class);
                    coffee.setName(coffeeName);
                    coffee.setPrice(price);
                    coffee.setDescription(description);

                    coffeeDAO.saveCoffee(coffee);
                    System.out.println("New Coffee Added..");
                    break;
                }
                case 2: {
                    List<Coffee> coffess = showCoffeeMenu();
                    if(coffess.isEmpty()) {
                        System.out.println("Coffees menu is empty");
                        break;
                    }

                    int n = 0;
                    do {
                        System.out.println("Select Coffee num to modify: ( 1 to " + coffess.size() + " )");
                        n = sc.nextInt();
                    } while (n < 1 || n > coffess.size());
                    Coffee c = coffess.get(n-1);
                    sc.nextLine();

                    System.out.println("Enter new coffee name: (old name: " + c.getName() + " )");
                    String newName = sc.nextLine();
                    System.out.println("Enter new description: ( old description: " + c.getDescription() + " )");
                    String d = sc.nextLine();
                    System.out.println("Enter new coffee price: (old price: " + c.getPrice() + " )");
                    float p = sc.nextFloat();

                    c.setName(newName);
                    c.setDescription(d);
                    c.setPrice(p);

                    coffeeDAO.updateCoffee(c);
                    System.out.println("Updated coffee..");
                    break;
                }
                case 3: {
                    List<Coffee> coffees = showCoffeeMenu();
                    if(coffees.isEmpty()) {
                        System.out.println("Coffees menu is empty");
                        break;
                    }

                    int n = 0;
                    do {
                        System.out.println("Select Coffee num to delete: ( 1 to " + coffees.size() + " )");
                        n = sc.nextInt();
                    } while (n < 1 || n > coffees.size());
                    Coffee c = coffees.get(n-1);
                    coffeeDAO.deleteCoffee(c);
                    System.out.println("Coffee deleted...");

                    break;
                }
                case 4: {
                    isLooping = false;
                    break;
                }
                default:{
                    System.out.println("enter valid option...");
                }
            }
        }
    }

}
