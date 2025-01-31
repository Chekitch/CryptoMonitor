package com.cmlcz.cryptomonitor;

import java.util.Scanner;

import static com.cmlcz.cryptomonitor.CryptoAPI.COIN_TYPES;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static boolean isAppRunning = true;
    private static final CryptoAPI CryptoAPI = new CryptoAPI();

    public static void main(String[] args) {


        // Print the menu
        // get input from the user
        // use API to find current price of the coin

        try{
            while(isAppRunning) {
                launchMenu();
            }
        }catch(UserRequestsExitException e){
            System.out.println(e.getMessage());
        }
    }

    private static void launchMenu()  {

        System.out.println("\n-----------------------------------------\n");
        System.out.println("Which coin price would you like to see? (type exit to quit)\n");
        for(String coinType : COIN_TYPES){
            System.out.println("\t"+coinType);
        }
        System.out.print("\n> ");
        String input = SCANNER.nextLine().toLowerCase();

        if(input.equals("exit")){
            isAppRunning = false;
            throw new UserRequestsExitException("See you later!");
        }

        if(isCoinTypeValid(input)){
            String price = CryptoAPI.getCurrentCoinPrice(input);
            System.out.println("Current price of "+input+" is " + price);
        }else{
            System.out.println("That coin is not supported.");
            launchMenu();
        }
    }

    private static boolean isCoinTypeValid(String input) {
        return COIN_TYPES.contains(input);
    }
}
