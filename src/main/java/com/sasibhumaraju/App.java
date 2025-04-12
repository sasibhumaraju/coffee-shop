package com.sasibhumaraju;

import com.sasibhumaraju.model.AppUser;
import com.sasibhumaraju.model.Coffee;
import com.sasibhumaraju.model.Role;
import com.sasibhumaraju.service.AppUserService;
import com.sasibhumaraju.service.AuthService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App
{

    public static AppUser appUser = null;
    public static void main( String[] args )
    {
        ApplicationContext context =  new ClassPathXmlApplicationContext("beans.xml");
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;

        AppUserService appUserService = context.getBean("app_user_service", AppUserService.class);
        AuthService authService = context.getBean("auth_service", AuthService.class);

        while(isLoop) {

            if( appUser == null) {
                System.out.println("--- Welcome to Coffee Shop ---");
                System.out.println("Enter option:\n1) Add New User \n2) Login ( Coffee Maker/ Coffee Taker ) \n3) Show Users \n4) Exit..");
                int option = sc.nextInt();

                switch (option) {
                    case 1: {
                        appUserService.addNewAppUser(context);
                        break;
                    }

                    case 2: {
                        authService.Login();
                        break;
                    }
                    case 3: {
                        appUserService.showAllUsers();
                        break;
                    }
                    case 4: {
                        isLoop = false;
                        break;
                    }
                    default:
                        System.out.println("select valid option..");
                }
            } else {
                if(appUser.getRole() == Role.COFFEE_TAKER) {
                    System.out.println("\n\n\nSelect option:");
                    System.out.println("1) Show Coffee Menu \n2) Order coffee \n3) See active orders \n4) See history of orders \n5) Logout \n6) Exit..");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1: {
                            appUserService.showCoffeeMenu();
                            break;
                        }
                        case 2: {
                            appUserService.orderCoffee(context);
                            break;
                        }
                        case 3: {
                            appUserService.seeActiveOrders();
                            break;
                        }
                        case 4: {
                            appUserService.seeHistoryOfOrders();
                            break;
                        }
                        case 5: {
                            authService.Logout();
                            break;
                        }
                        case 6: {
                            isLoop = false;
                            break;
                        }
                        default:
                            System.out.println("Invalid option..");
                    }
                } else {
                    System.out.println("\n\n\nSelect option:");
                    System.out.println("1) Work on coffee orders \n2) See history of orders in shop \n3) Logout \n4) Exit.. \n5) Show Coffee Menu \n6) Modify Coffee Menu");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1: {
                            appUserService.workOnCoffeeOrders();
                            break;
                        }
                        case 2: {
                            appUserService.seeHistoryOfOrdersInShop();
                            break;
                        }
                        case 3: {
                            authService.Logout();
                            break;
                        }
                        case 4: {
                            isLoop = false;
                            break;
                        }
                        case 5: {
                            appUserService.showCoffeeMenu();
                            break;
                        }
                        case 6: {
                            appUserService.modifyCoffeeMenu(context);
                            break;
                        }
                        default:
                            System.out.println("Invalid option..");
                    }

                }
            }


        }

    }
}
