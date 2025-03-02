package BalancerPack;

import StorePack.Store;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Balancer implements Runnable {

    private static ServerSocket client;
    private static ServerSocket store;
    public static ArrayList<Store> stores = new ArrayList<>();
    public static BalancerGUI gui = new BalancerGUI();
    public static final Object LOCK = new Object();
    private static int storeNum = 0;

    public void run() {
        Socket storee;
        ObjectOutputStream out;
        while (true) {
            try {
                storee = store.accept();
                out = new ObjectOutputStream(storee.getOutputStream());
                gui.getStores()[storeNum++].setText("5000");
                out.writeInt(storeNum);
                out.flush();
                synchronized (LOCK) {
                    stores.add(new Store("Store " + storeNum, out, 5000 /*Size*/));
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        gui.setVisible(true);

        try {
            client = new ServerSocket(2000);
            store = new ServerSocket(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Thread th = new Thread(new Balancer());
        th.start();

        Socket clientt;
        while (true) {
            try {
                clientt = client.accept();
                new Thread(new BalancerClient(clientt)).start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
