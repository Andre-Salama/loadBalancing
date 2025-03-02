package BalancerPack;

import java.io.*;
import java.net.*;
import javax.swing.table.DefaultTableModel;

public class BalancerClient implements Runnable {

    private final Socket clientt;

    public BalancerClient(Socket clientt) {
        this.clientt = clientt;
    }

    public void run() {
        DataInputStream inp;
        String type;
        int size;
        DefaultTableModel table = (DefaultTableModel) Balancer.gui.getTable().getModel();
        while (true) {
            try {
                inp = new DataInputStream(clientt.getInputStream());

                type = inp.readUTF();
                size = inp.readInt();

                Item item = new Item(type, size);
                String[] data = new String[4];
                data[0] = item.getID() + "";
                data[1] = item.getType();
                data[2] = item.getSize() + "";
                data[3] = "";

                int max = 0;
                int index = -1;
                int storage;
                int lastIndex = -1;
                synchronized (Balancer.LOCK) {
                    for (int x = 0; x < 2; x++) {
                        for (int y = 0; y < Balancer.stores.size(); y++) {
                            if (max < Balancer.stores.get(y).getStorageSize() && lastIndex != y
                                    && Balancer.stores.get(y).getStorageSize() >= item.getSize()) {
                                max = Balancer.stores.get(y).getStorageSize();
                                index = y;
                            }
                        }
                        max = 0;
                        if (index != lastIndex) { //prevents duplication of data on a server
                            lastIndex = index;
                            data[3] += Balancer.stores.get(index).getName() + " ";
                            Balancer.stores.get(index).getOutputStream().writeObject(item);
                            Balancer.stores.get(index).getOutputStream().flush();
                            //
                            //
                            storage = Balancer.stores.get(index).getStorageSize() - item.getSize();
                            Balancer.stores.get(index).setStorageSize(storage);
                            Balancer.gui.getStores()[index].setText(storage + "");
                        }
                    }
                }
                if (index != -1) //prevents an empty record from being added to the table when no store is available
                    table.addRow(data);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
