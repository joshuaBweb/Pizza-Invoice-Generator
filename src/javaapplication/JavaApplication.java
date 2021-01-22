package javaapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * Programming ISU Assignment
 * Joshua Bastien
 * 01/20/2020
 * 
 */
public class JavaApplication {
        
    //Method to return the number of lines in the user text file.
    public static int getNumOfLines(String path) throws IOException{
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader txtReader = new BufferedReader(fr);
                int numOfLines = 0;
                while (txtReader.readLine()!=null) {
                numOfLines++;
                }
                txtReader.close();
            return numOfLines;
            
            }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    //Method to read the text file and put each line of it into a String array
    public static String[] readFile (String path) throws IOException {
        
        int numOfLines = getNumOfLines(path);
        String textData[] = new String[numOfLines];
        if (numOfLines >0) {
            FileReader fr = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fr);
            for (int i=0; i<numOfLines; i++) {
                textData[i] = textReader.readLine();
            }
            textReader.close(); 
        }
        return textData;
    }
    
        //Method to create an array filled with all of the toppping names.
        public static String[] getToppingsName() {
        
        String toppingsName[] = {"Sausage","Pepperoni","Bacon","Ham","Lean Beef","Chicken","Anchovies","Mushrooms","Fresh Mushrooms","Green Peppers",
        "Sliced Tomatoes","Black Olives","Green Olives","Onions","Hot Peppers","Pineapple","Garlic","Red Peppers","Basil","Extram Cheese","Artichoke",
        "Roasted Peppers","Sun Dried Tomatoes", "Feta Cheese","Provolone Cheese","Romano Cheese","Parmigiano Cheese"};
        
        return toppingsName;
    }
    
    //Method to create an array filled with all of the topping codes.
    public static String[] getTopsCode() {
        
        String topsCode[] = {"sausage","pepperoni","bacon","ham","beef","chicken","anchovies","mushrooms","freshmush","greenpepp","slicetom","blackoliv",
        "greenoliv","onions","hotpepp","pineapple","garlic", "redpepp","basil","xcheese","artichoke","roastedpepp","suntom","feta","provolone","romano","parmigiano"};
        
        return topsCode;
    }
        //Method to a number to 2 decimal places.

    //Method to a number to 2 decimal places.
    public static double round(double numToRound) {
        numToRound = Math.round(numToRound*100);
        return numToRound/100;
    }
    
    //Method to retrieve and specifically assign the code topping name (ex: s) for the size to it's actual name (ex: Small).
    public static String showSize(char s) {
            switch(s) {
            case 'p':
                return "Personal";
            case 's':
                return "Small";
            case 'm':
                return "Medium";
            case 'l':
                return "Large";
            case 'q':
                return "Queen";
            case 'k':
                return "King";
        }
        // If an invalid character is used, the program will display this.
        return "Invalid size.";
    }
    
    //Method to list out the topping on the pizza
    public static String showToppings(Pizza pizza, String[] toppings) {
        boolean[] pizzaTop = pizza.getToppings();
        String str = "";
        //loops through pizzaTop[] boolean array to format and display topping name from toppings[] String array
        for (int i = 0; i < 27; i++) {
                if (pizzaTop[i] == true) {
                    str = str + " " + toppings[i] + ",";
            }
        }
        //Removes the last comma at the end of the list of toppings.
        str = str.substring(0, str.length() - 1);
        return str;
    }
 
    
    //Method to check if the user has ordered a special.
    public static int getSpecial(String topping, String[] spec) {
        
        //Will loop through the 5 specials here.
        for (int i=0; i < 6; i++) {
            if (topping.equals(spec[i])) {
                return i;
            }
        }
        
        //Will return -1 if user has not ordered a special.
        return -1;
    }
    
    //Method to find the index of the topping in the array. 
    public static int getToppingIndex(String topping, String[] toppings) {
        for (int i=0; i < 27; i++) {
            if (topping.equals(toppings[i])) {
                return i;
            }
        }
        //A return of -1 means it's a specialty.
        return -1;
    }
   
    //Biggest Method used to create the invoice and write to the text file.
    public static void writeInvoice(String path, String[] textData, String deliver, Pizza[] pizzas, String[] toppings) throws IOException {
        //Variables here
        FileWriter fw = new FileWriter(path, false);
        BufferedWriter bw = new BufferedWriter(fw);
        double disPrice = 1;
        String disPercent = textData[textData.length-3].substring(1);
        double total = 0;
        double tip;
        int count = 0;
        
        //Writes top line of stars 
        for (int x=0; x<130; x++) {
            bw.write("*");
        }
        bw.newLine();
        //Pizzaria info
        bw.write(String.format("%74s","Josh's Pizzeria\n"));
        bw.write(String.format("%75s","865 New York Road\n"));
        bw.write(String.format("%72s","Windsor, ON\n"));
        bw.write(String.format("%70s","N9A 2Y3\n"));
        //Writes bottom line of stars.
        for (int x=0; x<130; x++) {
            bw.write("*");
        }
        bw.write("\n");
        //Outputting customer info.
        bw.write("Customer: \t" + textData[0] + ", " + textData[1]+"\n");
        bw.write("\t\t" + textData[2] +"\n");
        bw.write("\t\t" + textData[3] +"\n");
        bw.write("\t\t" + textData[4] +"\n");
        bw.write("\t\t" + textData[5] +"\n\n");
        //Outputting delivery or pickup and instructions if necessary.
        if(deliver.equals("true")) {
        bw.write("Delivery" +"\n");
        } else {
        bw.write("Pick up" +"\n");
        }
        bw.write(textData[textData.length-1]);
        bw.write("\n\n");
        //Formatting and writing more text
        bw.write("Number of Pizzas: " + textData[6]);
        bw.write("\n\nPizza");
        bw.write(String.format("%125s", "Price\n"));
        //Writing long horizontal line
        for (int x=0; x<130; x++) {
            bw.write("_");
        }
        bw.newLine();
        //Writing each pizza (using enhanced for loop) with their size, toppings, price and possibly which specialty it is.
        for (Pizza pizza : pizzas) {
            count++;
            //Formatting values mentioned above using String.format
            bw.write(String.format("%s %-20s %101s", count +".", "\tSize: " + showSize(pizza.getSize()), "$" + pizza.calculatePrice()) + "\n");
            if (pizza.getSpecialty().equals("none")) {
                //Outputing Toppings
                bw.write("\tToppings: " + showToppings(pizza, toppings) + "\n\n");
            } else {
                //Outputting Specialty
                bw.write("\tSpecialty: " + pizza.getSpecialty() + " (" + showToppings(pizza, toppings) + ")" + "\n\n");
            }
        //Calculating and outputting price calculations in proper format
            total = pizza.calculatePrice()+total;
        }
        bw.write("\n");
        //Subtotal
        bw.write(String.format("%117s %12s", ("Subtotal:"), "$"+ round(total)) + "\n");
        
        //%10 Discount
        if (textData[textData.length-2].equals("DISCOUNT")) {
            disPrice = 0.9;
            bw.write(String.format("%117s %12s", ("Discount (%10):"), "-$"+ round(total-(total*disPrice))) + "\n");
        }
        //%15 Discount
        else if (textData[textData.length-2].equals("COMPSCI")) {
            disPrice = 0.85;
            bw.write(String.format("%117s %12s", ("Discount (15%):"), "-$"+ round(total-(total*disPrice))) + "\n");
        }
        total = total*disPrice;                     
        bw.newLine();
        //HST
        bw.write(String.format("%117s %12s", ("HST (13%):"), "$"+ round(total*0.13)) + "\n");
        //Delivery if applicable
        if (deliver.equals("true")) {
        bw.write(String.format("%117s %12s", ("Delivery:"), "$" + "5.00") +"\n");
        }
        //Tip dollar amount
        if (textData[textData.length-3].charAt(0) == '$') {
            tip = Integer.parseInt(disPercent);
            bw.write(String.format("%117s %12s", "Tip:", "$" + round(tip)) +"\n");
        }
        else {
        //Tip percentage
            tip = Double.parseDouble(disPercent);
            tip = total*(tip/100);
            bw.write(String.format("%117s %12s", "Tip (" +Integer.parseInt(disPercent)+"%):", "$" + round(tip)) +"\n");
        }
        
        //Adding tax and delivery fee to total.
        total = total*1.13;
        total = total + tip +5;
        //Total
        bw.write(String.format("%117s %12s", "TOTAL:", "$" + round(total)) +"\n\n");
        bw.write("*Thank you for choosing Josh's Pizzeria\n");
        bw.write("You can't spell 'Josh's Pizzeria' without 'Quality'!");
        bw.close();
    }
    
    public static void main(String[] args) throws IOException {
        //Variables here
        String path =  "C:\\Users\\remem\\Desktop\\pizzaorder.txt";
        String[] data = readFile(path);
        String[] toppings = getTopsCode(); 
        String[] fulltoppings = getToppingsName(); 
        //ParseInt here changes the text in the String data[] array to an int.
        Pizza[] pizzas = new Pizza[Integer.parseInt(data[6])];
        int count = 0;
        int index;
        String deliver = "true";
        String[] specialty = {"deluxe", "super", "vegetarian", "sicilian", "greek", "hawaiian"};
        
        
        for (int i = 7; i < data.length; i++) {
            if (!(data[i].equals("true"))) {
                
                //This line checks if the user has entered any illegal characters.
                if (data[i].matches("[a-zA-Z]+") && data[i].length() < 2) {
                    pizzas[count] = new Pizza(data[i].charAt(0));
                    for (int x = 0; x < Integer.parseInt(data[i+1]); x++) {
                        index = getToppingIndex(data[i+2+x], toppings);
                        
                        //If the pizza is a specialty, the program will run through this code and set all the necessary toppings.
                        if (index == -1) {
                            index = getSpecial(data[i+2+x], specialty);
                            switch(index) {
                                case 0:
                                    pizzas[count].setSpecialty("The Deluxe");
                                    pizzas[count].setToppings(true, 0);
                                    pizzas[count].setToppings(true, 7);
                                    pizzas[count].setToppings(true, 9);
                                    break;
                                case 1:
                                    pizzas[count].setSpecialty("The Super");
                                    pizzas[count].setToppings(true, 0);
                                    pizzas[count].setToppings(true, 2);
                                    pizzas[count].setToppings(true, 7);
                                    pizzas[count].setToppings(true, 9);
                                    break;
                                case 2:
                                    pizzas[count].setSpecialty("The Vegetarian");
                                    pizzas[count].setToppings(true, 7);
                                    pizzas[count].setToppings(true, 9);
                                    pizzas[count].setToppings(true, 10);
                                    pizzas[count].setToppings(true, 11);
                                    break;
                                case 3:
                                    pizzas[count].setSpecialty("The Sicillian");
                                    pizzas[count].setToppings(true, 10);
                                    pizzas[count].setToppings(true, 13);
                                    pizzas[count].setToppings(true, 16);
                                    pizzas[count].setToppings(true, 24);
                                    pizzas[count].setToppings(true, 25);
                                    pizzas[count].setToppings(true, 26);
                                    break;
                                case 4:
                                    pizzas[count].setSpecialty("The Greek");
                                    pizzas[count].setToppings(true, 10);
                                    pizzas[count].setToppings(true, 11);
                                    pizzas[count].setToppings(true, 13);
                                    pizzas[count].setToppings(true, 23);
                                    break;
                                case 5:
                                    pizzas[count].setSpecialty("The Hawaiian");
                                    pizzas[count].setToppings(true, 3);
                                    pizzas[count].setToppings(true, 15);
                                    break;
                            }
                        }
                        else {
                            //Code if there is no specialty.
                            pizzas[count].setSpecialty("none");
                            pizzas[count].setToppings(true, index);
                        }
                    }
                    //To calculate final price using calculatePrice() method from Pizza.class 
                    pizzas[count].calculatePrice();
                    count++;
                }
            } else {
                //Checking if user is getting the pizza delivery or pick up.
                deliver = data[i];
                break;
            }
        }
        //Program will toss all of this information to the writeInvoice method where it will be able to finish calculations and display it all.
        writeInvoice(( "C:\\Users\\remem\\Desktop\\invoice.txt"), data, deliver, pizzas, fulltoppings);
    }
}