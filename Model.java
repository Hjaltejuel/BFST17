//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Model extends Observable implements Iterable<Address> {
    private List<Address> addresses;
    private List<Address> addressesUdskrift;

    public Model() {
        this.addresses = new ArrayList();
        this.addressesUdskrift = new ArrayList();
    }

    public Model(String filename) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(new File(filename)));
        Throwable var3 = null;

        try {
            this.addresses = (List)input.lines().map(Address::parse).collect(Collectors.toList());
            this.addressesUdskrift = new ArrayList();
        } catch (Throwable var12) {
            var3 = var12;
            throw var12;
        } finally {
            if(input != null) {
                if(var3 != null) {
                    try {
                        input.close();
                    } catch (Throwable var11) {
                        var3.addSuppressed(var11);
                    }
                } else {
                    input.close();
                }
            }

        }

    }

    public void add(Address address) {
        this.addressesUdskrift.add(address);
        this.addresses.add(address);
        this.setChanged();
        this.notifyObservers();
    }

    public Iterator<Address> iterator() {
        return this.addresses.iterator();
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public List<Address> getAddressesUdskrift() {
        return this.addressesUdskrift;
    }
}
