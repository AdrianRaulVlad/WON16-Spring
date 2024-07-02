package org.fasttrack.countries;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class CountryReader {
    public List<Country> readCountries() {
        List<Country> countries = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/countries.txt"));
            long id = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                countries.add(stringToCountry(line, id++));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }

    private Country stringToCountry(String text, long id) {
       String[] tokens = text.split("\\|");
       Country.CountryBuilder countryBuilder = Country.builder()
               .id(id)
               .name(tokens[0])
               .capital(tokens[1])
               .population(Long.parseLong(tokens[2]))
               .area(Long.parseLong(tokens[3]))
               .continent(tokens[4]);
       if (tokens.length == 6) {
           countryBuilder.neighbours(Arrays.asList(tokens[5].split("~")));
       }
       return countryBuilder.build();
    }
}
