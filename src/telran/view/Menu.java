package telran.view;

public class Menu implements Item{

    private static final String SYMBOL = "*" ;
    private static final int N_SYMBOLS = 30;
    private String name;
    private Item[] items;

    public Menu(String name, Item[] items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public String displayName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        Item item = null;
        do {
            displayTitle(io);
            displayItem(io);
            int nItem = io.readInt("Select item number","Wrong item number",1, items.length);
            item = items[nItem-1];
            try {
                item.perform(io);
            }catch (Exception e){
                io.writeLine(e.getMessage());
            }
        }while(!item.isExists());
    }

    private void displayItem(InputOutput io) {
        for (int i =0; i<items.length;i++){
            io.writeLine(String.format("%d. %s", i+1,items[i].displayName()));
        }
    }

    private void displayTitle(InputOutput io) {
        io.writeLine(SYMBOL.repeat(N_SYMBOLS));
        io.writeLine(String.format("%s%s%s", SYMBOL, " ".repeat(N_SYMBOLS/4), name));
        io.writeLine(SYMBOL.repeat(N_SYMBOLS));
    }

    @Override
    public boolean isExists() {
        return false;
    }
}
