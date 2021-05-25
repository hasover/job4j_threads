package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {

        StringBuilder builder = new StringBuilder();

            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                int data;
                while ((data = in.read()) > 0) {
                    char dataChar = (char) data;
                    if (filter.test(dataChar)) {
                        builder.append((char) data);
                    }
                }
            }

        return builder.toString();
    }
}