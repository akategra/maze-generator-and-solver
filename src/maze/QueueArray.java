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
public class QueueArray<T> {

    T[] Q;
    int F;
    int R;

    public QueueArray(int size) {
        this.Q = (T[]) new Object[size];
        this.F = size - 1;
        this.R = size - 1;
    }

    public void Enqueue(T data) {
        R = (R + 1) % Q.length;
        Q[R] = data;
    }

    public T Dequeue() {
        F = (F + 1) % Q.length;
        return Q[F];
    }

    public boolean ifEmpty() {
        return F == R;
    }

    public boolean ifFull() {
        return (R + 1) % Q.length == F;
    }
}
