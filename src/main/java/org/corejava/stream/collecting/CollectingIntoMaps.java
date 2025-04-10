package org.corejava.stream.collecting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps
{
	public static class Person
	{
        private int id;
		private String name;

		public Person(int id, String name)
		{
			this.id = id;
			this.name = name;
		}

		public int getId()
		{
			return id;
		}

		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return "Person [id=" + id + ", name=" + name + "]";
		}
	}

    public static Stream<Person> people()
    {
        return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
    }

    public static void main(String[] args)
    {
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity(), 
                (existingValue, newValue) -> { throw new IllegalStateException(); }, 
                TreeMap::new));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, String> languageNames = locales.collect(
				Collectors.toMap(
						Locale::getDisplayLanguage,
						l -> l.getDisplayLanguage(l),
						(existingValue, newValue) -> existingValue));
        System.out.println("languageNames: " + languageNames);

		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryLanguageSets = locales.collect(
				Collectors.toMap(
						Locale::getDisplayCountry,
						l -> Set.of(l.getDisplayLanguage()),
						(a, b) -> {
							Set<String> union = new HashSet<>(a);
							union.addAll(b);
							return union;
						}));
		System.out.println("countryLanguageSets: " + countryLanguageSets);

		// Grouping and Partitioning
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, List<Locale>> countryToLocales = locales.collect(
				Collectors.groupingBy(Locale::getCountry));
		List<Locale> swissLocales = countryToLocales.get("CH");
		swissLocales.forEach(System.out::println);

		locales = Stream.of(Locale.getAvailableLocales());
		Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(
				Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
		List<Locale> englishLocales = englishAndOtherLocales.get(true);
		System.out.println("englishLocales: " + englishLocales);
    }
}
