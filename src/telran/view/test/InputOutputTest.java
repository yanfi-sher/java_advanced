package telran.view.test;

import org.junit.Test;
import telran.view.*;

import static org.junit.Assert.*;

record Point(int x, int y){

}
public class InputOutputTest {
    InputOutput io = new StandartInputOutput();

    @Test
    public void readPointTest(){
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
        io.writeLine( point.x()+point.y()+"");
    }
}
