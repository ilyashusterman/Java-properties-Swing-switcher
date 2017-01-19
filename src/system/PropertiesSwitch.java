package system;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ilya on 16/09/2016.
 */
public class PropertiesSwitch {


    public PropertiesSwitch(){}

    public String getValue(String filePath,String regex){
    String content = getContent(filePath);
    int index = content.indexOf(regex);
    if(index!=-1)
    return content.substring(index+1,content.length());
    return "Not Found";
}

    private String getContent(String filePath){
    Path path = Paths.get(filePath);
    Charset charset = StandardCharsets.UTF_8;
    String content = null;
    try {
        content = new String(Files.readAllBytes(path), charset);
    } catch (IOException e) {
        e.printStackTrace();
        return "Could not read file , Please check file, File corrupted";
    }
    return content;
    }

    public boolean switchProperties(String filePath,String regex,String replacement){
        Path path = Paths.get(filePath);
        Charset charset = StandardCharsets.UTF_8;
        String content = getContent(filePath);

        content = content.replaceAll(regex, replacement);
        try {
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
