package lanou.carhome.find.dbtool;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/10.
 */
public class SearchCarNameBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SearchCarNameBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
