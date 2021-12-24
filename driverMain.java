//Author: @Rishi Meka

import java.lang.*;
import java.util.*;

public class driverMain {
    public static void main(String[] args) {
        // Binary tree setup
        char[] morseBT = {' ', 'E', 'T', 'I', 'A', 'N', 'M', 'S', 'U', 'R', 'W', 'D', 'K', 'G', 'O', 'H', 'V', 'F', ' ', 'L', ' ', 'P', 'J', 'B', 'X', 'C', 'Y', 'Z', 'Q'};
        // Printing out the menu system
        System.out.println("*-----* Morse Code Converter *-----*");
        System.out.println("*---* 1. Convert from Morse Code\n" +
                           "*---* 2. Convert to Morse Code");
        System.out.print("*---* Selection: ");
        // Switch for the user input
        switch (new Scanner(System.in).nextInt()){
            case 1:
                // Getting the morse code input from the user and calling the recursive method convertFromMorse
                System.out.print("*-* Morse Code Input: ");
                convertFromMorse(new Scanner(System.in).nextLine(), "", morseBT, 0);
                break;
            case 2:
                // Getting the morse code input from the user and calling the recursive method convertToMorse
                System.out.print("*-* English Input: ");
                String userInput = new Scanner(System.in).nextLine().toUpperCase();
                String input = "";
                // If the char at 0 of the userInput string is a letter, we add that letter to the input string.
                while(!userInput.isEmpty()){
                    if(Character.isLetter(userInput.charAt(0)))
                        input += userInput.charAt(0);
                    userInput = userInput.substring(1);
                }
                convertToMorse(input, "", morseBT, 0);
            default:
                // In the case of an invalid input
                System.out.println("*---* Invalid Input *---*");
                break;
        }
    }
    // Converts from morse code to english. @Param String input, String output, Char[] BinaryTree, int positionToSearch
    public static void convertFromMorse(String input, String output, char[] BT, int position){
        // Base case: if the input string is empty, complete the output string and print it out.
        if(input.isEmpty()) {
            output += BT[position];
            System.out.print("*-* English Output: ");
            System.out.println(output);
            System.exit(0);
        }
        // If there is a space in the input string, add a space in the output string, and reset the position variable
        if(input.charAt(0) == ' '){
            output += BT[position] +  " ";
            position = 0;
        }
        // If input string contains a char that is not '.' or '-', the system will print an error statement, and exits.
        else if(input.charAt(0) != '.' && input.charAt(0) != '-'){
            System.out.println("*---* Invalid Input *---*");
            System.exit(0);
        }
        // If the char at 0 of the input = '.' then we move to the left child of the node
        else if(input.charAt(0) == '.'){
            position = (2 * position + 1);
        }
        // If the char at 0 of the input = '-' then we move to the right child of the node
        else if(input.charAt(0) == '-'){
            position = (2 * position + 2);
        }
        // Recursively calling this method by resizing the input string
        convertFromMorse(input.substring(1), output, BT, position);
    }
    // Converts from english to morseCode. @Param String input, String output, Char[] BinaryTree, int positionToSearch
    public static void convertToMorse(String input, String output, char[] BT, int position){
        // If the position is larger than the length of the binary tree, return
        if(position >= BT.length)
            return;
        // Base case: If the input string is empty, print out the output string, and exit the program.
        if(input.isEmpty()){
            System.out.print("*-* Morse Code Output: ");
            System.out.println(output);
            System.exit(0);
        }
        // Because we are using a binary tree, by definition the position in the array of any left child is going to be
        // an odd number, and the position of any right child is going to be even. Using the binary tree we know that
        // if we move to the left, we add a '.' to the output string and if we move right, we add a '-' to the output
        // By this logic, we can use the mod operator to check if the position is an even number or an odd number and
        // add a '-' or a '.' accordingly.
        if(position != 0 && position % 2 == 0)
            output += '-';
        else if(position != 0 && position % 2 != 0)
            output += '.';
        // When we get a hit on the letter we are searching for, we add a space to the output string and reset position
        if(BT[position] == input.charAt(0)){
            output += " ";
            input = input.substring(1);
            position = 0;
        }
        // Recursively calling the right child and the left child
        convertToMorse(input, output, BT, (2 * position + 2));
        convertToMorse(input, output, BT, (2 * position + 1));
    }
}

