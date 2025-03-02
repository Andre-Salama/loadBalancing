package GeneratorPack;

import java.io.*;
import java.net.*;

public class Generator {

    private Socket balancer;
    private DataOutputStream out;

    public Generator() {
        try {
            balancer = new Socket("localhost", 2000);
            out = new DataOutputStream(balancer.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendObject(javax.swing.JTextField type, javax.swing.JTextField size) {
        try {
            out.writeUTF(type.getText());
            out.flush();
            type.setText("");
            out.writeInt(Integer.parseInt(size.getText()));
            out.flush();
            size.setText("");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
