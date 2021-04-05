package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public static final String EMPTY_CHARACTER = " ";
    public static final String COMMA = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        createFile(toFileName);
        String writeTo = readFromFile(fromFileName);
        writeToFile(toFileName, writeTo);
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create a file.");
        }
    }

    private String readFromFile(String fromFileName) {
        String read = "";
        StringBuilder fileReader = new StringBuilder();
        int supply = 0;
        int buy = 0;
        try {
            List fileToRead = Files.readAllLines(Paths.get(fromFileName));
            for (int i = 0; i < fileToRead.size(); i++) {
                read += fileToRead.get(i) + EMPTY_CHARACTER;
            }
            String[] readSplit = read.split(EMPTY_CHARACTER);
            for (int i = 0; i < readSplit.length; i++) {
                if (readSplit[i].split(COMMA)[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(readSplit[i].split(COMMA)[1]);
                } else {
                    buy += Integer.parseInt(readSplit[i].split(COMMA)[1]);
                }
            }
            fileReader.append(SUPPLY).append(COMMA).append(supply)
                    .append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy)
                    .append(System.lineSeparator())
                    .append(RESULT).append(COMMA).append(supply - buy)
                    .append(System.lineSeparator());

            return fileReader.toString();

        } catch (IOException e) {
            throw new RuntimeException("Can't read to file");
        }
    }

    private void writeToFile(String fromFileName, String writeTo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fromFileName))) {
            writer.write(writeTo);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}

