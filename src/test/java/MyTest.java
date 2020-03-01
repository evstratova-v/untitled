import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {
    Path path = Paths.get("src/test/resources/filename.txt");

    @Test
    public void test() {
        try {
            String fileContent = Files.readString(path);

            Pattern pattern = Pattern.compile("start\r\nid2\r\nend");
            Matcher matcher = pattern.matcher(fileContent);

            String ruleTemplate = "";

            if (matcher.find()) {
                ruleTemplate = fileContent.concat("\n\n" + matcher.group().replaceFirst("id.?", "id4"));
                System.out.println(ruleTemplate);
            }
            if(!"".equals(fileContent)) {
                Files.writeString(path, ruleTemplate);
            }
            Thread.sleep(5000L);
            Files.writeString(path, fileContent);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
