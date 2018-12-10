package framework.helperClasses;

import framework.models.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileReader {

    public static User readUserDataFrom(String filePath){
        User user = new User();

        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert JSON string from file to Object
            user = mapper.readValue(new File(filePath), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static Properties readProperties(){
        Properties prop = new Properties();

        InputStream input = null;
        try {
            input =new FileInputStream("src/test/resources/config.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}
