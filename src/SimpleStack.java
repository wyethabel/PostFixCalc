/*
    Simplistic self-made stack designed around an
    arraylist with the intent to store doubles from
    calculating PostFix notation.
 */

import java.util.*;

public class SimpleStack {
    private ArrayList<Double> contents;

    public SimpleStack() {
        contents = new ArrayList<>();
    }

    public double peek() {
        if (contents.isEmpty()) {
            throw new EmptyStackException();
        }
        return contents.getLast();
    }

    public double pop() {
        if (contents.isEmpty()) {
            throw new EmptyStackException();
        }
        return contents.removeLast();
    }

    public void push(double value) {
        contents.add(value);
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public void clear() {
        contents.clear();
    }

    public int size() {
        return contents.size();
    }
}
