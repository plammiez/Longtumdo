import sun.misc.MessageUtils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

/**
 * Created by Waraporn on 8/19/2016.
 */
public class StreamTester {

    public static void main(String... args) throws Exception{

        String streamline = "This is Sparta!";
        ByteArrayInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new ByteArrayInputStream(streamline.getBytes());
            outputStream = new FileOutputStream("file.txt");

            int readSize = 0;
            byte[] buff = new byte[1024];
            while ((readSize = inputStream.read(buff, 0, buff.length)) > 0 ) {
                System.out.println("readSize = " + readSize);

                String buffString = new String(buff);
                System.out.println(buffString.substring(0, readSize));
                outputStream.write(buff, 0, readSize);

            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
