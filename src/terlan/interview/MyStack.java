package terlan.interview;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyStack {
    private final LinkedList<Integer> stackMap = new LinkedList<>();
    private int size = 0;

    public void push(int number){
        stackMap.addLast(number);
        size++;
    }

    public int pull(){
        emptyException();
        int res = stackMap.getLast();
        stackMap.remove(size-1);
        size--;
        return res;

        //возвращается и удаляется последний элемент
        //если пустой стэк, то бросается NoSuchElementException
    }

    private void emptyException() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty stack");
        }
    }

    public boolean isEmpty(){
        // выясняет пустой стэк или нет
        return stackMap.isEmpty();
    }

    public int getMax(){
        emptyException();
        int max = Integer.MIN_VALUE;
        for (int x : stackMap) {
            max = Math.max(max, x);
        }
        return max;
        //возвращает наибольшее число, если стек не пустой
        //если пустой стэк, то бросается NoSuchElementException

        //я думаю, что сложность не та!!!! надо переделать...
    }

}
