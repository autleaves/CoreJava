package org.corejava.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.lang.System.out;

public class Transformation
{
    public static Stream<String> codePoints(String s) {

        ArrayList<String> result = new ArrayList<String>();
        int i = 0;
        while (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        return result.stream();
    }

    public static void main(String[] args)
    {
        Stream<String> res =  codePoints("code");
        res.forEach(System.out::println);
    }
}
