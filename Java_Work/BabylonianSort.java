/*
 * Philip Bettler 
 * Ph726065
 */
package babyloniansort;
import java.lang.Math;
import java.util.Arrays;

/**
 *
 * @author Pbettler
 */
public class BabylonianSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
    public static void babylonianSort(String[] numbers) throws NumberFormatException {
        // first find the longest string in the array, max
        int i,j = 0, max;
        char value;
        //Grab amount of numbers to sort
        int arrlen = numbers.length;
        
        //Check for valid sexagesimal 
        for(i = 0; i< arrlen; i++ )
            if(isValidSexagesimalNumber(numbers[i]) != true)
                throw new NumberFormatException("The String entered, " +numbers[i]+ " is invalid.");
        
        //grab the max number of digits within the list
        max = numbers[0].length();
        for(i = 1; i < arrlen; i ++) 
            if(numbers[i].length() > max)
                max = numbers[i].length();
  
        // radix sort through the array of strings
        // sort by each digit place and return values
        for(i = 0; i < max; i++)
            counter(numbers, arrlen, i);
                         
    }
    //count sort for radix sort
    public static void counter(String[] numbers, int n, int place) {
        
        int i, index;
        char value;
        //create occurence counter and final array
        int occur[] = new int[60];
        String sorted[] = new String[n];
        // initialize all values to 0
        Arrays.fill(occur, 0);
        
        // generate amount of times seen per each digit at the given position
        for (i = 0; i < n; i++) {
            // grab the first value of each string, decode its proper place and
            // record occurence in count array
            
            //Check to see whethere the Digits place we're searching for
            //is present in each given number
            //replace missing values with 0
            if((numbers[i].length()-1) - place >= 0)
                value = numbers[i].charAt((numbers[i].length()-1)- place);
            else
                value = 0;
            
            // Find the modified ascii value of the digit
            int ascii = Integer.valueOf(value);
            // check to see if Ascii value is in correct range
            if(ascii >= 97 && ascii <= 122){
                // the character is lowercase
                ascii = ascii - 87;
            }
            else if(ascii >= 65 && ascii <= 88){
                // the char is upppercase
                ascii = ascii - 29;
            }
            else if(ascii >= 48 && ascii <= 57) {
                // the char is a digit
                ascii = ascii - 48;
            }
            index = ascii;
            //record  
            occur[index]++;
        }
        //making it so occur[i] contains correct digit in sorted
        for(i= 1; i < 60; i++)
            occur[i] += occur[i-1];
        // sort the array
        for(i = n-1; i >= 0; i--) {
            
            //Check to see whethere the Digits place we're searching for exists
            //replace missing values with 0
            if((numbers[i].length()-1) - place >= 0)
                value = numbers[i].charAt((numbers[i].length()-1)- place);
            else
                value = 0;
            
            // Find the modified ascii value of the digit
            int ascii = Integer.valueOf(value);
            // check to see if Ascii value is in correct range
            if(ascii >= 97 && ascii <= 122){
                // the character is lowercase
                ascii = ascii - 87;
            }
            else if(ascii >= 65 && ascii <= 88){
                // the char is upppercase
                ascii = ascii - 29;
            }
            else if(ascii >= 48 && ascii <= 57) {
                // the char is a digit
                ascii = ascii - 48;
            }
            index = ascii;
            sorted[occur[index]-1] = numbers[i];
            occur[index]--;
        }
        
        // Rearranging the original array to match sorted array
        for(i = 0; i< n; i++)
            numbers[i] = sorted[i];
    }
    
    public static String decimalToSexagesimal(long number) {
        
        int value;
        String sexagesimal = "";
        String digit;
        
        while(number != 0) {
            // Get the remainder value 
            value = (int) (number % 60);
            number = (number-value) / 60;
            // transform the value into the right Sexagesimal digit
            // then concatenate value onto the beginning of the string
            if(value <= 9){
                // the value is in 0-9
                value = value + 48;
            }
            else if(value > 9 && value <= 35){
                value = value + 87;
            }
            else if(value > 35 && value <= 59){
                value = value + 29;
            }
            // convert value to digit
            digit = String.valueOf((char) value);
            // Concatenate digit sring into the beginning of Sexagesimal String
            sexagesimal = digit + sexagesimal;
        }
        return sexagesimal;
        
    }
    
    public static long sexagesimalToDecimal(String number) throws NumberFormatException,
    ArithmeticException {
        // evaluate the "polynomial" that is really a 
        // sexagesimal number. Use the position of the characters in the string 
        //to pull coefficients. 
        
        int i, digit;
        char value;
        long sum = 0;
        
        //convert number into an array of digits
        // using ascii values, a-z is 97-122
        // A-Z is 65-90
        for(i = number.length()-1; i > 0; i--) {

            value = number.charAt(i);
            
            int ascii = Integer.valueOf(value);
            // check to see if Ascii value is in correct range
            if(ascii >= 97 && ascii <= 122){
                // the character is lowercase
                ascii = ascii - 87;
            }
            else if(ascii >= 65 && ascii <= 88){
                // the char is upppercase
                ascii = ascii - 29;
            }
            else if(ascii >= 48 && ascii <= 57) {
                // the char is a digit
                ascii = ascii - 48;
            }
            else {
                // the char is invalid
                throw new NumberFormatException("The String entered, " +number+ " is invalid.");
            }
            //Convert the number into a decimal
            try {
                sum += ascii * Math.pow(60, i);
            }
            catch (ArithmeticException ae) {
                throw new ArithmeticException("The sexagesimal number is too long to be converted into a long");
            }
        }

        return sum;
    }
    
    public static boolean isValidSexagesimalNumber(String number) {
        
        int i, digit;
        char value;
        
        //convert number into digits
        // using ascii values, a-z is 97-122
        // A-Z is 65-90
        for(i = number.length()-1; i > 0; i--) {

            //check for leading 0's
            if(number.charAt(0)== '0')
                return false;
            value = number.charAt(i);
            int ascii = Integer.valueOf(value);
            // check to see if Ascii value is in correct range
            if(ascii >= 97 && ascii <= 122)
                continue;            
            else if(ascii >= 65 && ascii <= 88)
                continue;
            else if(ascii >= 48 && ascii <= 57) 
                continue;
            else {
                // the number is invalid
                return false;
            }
        }
        return true;
    }         
    
}