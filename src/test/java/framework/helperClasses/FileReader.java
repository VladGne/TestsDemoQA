package framework.helperClasses;

import framework.models.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

public class FileReader {

//    Map<String, Object> data;
//
//    public void readUserDataFrom(String filePath){
//        //User user = new User();
//        data = new HashMap<String, Object>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            // Convert JSON string from file to Object
//            //user = mapper.readValue(new File(filePath), User.class);
//
//            byte[] mapData = Files.readAllBytes(Paths.get(filePath));
//
//            data =  mapper.readValue(allData, User.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    Collection<Object> users;

    public void processDataFile( String filePath ){
        users = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        User user[];
        try {
            byte[] validUsers = Files.readAllBytes(Paths.get(filePath));
            //data =  objectMapper.readValue(allData, User.class);
            //Object obj = objectMapper.readValue( new File( filePath ), User.class );
            users.add( objectMapper.readValue(validUsers, User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getData() {

        Object[][] data = new Object[ users.size() ][ 1 ];

        Iterator<Object> iterator = users.iterator();

        int i = 0;
        while( iterator.hasNext() ) {
            data[ i ][ 0 ] = iterator.next();
            i++;
        }

        return data;
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
