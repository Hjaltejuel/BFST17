//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringSearchable {
    private List<String> databaseInfo = new ArrayList();

    public StringSearchable(List<String> databaseInfo) {
        this.databaseInfo.addAll(databaseInfo);
    }

    public Collection<String> search(String userInput) {
        ArrayList founds = new ArrayList();
        Iterator var3 = this.databaseInfo.iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            if(s.indexOf(userInput) == 0) {
                founds.add(s);
            }
        }

        return founds;
    }
}
