package javaapplication;

/**
 *
 * Programming ISU Assignment
 * Joshua Bastien
 * 01/20/2020
 * 
 */
public class Pizza {
    
    private char size;
    private boolean[] toppings = new boolean[27];
    private String specialty;

    //Constructor that sets the input parameter, sets the specialty to "none" and initializes all elements in the toppings array to false. 
    public Pizza(char size) {
        this.size = size;
        
        specialty = "none";
        
        for(int i =0; i > 26; i++) {
            toppings[i] = false;
        }
        
    }
    //Getters
    public char getSize() {
        return size;
    }   
    
    public boolean[] getToppings() {
        return toppings;
    }
    
    public String getSpecialty() {
        return specialty;
    }

    //Setters
    public void setSize(char size) {
        this.size = size;
    }
    
    public void setToppings(boolean topping, int index) {
        this.toppings[index] = topping;
    }


    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    //Retrieving special prices method
    private double getSpecialP(char size, double[] prices) {
        switch(size) {
            case 'p':
                return prices[0];
            case 's':
                return prices[1];
            case 'm':
                return prices[2];
            case 'l':
                return prices[3];
            case 'q':
                return prices[4];
            case 'k':
                return prices[5];
        }
        return prices[0];
    }
    //Method to round a number to 2 decimal places.
    public static double round(double numToRound) {
        numToRound = Math.round(numToRound*100);
        return numToRound/100;
    }
    
    //Calculate price method to return price rounded to two decimal places
    public double calculatePrice() {
        //Declaring/Initializing variables
        double sizePrice = 0;
        double regPrice = 0;
        double gourmetPrice = 0;
        double totalPrice = 0;
        int numRegTops = 0;
        int numGourmetTops = 0;
            //Checking each possible size the user could have ordered and setting the prices for the size they chose
            //"Personal"
            if (size == 'p') {
                sizePrice = 8.84;
                regPrice = 0.48;
                gourmetPrice = 0.60;
            }
            //"Small"
            if (size == 's') {
                sizePrice = 13.26;
                regPrice = 0.88;
                gourmetPrice = 0.94;
            }
            //"Medium"
            if (size == 'm') {
                sizePrice = 17.68;
                regPrice = 0.88;
                gourmetPrice = 0.94;
            }
            //"Large"
            if (size == 'l') {
                sizePrice = 21.23;
                regPrice = 0.88;
                gourmetPrice = 0.94;
            }
            //"Queen"
            if (size == 'q') {
                sizePrice = 30.08;
                regPrice = 1.77;
                gourmetPrice = 1.98;
            }
            //"King"
            if (size == 'k') {
                sizePrice = 36.27;
                regPrice = 1.77;
                gourmetPrice = 1.98;
            }
        
        //Counting number of reugular toppings user entered -Stored in numRegTops
        for(int i=0; i < 20; i++) {
            if (toppings[i] == true) {
                numRegTops = numRegTops+1;
            }
        }
        //Counting number of gourmet topping user entered -Stored in numGourmetTops    
        for(int i=20; i<27; i++) {
            if (toppings[i] == true) {
                numGourmetTops = numGourmetTops+1;
            }
        }
        
        //Calculating final price if no specialty
        if(specialty.equals("none")) {
            totalPrice = sizePrice + ((numRegTops*regPrice)+(numGourmetTops*gourmetPrice));
        }
        
        //If there is a specialty:
        else {
            
            //Putting the different specialty prices for each size in an array. Note: 2nd and 3rd array prices are used for two specialties.
            double[] price1 = {10.41, 15.62, 20.34, 23.88, 34.50, 40.69};
            double[] price2 = {10.99, 16.8, 21.32, 24.77, 35.39, 41.57};
            double[] price3 = {11.91, 18.17, 23.34, 27.63, 39.25, 46.79};
            double[] price4 = {9.72, 15.03, 19.46, 23, 33.18, 38.92};
            
            //Inputting the special's prices using the getSpecialP method utilising the above arrays along with their size.
            switch(specialty) {
                case "The Deluxe":
                    totalPrice = getSpecialP(size, price1);
                    break;
                case "The Super":
                    totalPrice = getSpecialP(size, price2);
                    break;
                case "The Vegetarian":
                    totalPrice = getSpecialP(size, price2);
                    break;
                case "The Sicillian":
                    totalPrice = getSpecialP(size, price3);
                    break;
                case "The Greek":
                    totalPrice = getSpecialP(size, price3);
                    break;
                case "The Hawaiian":
                    totalPrice = getSpecialP(size, price4);
                    break;
            }
        }
        //Returning final value rounded to two decimal places using round method
        return round(totalPrice);
    }
}