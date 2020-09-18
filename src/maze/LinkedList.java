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
public class LinkedList<T> {

    Node head;

    public void Insert(T data) {
        Node n = new Node(data);

        if (head == null) {
            head = n;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = n;
        }

    }

    public void delete(Node data) {
        Node temp = head;
        Node prev = head;

        while (temp != null && temp != data) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == data) {
            prev.next = data.next;
        }
    }

    public T get(int index) {
        int count = 0;
        Node<T> temp = head;
        while (count != index && temp != null) {
            count++;
            temp = temp.next;
        }

        return temp.data;
    }

    public int size() {
        Node<T> temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

   
}
