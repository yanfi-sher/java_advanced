package terlan.stream.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTests {
    int[] arr = {10,13,8,7,3,5,6};

    @Test
    @Disabled
    void arrayStreamTest(){
        int[] arr = {10,13,8,7,3,5,6};
        int[] empty = {};
        assertEquals(24, Arrays.stream(arr)
                .filter(n->n%2==0)
                .sum());
        assertEquals(13,Arrays.stream(arr)
                .filter(n->n%2!=0)
                .max().orElse(0));
        assertEquals(0,Arrays.stream(empty)
                .filter(n -> n%2 != 0)
                .max().orElse(0));
    }

    @Test
    @Disabled
    void displaySportloto(){
        Random gen = new Random();
        gen.ints(1,50)
                .distinct().limit(7)
                .forEach(n-> System.out.print(n+ " "));
    }

    @Test
    @Disabled
    void evenOddGrouping(){
        Map<String, List<Integer>> mapOddEven = Arrays.stream(arr).boxed().
                collect(Collectors.groupingBy(n-> n%2==0 ? "even":"odd"));
        System.out.println(mapOddEven);
    }

    @Test
    @Disabled
    void displayOccurrenceSorted(){
        String[]strings = {"lpm","y", "a", "lpm", "aa", "yy","yy","aa","lpm"};
        Map<String,Long> occurrencesMap = Arrays.stream(strings)
                .collect(Collectors.groupingBy(s -> s,Collectors.counting()));
        occurrencesMap.entrySet().stream().sorted((e1,e2) -> {
            int res = Long.compare(e2.getValue(), e1.getValue());
            if (res == 0){
                res = e1.getKey().compareTo(e2.getKey());
            }
            return res;
        })
                .forEach(e-> System.out.printf("%s => %d\n", e.getKey(), e.getValue()));

    }

    @Test
    @Disabled
    void stringStream(){
        String string = "Hello";
        //string.chars().forEach(c -> System.out.printf("%c, ",c));
        string.chars().mapToObj(c->""+(char)c).forEach(s -> System.out.print(s+", "));
    }

    @Test
    void splittingStringArray(){
        String[] strings = {"Hello","World"};
        Arrays.stream(strings).flatMapToInt(String::chars).
                mapToObj(c->""+(char)c).forEach(s -> System.out.print(s+", "));
    }




}



//
//
//
//
//
//

