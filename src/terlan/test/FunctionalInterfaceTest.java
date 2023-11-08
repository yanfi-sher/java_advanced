package terlan.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
//import java.util.Comparator;

import org.junit.jupiter.api.Test;

//class ReverseIntegerComp implements Comparator<Integer>{
//
//    @Override
//    public int compare(Integer o1, Integer o2) {
//        return o2 - o1;
//    }
//}

class FunctionalInterfaceTest {
    Integer [] ar = {100, 13, -10, 201, 150, 17};

    @Test
    void reverseSortTest() {
        Integer[] expected = {201, 150, 100, 17, 13, -10};
        Arrays.sort(ar, (o1,o2) -> o2-o1);
        assertArrayEquals(expected, ar);
    }

    @Test
    void evenOddSortTest(){
        Integer[] expected = {-10,100,150,201,17,13};
        Arrays.sort(ar, (o1,o2) -> {
            int res = 0;
            if(o1%2 ==0 && o2%2!=0) {
                res = -1;
            }else if (o1%2 !=0 && o2%2 == 0){
                res = 1;
            }else if (o1 % 2 == 0){
                res = o1-o2;
            }else {
                res = o2-o1;
            }
            return res;
        });
        assertArrayEquals(expected, ar);
    }

    private int evenOddComp(Integer o1,Integer o2){
        int res = 0;
        if(o1%2 ==0 && o2%2!=0) {
            res = -1;
        }else if (o1%2 !=0 && o2%2 == 0){
            res = 1;
        }else if (o1 % 2 == 0){
            res = o1-o2;
        }else {
            res = o2-o1;
        }
        return res;
    }
    @Test
    void evenOddMethodReference() {
        Integer[] expected = {-10, 100, 150, 201, 17, 13};
        Arrays.sort(ar,this::evenOddComp);
        assertArrayEquals(expected, ar);
    }
}