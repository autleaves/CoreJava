package org.corejava.stream.collecting;

import static java.lang.System.out;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class DownstreamCollectors
{
	public static class City
	{
		private String name;
		private String state;
		private int population;

		public City(String name, String state, int population)
		{
			this.name = name;
			this.state = state;
			this.population = population;
		}
		public String getName()
		{
			return name;
		}
		public String getState()
		{
			return state;
		}
		public int getPopulation()
		{
			return population;
		}
		public static Stream<City> readCities(String filename) throws IOException
		{
			return Files.lines(Paths.get(filename))
					.map(l -> l.split(","))
					.map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
//					.map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
		}

		public static void main(String[] args) throws IOException
		{
			Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
			Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
			out.println("countryToLocaleSet: " + countryToLocaleSet);

			locales = Stream.of(Locale.getAvailableLocales());
			Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(Locale::getCountry, counting()));
			out.println("countryToLocaleCounts: " + countryToLocaleCounts);

			Stream<City> cities = readCities("cities.txt");
			Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
//			Map<String, List<City>> stateToCityPopulation = cities.collect(groupingBy(City::getState));
			out.println("stateToCityPopulation: " + stateToCityPopulation);

			cities = readCities("cities.txt");
			Map<String, Optional<String>> stateToLongestCityName = cities
					.collect(groupingBy(City::getState,
							mapping(City::getName, maxBy(Comparator.comparing(String::length)))));
			out.println("stateToLongestCityName: " + stateToLongestCityName);

			locales = Stream.of(Locale.getAvailableLocales());
			Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(
					Locale::getDisplayCountry, mapping(Locale::getDisplayLanguage, toSet())));
			out.println("countryToLanguages: " + countryToLanguages);

			cities = readCities("cities.txt");
			Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
					.collect(groupingBy(City::getState, summarizingInt(City::getPopulation)));
			out.println(stateToCityPopulationSummary.get("Sicuan"));

			cities = readCities("cities.txt");
			Map<String, String> stateToCityNames = cities.collect(groupingBy(
					City::getState,
					reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
			cities = readCities("cities.txt");
			stateToCityNames = cities.collect(groupingBy(City::getState,
					mapping(City::getName, joining(", "))));
			out.println("stateToCityNames: " + stateToCityNames);

		}
	}
}
