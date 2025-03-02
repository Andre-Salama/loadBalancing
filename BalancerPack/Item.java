package BalancerPack;

import java.io.Serializable;

public class Item implements Serializable {

    private static int IDs = 0;
    private final int ID;
    private String type;
    private int size;

    Item(String type, int size) {
        ID = IDs++;
        this.type = type;
        this.size = size;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

}
