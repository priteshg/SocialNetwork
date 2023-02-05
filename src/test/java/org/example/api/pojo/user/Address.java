package org.example.api.pojo.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Address {
    private String street;
    private String suite;
    private String zipcode;
    private String city;
    private Geo geo;
}
