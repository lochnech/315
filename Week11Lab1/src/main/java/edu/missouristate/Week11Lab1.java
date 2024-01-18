// Keegan Spell
package edu.missouristate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Week11Lab1 {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("./numbers.txt");
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }

    }

}