package org.corejava.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.System.out;

public class CreatingStreams
{
    public static <T> void show(String title, Stream<T> stream)
    {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        out.println(title + "* ");
        for (int i = 0; i < firstElements.size(); i++)
        {
            if(i > 0) out.println(", ");
            if(i < SIZE) out.println(firstElements.get(i));
            else out.println("...");
        }
        out.println();
    }
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8))
        {
            show("lines", lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootdirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootdirectories);

//        Iterator<Path> iterator = Paths.get()
    }
}
