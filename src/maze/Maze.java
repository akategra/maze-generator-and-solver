/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Akash
 */
public class Maze extends JFrame {

    private Button[][] arr;
    private final Object lock = new Object();
    private boolean flag = false;
    private final Solver solver = new Solver();
    private final JButton findPath = new JButton("Find Path");
    public final JButton generatePath = new JButton("Generate Path");
    private final Container c = this.getContentPane();
    private final JLabel lab = new JLabel(new ImageIcon(getClass().getResource("z9yLlx.jpg")));
    private final Maze thisFrame = this;

    public void setGridSize(int row, int col) {
        arr = new Button[row + 1][col + 1];
    }

    public void drawGrid() {
        c.setLayout(null);
        generatePath.setBounds(950, 900, 400, 60);
        generatePath.setFocusPainted(false);
        generatePath.setEnabled(false);
        generatePath.setBackground(Color.black);
        generatePath.setForeground(Color.white);
        generatePath.setFont(new Font("Arial", Font.BOLD, 50));
        findPath.setBounds(600, 900, 300, 60);
        findPath.setFont(new Font("Arial", Font.BOLD, 50));
        findPath.setBackground(Color.black);
        findPath.setForeground(Color.white);
        findPath.setFocusPainted(false);
        findPath.setEnabled(false);
        c.add(findPath);
        c.add(generatePath);

        findPath.addActionListener((ActionEvent e) -> {
            findPath.setEnabled(false);
            flag = true;
            synchronized (lock) {
                lock.notifyAll();
            }
        });

        generatePath.addActionListener((ActionEvent e) -> {
            if (generatePath.getForeground() == Color.red) {
                thisFrame.dispose();
            }
            generatePath.setEnabled(false);
            flag = true;
            synchronized (lock) {
                lock.notifyAll();
            }
        });

        for (int i = 560, row = 0; row < arr.length && i <= 1360; i += 80 / (arr.length / 10), row++) {
            for (int j = 60, col = 0; col < arr[row].length && j <= 860; j += (80 / (arr.length / 10)), col++) {
                arr[row][col] = new Button();
                arr[row][col].setB(new JButton());
                arr[row][col].getB().setBounds(i, j, (80 / (arr.length / 10)), (80 / (arr.length / 10)));
                arr[row][col].getB().setBackground(Color.black);
                arr[row][col].getB().setOpaque(true);
                arr[row][col].getB().setBorderPainted(false);
                arr[row][col].getB().setEnabled(false);
                c.add(arr[row][col].getB());
            }
        }

        lab.setVisible(true);
        lab.setBounds(0, 0, 1920, 1080);
        c.add(lab);
        insertNeighbours();

    }

    public int[] returnStartingPoint() {
        Random r = new Random();
        int[] a = new int[2];
        int i = 0, j = 0;
        while (i % 2 == 0) {
            i = r.nextInt(arr.length - 1);
        }
        while (j % 2 == 0) {
            j = r.nextInt(arr[i].length - 1);
        }
        a[0] = i;
        a[1] = j;
        return a;
    }

    public void Pause() {
        synchronized (lock) {
            while (!flag) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    //
                }
            }
        }
    }

    public void insertNeighbours() {
        Character[] list = new Character[4];
        list[0] = 'U';
        list[1] = 'D';
        list[2] = 'L';
        list[3] = 'R';

        for (Button[] arr1 : arr) {
            for (Button arr11 : arr1) {
                arr11.setList(list);
                Collections.shuffle(Arrays.asList(arr11.getList()));
                Collections.shuffle(Arrays.asList(arr11.getList()));
                Collections.shuffle(Arrays.asList(arr11.getList()));
            }
        }

    }

    public void callGenerator() {
        generateMaze();
    }

    public void generateMaze() {

        int[] a = returnStartingPoint();
        RecursiveBackTrack(arr, a[0], a[1]);
        findPath.setEnabled(true);
        Pause();
        Solver();
    }

    public void RecursiveBackTrack(Button[][] arr, int x, int y) {

        Collections.shuffle(Arrays.asList(arr[x][y].getList()));
        Character[] c = arr[x][y].getList();

        for (Character c1 : c) {
            switch (c1) {
                case 'U':
                    if (x - 2 > 0) {
                        arr[x][y].getB().setBackground(Color.white);
                        if (arr[x - 2][y].getB().getBackground() == Color.black) {
                            arr[x - 1][y].getB().setBackground(Color.white);
                            arr[x - 2][y].getB().setBackground(Color.red);
                            long expectedtime = System.currentTimeMillis() + 5;
                            while (System.currentTimeMillis() < expectedtime) {
                            }
                            RecursiveBackTrack(arr, x - 2, y);
                        }
                    }
                    break;
                case 'D':
                    if (x + 2 < arr.length - 1) {
                        arr[x][y].getB().setBackground(Color.white);
                        if (arr[x + 2][y].getB().getBackground() == Color.black) {
                            arr[x + 1][y].getB().setBackground(Color.white);
                            arr[x + 2][y].getB().setBackground(Color.red);
                            long expectedtime = System.currentTimeMillis() + 5;
                            while (System.currentTimeMillis() < expectedtime) {
                            }
                            RecursiveBackTrack(arr, x + 2, y);
                        }
                    }
                    break;
                case 'R':
                    if (y + 2 < arr[x].length - 1) {
                        arr[x][y].getB().setBackground(Color.white);
                        if (arr[x][y + 2].getB().getBackground() == Color.black) {
                            arr[x][y + 1].getB().setBackground(Color.white);
                            arr[x][y + 2].getB().setBackground(Color.red);
                            long expectedtime = System.currentTimeMillis() + 5;
                            while (System.currentTimeMillis() < expectedtime) {
                            }
                            RecursiveBackTrack(arr, x, y + 2);
                        }
                    }
                    break;
                case 'L':
                    if (y - 2 > 0) {
                        arr[x][y].getB().setBackground(Color.white);
                        if (arr[x][y - 2].getB().getBackground() == Color.black) {
                            arr[x][y - 1].getB().setBackground(Color.white);
                            arr[x][y - 2].getB().setBackground(Color.red);
                            long expectedtime = System.currentTimeMillis() + 5;
                            while (System.currentTimeMillis() < expectedtime) {
                            }
                            RecursiveBackTrack(arr, x, y - 2);
                        }
                    }
                    break;
            }
        }
    }

    public void Solver() {

        flag = false;
        Solver s = new Solver();
        s.InsertNeighbours(arr);
        Point p = s.BFS(arr, generatePath);
        Pause();
        s.GeneratePath(arr, p);
    }

}
