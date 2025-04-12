package com.sasibhumaraju.service;

import com.sasibhumaraju.App;
import com.sasibhumaraju.DAO.UserDAO;
import com.sasibhumaraju.model.AppUser;

import java.util.Scanner;

public class AuthService {

    private final UserDAO userDAO;
    private final Scanner sc;

    AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.sc = new Scanner(System.in);
    }

    public void Login() {

        System.out.println("\n\nEnter email id login:");
        String email = sc.next();
        AppUser appUser = userDAO.getUserByEmail(email);
        if(appUser == null) System.out.println("User not found...");
        else {
            System.out.printf("\n\nHello.. %s ( %s )",appUser.getEmail(),appUser.getRole());
            App.appUser = appUser;
        }
    }

    public void Logout() {
        App.appUser = null;
        System.out.println("\n\nLogged out successfully..\n\n");
    }
}
