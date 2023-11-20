package telran.view.test;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import telran.view.InputOutput;
import telran.view.StandartInputOutput;

record Point(int x, int y){

}
public class InputOutputTest {
    InputOutput io = new StandartInputOutput();

    @Test
    @Disabled
    void readPointTest(){
        Point point = io.readObject("enter coordinate of point; usage <x>#<y>",
                "Wrong coordinates", str->{
                    String[] xy = str.split("#");
                            if (xy.length != 2){
                                throw new RuntimeException("Incorrect format of input");
                            }
                            int x = Integer.parseInt(xy[0]);
                            int y = Integer.parseInt(xy[1]);
                            return new Point(x,y);
                }
        );
        io.writeLine( point.x()+point.y());
    }

    @Test
    void readObjectInteger(){
        Integer number = io.readObject("Enter number in range [100-200]",
                "Wrong number", str ->{
            int num = Integer.parseInt(str);
            if (num<100 || num>200){
                throw new RuntimeException("Must be in range [100-200]");
            }
            return num;
        });
        io.writeLine(number/2);
    }
}
