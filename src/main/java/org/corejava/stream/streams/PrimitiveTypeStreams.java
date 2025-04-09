package org.corejava.stream.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class PrimitiveTypeStreams
{
	public static void show(String title, IntStream stream)
	{
		final int SIZE = 10;
		int[] firstElements = stream.limit(SIZE + 1).toArray();
		out.print(title + ": ");
		for (int i = 0; i < firstElements.length; i++)
		{
			if(i > 0) out.print(", ");
			if(i < SIZE) out.print(firstElements[i]);
			else out.println("...");
		}
	}
	public static void main(String[] args) throws IOException
	{
		IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
		show("is1", is1);
		IntStream is2 = IntStream.range(5, 10);
		show("is2", is2);
		IntStream is3 = IntStream.rangeClosed(5, 10);
		show("is3", is3);

		Path path = Paths.get("alice30.txt");
		String contents = new String(Files.readString(path));

		Stream<String> words = Stream.of(contents.split("\\PL+"));
		IntStream is4 = words.mapToInt(String::length);
		show("is4", is4);

		String sentence = "\uD835\uDD46 is the set of octonions.";
		out.println(sentence);
		IntStream codes = sentence.codePoints();
		out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));

		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		IntStream is5 = integers.mapToInt(Integer::intValue);
		show("is5", is5);
	}
}
