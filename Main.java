//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model;
            if(args.length > 0) {
                try {
                    model = new Model(args[0]);
                } catch (IOException var3) {
                    throw new RuntimeException(var3);
                }
            } else {
                model = new Model();
            }

            new View(model);
        });
    }
}
