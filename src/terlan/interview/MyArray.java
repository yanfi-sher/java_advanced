package terlan.interview;

import java.util.HashMap;

public class MyArray {
    private int size;
    private int allValues;
    private HashMap<Integer, Integer> valuesMap = new HashMap<>();

    public MyArray(int size) {
        this.size= size;
    }

    public void setAll(int value) {

        valuesMap = new HashMap<>();
        allValues = value;
    }

    public Integer get(int index) {
        Integer res = null;
        if (index >= 0 && index < size) {
            res = valuesMap.getOrDefault(index, allValues);
        }
        return res;
    }

    public void set(int index, int value) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        valuesMap.put(index, value);
    }
}
