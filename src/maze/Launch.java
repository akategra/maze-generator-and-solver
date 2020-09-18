/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Akash
 */
public class Launch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] sizes = new int[2];
        do {

            System.out.print("Choose grid Size (Separate Dimensions by \",\") : ");
            String input = sc.next();
            String[] I = input.split(",");
            sizes[0] = Integer.valueOf(I[0]);
            sizes[1] = Integer.valueOf(I[1]);

            if (sizes[0] < sizes[1]) {
                System.out.println("Invalid Input !!! Try Again....\n");
            }

        } while (sizes[0] < sizes[1]);

        Maze maze = new Maze();
       
        maze.setGridSize(sizes[0], sizes[1]);
        maze.setSize(1920, 1080);
        maze.setLocationRelativeTo(null);
        maze.setTitle("Maze");
        maze.setResizable(true);
        maze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maze.drawGrid();
        maze.setVisible(true); 
        maze.callGenerator();
    }
}
