/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Akash
 */
public class Solver {

    public void InsertNeighbours(Button[][] arr) {
        LinkedList list;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[i].length - 1; j++) {
                arr[i][j].setX(i);
                arr[i][j].setY(j);
                list = new LinkedList();
                if ((i + 1) < arr.length - 1 && arr[i + 1][j].getB().getBackground() == Color.white) {
                    list.Insert(arr[i + 1][j]);
                }
                if ((i - 1) >= 1 && arr[i - 1][j].getB().getBackground() == Color.white) {
                    list.Insert(arr[i - 1][j]);
                }
                if ((j + 1) < arr[i].length - 1 && arr[i][j + 1].getB().getBackground() == Color.white) {
                    list.Insert(arr[i][j + 1]);
                }
                if ((j - 1) >= 1 && arr[i][j - 1].getB().getBackground() == Color.white) {
                    list.Insert(arr[i][j - 1]);
                }
                arr[i][j].setNeighbours(list);
            }
        }
    }

    public Point BFS(Button[][] arr, JButton generate) {
        QueueArray<Point> Q = new QueueArray(arr.length * arr.length);
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        Q.Enqueue(new Point(1, 1, null));
        arr[1][1].getB().setBackground(Color.yellow);

        visited[1][1] = true;
        while (!Q.ifEmpty()) {
            Point p = Q.Dequeue();
            if (p.X == arr.length - 2 && p.Y == arr[0].length - 2) {
                generate.setForeground(Color.green);
                generate.setEnabled(true);
                RefreshGrid(arr);
                return p;
            }
            LinkedList<Button> list = arr[p.X][p.Y].getNeighbours();

            for (int i = 0; i < list.size(); i++) {

                int LX = list.get(i).getX();
                int LY = list.get(i).getY();
                if (!visited[LX][LY]) {
                    visited[p.X][p.Y] = true;
                    arr[LX][LY].getB().setBackground(Color.red);

                    long expectedtime = System.currentTimeMillis() + 20;
                    while (System.currentTimeMillis() < expectedtime) {
                    }
                    Point nextP = new Point(LX, LY, p);
                    Q.Enqueue(nextP);
                    arr[LX][LY].getB().setBackground(Color.yellow);

                }
            }
        }
        generate.setForeground(Color.red);
        return null;
    }

    public void RefreshGrid(Button[][] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[i].length - 1; j++) {
                if (arr[i][j].getB().getBackground() == Color.yellow) {
                    arr[i][j].getB().setBackground(Color.white);
                }
            }
        }

    }

    public void GeneratePath(Button[][] arr, Point p) {

        while (p.getPrev() != null) {
            arr[p.X][p.Y].getB().setBackground(Color.green);
            long expectedtime = System.currentTimeMillis() + 25;
            while (System.currentTimeMillis() < expectedtime) {
            }
            p = p.getPrev();
        }
        arr[1][1].getB().setBackground(Color.blue);
        arr[arr.length - 2][arr[0].length - 2].getB().setBackground(Color.red);
    }
}
