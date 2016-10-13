package hise.hznu.istudy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
    public IOUtil() {
    }

    public static void copy(InputStream is, OutputStream os, boolean closeIs, boolean closeOs) throws Exception {
        try {
            byte[] ex = new byte[2048];

            int len;
            while((len = is.read(ex)) != -1) {
                os.write(ex, 0, len);
            }
        } catch (Exception var30) {
            throw var30;
        } finally {
            try {
                if(is != null && closeIs) {
                    is.close();
                }
            } catch (Exception var28) {
                ;
            } finally {
                if(os != null && closeOs) {
                    os.close();
                }

            }

        }

    }

    public static byte[] readBytes(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        copy(fis, os, true, true);
        return os.toByteArray();
    }
}

