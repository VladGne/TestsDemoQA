package framework.helperClasses;

import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileReader {

   Map data;

    public void processDataFile( String filePath){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        try {
            data = objectMapper.readValue(file, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public Object[][] getData(Class necessaryClass) {
        Object[][] objects = new Object[ data.size() ][ 1 ];

        ObjectMapper objectMapper = new ObjectMapper();
        Set keys = data.keySet();

        for (Object key : keys){
            int i = 0;
            if(key.toString().contains(necessaryClass.getSimpleName())){
                objects[i][0] = objectMapper.convertValue(data.get(key), necessaryClass);
                i++;
            }
        }

        return objects;
    }

    public static Properties readProperties(){
        Properties prop = new Properties();

        InputStream input = null;
        try {
            input = new FileInputStream("src/test/resources/config.properties");
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
