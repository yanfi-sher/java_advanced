package terlan.interview;

public class MyArray {
    private final int size;
    private final int[] array;
    public MyArray(int size) {
        this.size = size;
        array = new int[size];
    }

    public void setAll(int value) {
        for (int i=0;i<size;i++){
            array[i]=value;
        }
    }

    public Integer get(int index) {
        if (index >= 0 && index < array.length) {
            return array[index];
        } else {
            return null;
        }
    }

    public void set(int index, int value) {
        try {
            array[index] = value;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }
}
