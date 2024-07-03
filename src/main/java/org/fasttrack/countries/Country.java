package org.fasttrack.countries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class Country {
    private final long id;
    private final String name;
    private final String capital;
    // @JsonIgnore
    private final long population;
    private final long area;
    private final String continent;
    private final List<String> neighbours;
}
