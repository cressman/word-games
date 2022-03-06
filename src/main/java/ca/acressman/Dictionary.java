package ca.acressman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class Dictionary {
    private static final Logger LOG = Logger.getLogger(Dictionary.class.getName());
    private final List<char[]> words;
    private final String fileName;

    public Dictionary(Properties props) {
        // get dictionary choice from properties and load it.
        fileName = props.getProperty("dictionary");
        if (fileName == null) {
            throw new RuntimeException("No dictionary specified in properties");
        }
        InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new RuntimeException("No resource found matching dictionary specified in properties");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        words = reader.lines().map(String::toCharArray).toList();
        LOG.info("Read " + words.size() + " words");
    }

    public List<char[]> getWords() {
        return words;
    }

    public String getFileName() {
        return fileName;
    }

    public int size() {
        return words.size();
    }
}
