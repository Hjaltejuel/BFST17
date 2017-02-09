//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class View implements Observer {
    Model model;
    JFrame window;
    JTextField userInput;
    JTextArea userOutput;
    JScrollPane scrollPane;
    ArrayList<String> listItems;
    StringSearchable searchable;
    AutocompleteJComboBox combo;

    public View(final Model model) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception var4) {
            ;
        }

        this.model = model;
        model.addObserver(this);
        this.listItems = new ArrayList();
        Iterator var2 = model.iterator();

        while(var2.hasNext()) {
            Address address = (Address)var2.next();
            this.listItems.add(address.toString());
        }

        this.searchable = new StringSearchable(this.listItems);
        this.combo = new AutocompleteJComboBox(this.searchable);
        this.window = new JFrame("Adresse parser");
        this.window.setLayout(new BorderLayout());
        this.combo.setPreferredSize(new Dimension(500, 30));
        this.combo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if(event.getKeyChar() == 10) {
                    model.add(Address.parse(View.this.combo.getSelectedItem().toString()));
                    View.this.combo.setSelectedItem((Object)null);
                }

            }
        });
        this.window.add(this.combo, "North");
        this.userOutput = new JTextArea();
        this.userOutput.setEditable(false);
        this.userOutput.setBackground(Color.LIGHT_GRAY);
        this.scrollPane = new JScrollPane(this.userOutput);
        this.scrollPane.setPreferredSize(new Dimension(500, 500));
        this.window.add(this.scrollPane);
        this.window.pack();
        this.window.setDefaultCloseOperation(3);
        this.window.setVisible(true);
        this.update((Observable)null, (Object)null);
    }

    public void update(Observable o, Object arg) {
        StringBuilder sb = new StringBuilder();
        Iterator var4 = this.model.getAddressesUdskrift().iterator();

        while(var4.hasNext()) {
            Address address = (Address)var4.next();
            sb.append(address).append("\n\n");
        }

        this.userOutput.setText(sb.toString());
        this.window.repaint();
    }
}
