package org.corejava.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.System.out;

public class CountLongWords
{

    public static void main(String... args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("Alice.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));
        long count = words.parallelStream().filter(w -> w.length() > 8).count();
        long total = words.size();
        out.println("count = " + count + " ,total = " + total);
    }
}
