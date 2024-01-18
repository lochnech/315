//********************************************************************
//
// Keegan Spell
// Operating Systems
// Programming Project #5: Implementation of Banker's Algorithm
// October 9, 2023
// Instructor: Dr. Siming Liu
//
//********************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class osproj5 {

    static int n;
    static int m;
    static int[][] allocation;
    static int[][] max;
    static int[][] need;
    static int[] available;
    static int[] request;


    public static void main(String[] args) throws FileNotFoundException {


        // set up file
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);


        // a. number of processes
        n = sc.nextInt();
        System.out.println("There are " + n + " processes in the system.\n");


        // b. number of resource types
        m = sc.nextInt();
        System.out.println("There are " + m + " resource types.\n");


        // c. assign allocation matrix
        System.out.println("The Allocation Matrix is...");
        System.out.println("   A B C D ");
        allocation = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        // d. assign max matrix
        System.out.println("The Max Matrix is...");
        System.out.println("   A B C D ");
        max = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
                System.out.print(max[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();


        // e. assign need matrix
        System.out.println("The Need Matrix is...");
        System.out.println("   A B C D ");
        need = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j]-allocation[i][j];
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        // f. assign available vector
        System.out.println("The Available Vector is...");
        System.out.println("A B C D ");
        available = new int[m];
        for (int i = 0; i < m; i++) {
            available[i] = sc.nextInt();
            System.out.print(available[i] + " ");
        }
        System.out.println("\n");


        // g. check safety
        if (checkSafe()) {
            System.out.println("THE SYSTEM IS IN A SAFE STATE!\n");
        } else {
            System.out.println("THE SYSTEM IS NOT IN A SAFE STATE!\n");
        }


        // h. assign request vector
        request = new int[m+1];
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (!str.isEmpty()) {
                System.out.println("The Request Vector is...");
                System.out.println("  A B C D ");
                String[] items = str.split(":| ");
                for (int i = 0; i < m+1; i++) {
                    request[i] = Integer.parseInt(items[i]);
                    System.out.print(request[i] + " ");
                }
            }
        }
        System.out.println();


        // i. check if request can be granted
        System.out.println();
        if (attemptRequest()) {
            System.out.println("THE REQUEST CAN BE GRANTED!\n");
        } else {
            System.out.println("THE REQUEST CAN NOT BE GRANTED!\n");
        }


        // j. compute the new available vector
        System.out.println("The Available Vector is...");
        System.out.println("A B C D ");
        for (int i = 0; i < m; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println("\n");


        sc.close();
    }

    //********************************************************************
    //
    // Attempt Request Function
    //
    // This function computes whether a given request is valid or not
    // using the resource request algorithm discussed in class and in the
    // lecture slides.
    //
    // Return Value
    // ------------
    // boolean                           True/False if request is valid
    //
    //*******************************************************************

    public static boolean attemptRequest() {
        // save old variables for potential restore
        int[] _available = new int[available.length];
        if (m >= 0) System.arraycopy(available, 0, _available, 0, m);
        int[][] _allocation = new int[allocation.length][allocation[0].length];
        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(allocation[i], 0, _allocation[i], 0, m);
        }
        int[][] _need = new int[need.length][need[0].length];
        for (int i = 0; i < n; i++) {
            if (m >= 0) System.arraycopy(need[i], 0, _need[i], 0, m);
        }

        // check if request less than need
        for (int i = 0; i < m; i++) {
            if (request[i+1] > need[request[0]][i]) {
                System.out.println("PROCESS EXCEEDED MAXIMUM CLAIM");
                return false;
            }
        }

        // check if request less than available
        for (int i = 0; i < m; i++) {
            if (request[i+1] > available[i]) {
                System.out.println("RESOURCES UNAVAILABLE");
                return false;
            }
        }

        // assign new values
        for (int i = 0; i < m; i++) {
            available[i] -= request[i+1];
            allocation[request[0]][i] += request[i+1];
            need[request[0]][i] -= request[i+1];
        }

        // if unsafe revert to saved data
        if (!checkSafe()) {
            available = _available;
            allocation = _allocation;
            need = _need;
            return false;
        }

        return true;
    }

    //********************************************************************
    //
    // Check Safety Function
    //
    // This function computes whether the system is currently in a safe
    // state using the safety algorithm discussed in class.
    //
    // Return Value
    // ------------
    // boolean                           True/False if system is safe
    //
    //*******************************************************************

    public static boolean checkSafe() {
        // initialize work and finish arrays
        int[] work = new int[available.length];
        if (m >= 0) System.arraycopy(available, 0, work, 0, m);
        boolean[] finish = new boolean[n];
        for (int i = 0; i < n; i++) finish[i] = false;

        boolean valid;
        boolean working;
        do {
            working = false;
            for (int i = 0; i < n; i++) {
                valid = true;
                // if not finished
                if (!finish[i]) {
                    for (int j = 0; j < m; j++) {
                        // if the need is greater than available resources, process is not valid
                        if (need[i][j] > work[j]) {
                            valid = false;
                            break;
                        }
                    }
                } else {
                    valid = false;
                }
                // if this process is valid, put allocation of process into available pool
                if (valid) {
                    for (int j = 0; j < m; j++) {
                        work[j] = work[j] + allocation[i][j];
                    }
                    finish[i]= true;
                    working = true;
                }
            }
        } while (working);

        // check if all finished
        for (int i = 0; i < n; i++){
            if (!finish[i]) {
                return false;
            }
        }

        return true;
    }

}