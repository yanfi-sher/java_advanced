package telran.view;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
    String readString(String prompt);
    void write(String str);
    default void writeLine(Object obj){
        write(obj.toString() + "\n");
    }
    default <T>T readObject(String prompt, String errorPrompt, Function<String,T> mapper){
        boolean running = true;
        T res = null;
        do {
            running = false;
            try{
                String str = readString(prompt);
                res = mapper.apply(str);
            }catch (Exception e){
                running = true;
                writeLine(errorPrompt + ": " + e.getMessage());
            }
        }while (running);
        return res;
    }

    default int readInt(String prompt, String errorPrompt){
        return readObject(prompt,errorPrompt,Integer::parseInt);
    }

    default int readInt(String prompt, String errorPrompt, int min, int max){
        return readObject(String.format("%s [%d - %d]", prompt,min,max), errorPrompt, str->{
            int num = Integer.parseInt(str);
            if (num<min || num>max){
                throw new RuntimeException(String.format("Must be in the range [%d - %d]", min, max));
            }
            return num;
        });
    }

    default long readLong(String prompt, String errorPrompt){
        return readObject(prompt,errorPrompt,Long::parseLong);
    }

    default long readInt(String prompt, String errorPrompt, long min, long max){
        return readObject(String.format("%s [%d - %d]", prompt,min,max), errorPrompt, str->{
            long num = Long.parseLong(str);
            if (num<min || num>max){
                throw new RuntimeException(String.format("Must be in the range [%d - %d]", min, max));
            }
            return num;
        });
    }

    default LocalDate readDate(String prompt, String errorPrompt){
        return readObject(prompt,errorPrompt, LocalDate::parse);
    }

    default LocalDate readDate(String prompt, String errorPrompt, LocalDate from, LocalDate to){
        return readObject(prompt,errorPrompt, str ->{
            LocalDate date = LocalDate.parse(str);
            if (date.isBefore(from) || date.isAfter(to)){
                throw new RuntimeException(String.format("Date must be in the range [%s - %s]", from, to));
            }
            return date;
        });
    }

    default double readDouble(String prompt, String errorPrompt){
        return readObject(prompt,errorPrompt,Double::parseDouble);
    }

    default double readInt(String prompt, String errorPrompt, double min, double max){
        return readObject(String.format("%s [%d - %d]", prompt,min,max), errorPrompt, str->{
            double num = Double.parseDouble(str);
            if (num<min || num>max){
                throw new RuntimeException(String.format("Must be in the range [%d - %d]", min, max));
            }
            return num;
        });
    }

    default String readPredicate(String prompt, String errorPrompt, Predicate<String> predicate){
        return readObject(prompt, errorPrompt, str -> {
            if (predicate.test(str)) {
                return str;
            } else {
                throw new RuntimeException("Error");
            }
        });

    }

    default String readOptions(String prompt, String errorPrompt, Set<String> options){
        Predicate<String> predicate = options::contains;
        return readObject(prompt, errorPrompt,str -> {
            if (predicate.test(str)) {
                return str;
            } else {
                throw new RuntimeException("Error");
            }
        });
    }

    default String readEmail(String prompt, String errorPrompt){
        return readObject(prompt, errorPrompt, str ->{
            if (str.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                return str;
            } else {
                throw new RuntimeException("Error");
            }
        });
    }
}
