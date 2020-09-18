/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import javax.swing.JButton;

/**
 *
 * @author Akash
 */
public class Button {

    private JButton b;
    private Character[] list;
    private LinkedList<Button> neighbours;
    private int X;
    private int Y;

    public Character[] getList() {
        return list;
    }

    public void setList(Character[] list) {
        this.list = list;
    }

    public JButton getB() {
        return b;
    }

    public void setB(JButton b) {
        this.b = b;
    }

    public LinkedList getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(LinkedList<Button> neighbours) {
        this.neighbours = neighbours;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

}
