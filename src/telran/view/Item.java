package telran.view;

import java.util.function.Consumer;

public interface Item {
    String displayName();
    void perform(InputOutput io);
    boolean isExists ();
    static Item of(String name, Consumer<InputOutput> action, boolean isExit){
        return new Item() {
            @Override
            public String displayName() {
                return name;
            }

            @Override
            public void perform(InputOutput io) {
                action.accept(io);
            }

            @Override
            public boolean isExists() {
                return isExit;
            }
        };
    }
    static Item of(String name, Consumer<InputOutput> action){
        return of(name,action,false);
    }
    static Item exit(){
        return of("Exit", io -> {}, true);
    }
}
