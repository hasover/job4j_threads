package ru.job4j.io;

import java.io.*;

public class SaveData {
    private final File file;

    public SaveData(File file) {
        this.file = file;
    }

    public void saveContent(String content) {

            try (PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                out.print(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }
}
