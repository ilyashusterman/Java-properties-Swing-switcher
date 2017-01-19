package properties.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Profit1 on 17/09/2016.
 */
public class PropertiesChange {

    private String filePath;

    public PropertiesChange(String filePath){
    this.filePath=filePath;
    }

    public boolean changeValue(String regex,String replacement) throws IOException {
        Properties props = getProperties();
        FileOutputStream out = null;
            out = new FileOutputStream(this.filePath);

        props.setProperty(regex, replacement);
        props.store(out, null);
        out.close();
        return true;
    }

    private Properties getProperties() throws IOException {
        FileInputStream in = null;
            in = new FileInputStream(this.filePath);
        Properties props = new Properties();
            props.load(in);
            in.close();
        return props;
    }

    public String getValue(String key) throws IOException {
        return getProperties().getProperty(key);
    }
}

