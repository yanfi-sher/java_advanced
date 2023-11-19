package terlan.interview;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyStack {
    private LinkedList<Integer> stackMap = new LinkedList<>();
    private LinkedList<Integer> maxMap = new LinkedList<>();

    public void push(int number){
        stackMap.addLast(number);
        if (maxMap.isEmpty() || maxMap.getLast()<=number)
            maxMap.addLast(number);
    }

    public int pull(){
        emptyException();
        int res = stackMap.getLast();
        stackMap.removeLast();
        if (maxMap.getLast()==res)
            maxMap.removeLast();
        return res;
    }

    private void emptyException() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty stack");
        }
    }

    public boolean isEmpty(){
        return stackMap.isEmpty();
    }

    public int getMax(){
        emptyException();
        return maxMap.getLast();
    }
}
