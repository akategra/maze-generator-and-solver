/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

/**
 *
 * @author Akash
 */
public class Point {

    int X;
    int Y;
    Point point;

    public Point(int X, int Y, Point point) {
        this.X = X;
        this.Y = Y;
        this.point = point;
    }

    public Point getPrev() {
        return this.point;
    }

    @Override
    public String toString() {
        return "Point{" + "X=" + X + ", Y=" + Y + '}';
    }
}
