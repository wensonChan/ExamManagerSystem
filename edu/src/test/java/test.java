import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/14 11:19
 */
public class test {
    public static void main(String[] args){
        String str = "";
        try {
            String encodedExamName = URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
            System.out.println(encodedExamName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
