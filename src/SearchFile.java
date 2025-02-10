import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.util.ArrayList;

public class SearchFile {
    private String [] fileNames;
    private String [] fileExtensions;


    public SearchFile() {
        this.fileNames = new String[]{"log", "logs", "logging", "debug", "info", "warn", "error", "fatal", "trace","event"};
        this.fileExtensions = new String[]{".txt", ".log", ".out", ".dat", ".debug"};
    }

    public ArrayList<String> searchFile(String filePath) {
        ArrayList<String> files = new ArrayList<String>();
        StringBuilder fullPath = new StringBuilder();
        for (String fileName : fileNames) {
            for (String extension : fileExtensions) {
                fullPath.append(filePath).append("\\").append(fileName).append(extension);
                File file = new File(fullPath.toString());
                if (file.exists()) {
                    files.add(file.getAbsolutePath());
                }
            }
        }
        return files;
    }
}
