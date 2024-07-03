package org.fasttrack.countries;

public record ExternalCountry(ExternalCountryName name, String startOfWeek) {
}

record ExternalCountryName(String common) {

}