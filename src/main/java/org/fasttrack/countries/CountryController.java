package org.fasttrack.countries;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.fasttrack.countries.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping // GET http://host:port/countries?continent=Asia
    public List<Country> getAll(@RequestParam(required = false) String continent) {
        if (continent != null) {
            return countryService.getByContinent(continent);
        } else {
            return countryService.getAll();
        }
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable long id) {
        return countryService.getById(id).orElseThrow(() -> new EntityNotFoundException("Can't find country", id));
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable long id) {
        return countryService.delete(id);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable long id, @RequestBody Country country) {
        if (id != country.getId()) {
            throw new RuntimeException();
        }
        return countryService.update(country);
    }

    @GetMapping("/{id}/external")
    public ExternalCountry getCountriesFromExternal(@PathVariable long id){
        return countryService.getExternalDataById(id);
    }

}