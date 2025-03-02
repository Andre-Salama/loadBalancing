package StorePack;

import BalancerPack.Item;
import java.net.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class Store {

    private String name;
    private ObjectOutputStream store;
    private int storageSize;

    public Store(String name, ObjectOutputStream store, int storageSize) {
        this.name = name;
        this.store = store;
        this.storageSize = storageSize;
    }

    public String getName() {
        return name;
    }

    public ObjectOutputStream getOutputStream() {
        return store;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    //
    //
    //
    //
    public static void main(String[] args) {
        StoreGUI gui = new StoreGUI();
        gui.setVisible(true);
        Socket balancer;
        ObjectInputStream inp;
        //
        //
        try {
            balancer = new Socket("localhost", 3000);
            inp = new ObjectInputStream(balancer.getInputStream());
//
//
            gui.getname().setText("Store #" + inp.readInt());
            Item item;
            String textSize;
            String[] data = new String[3];
            DefaultTableModel table = (DefaultTableModel) gui.getTable().getModel();
            while (true) {
                item = (Item) inp.readObject();
                data[0] = item.getID() + "";
                data[1] = item.getType();
                data[2] = item.getSize() + "";
                table.addRow(data);
                textSize = (Integer.parseInt(gui.getSizeLabel().getText()) - item.getSize()) + "";
                gui.getSizeLabel().setText(textSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
