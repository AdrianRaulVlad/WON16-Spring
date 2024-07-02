package org.fasttrack.countries;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryReader countryReader;
    private final List<Country> countries = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("Post constructor on Country Service");
        countries.addAll(countryReader.readCountries());
        System.out.println("Service initialized with " + countries.size() + " countries.");
        System.out.println("Service initialized with " + countries);
    }

    public List<Country> getAll() {
        return countries;
    }

    public List<Country> getByContinent(String continent) {
        return countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                //.toList();
                .collect(Collectors.toList());
    }

    public Optional<Country> getById(long id) {
        return countries.stream()
                .filter(country -> country.getId() == id)
                .findFirst();
    }

    public Country delete(long id) {
        //Country countryToBeDeleted = getById(id).orElseThrow(() -> new RuntimeException("Country not found"));
        Country countryToBeDeleted = getById(id).orElseThrow(RuntimeException::new);
        countries.remove(countryToBeDeleted);
        return countryToBeDeleted;
    }

    public Country add(Country country) {
        if (!countries.contains(country)) {
            countries.add(country);
        }
        return country;
    }

    public Country update(Country country) {
        delete(country.getId());
        countries.add(country);
        return country;
    }

    public List<String> getAllCountryNames() {
        return countries.stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    public String getCapital(String countryName) {
        return countries.stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .map(Country::getCapital)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public long getPopulation(String countryName) {
        return countries.stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .map(Country::getPopulation)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public List<Country> getCountriesInContinent(String continent) {
        return getByContinent(continent);
    }

    public List<String> getCountryNeighbours(String countryName) {
        return countries.stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .map(Country::getNeighbours)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public List<Country> getCountriesInContinentWithPopulationLargerThan(String continent, long population) {
        return countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .filter(country -> country.getPopulation() > population)
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesThatNeighborXButNotY(String neighborX, String neighborY) {
        return countries.stream()
                .filter(country -> country.getNeighbours().contains(neighborX) && !country.getNeighbours().contains(neighborY))
                .collect(Collectors.toList());
    }
}
