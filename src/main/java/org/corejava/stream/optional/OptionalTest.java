package org.corejava.stream.optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.out;

public class OptionalTest
{
    public static void main(String[] args) throws IOException
    {
        var contents = new String(Files.readString(Paths.get("alice30.txt")));
        List<String> wordList = List.of(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("fred"))
                .findFirst();

        out.println(optionalValue.orElse("1.No word") + " contains fred");

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        out.println("2.result: " + result);
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        out.println("3.result: " + result);
        try{
            result = optionalString.orElseThrow(IllegalStateException::new);
            out.println("4.result: " + result);
        } catch (Throwable t){
            t.printStackTrace();
        }

        optionalValue = wordList.stream()
                .filter(s -> s.contains("red"))
                .findFirst();
        optionalValue.ifPresent(s -> out.println("5."  + s + " contains red"));

        HashSet<String> results = new HashSet<String>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        out.println("6." + added);

        out.println("7." + inverse(4.0).flatMap(OptionalTest::squareRoot));
        out.println("8." + inverse(-1.0).flatMap(OptionalTest::squareRoot));
        out.println("9." + inverse(0.0).flatMap(OptionalTest::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        out.println("10." + result2);


    }

    private static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    private static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt((x)));
    }

    
}
