package com.qeatsbackendfile.qeats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//  Implement Restaurant class.
// Complete the class such that it produces the following JSON during serialization.
// {
//  "restaurantId": "10",
//  "name": "A2B",
//  "city": "Hsr Layout",
//  "imageUrl": "www.google.com",
//  "latitude": 20.027,
//  "longitude": 30.0,
//  "opensAt": "18:00",
//  "closesAt": "23:00",
//  "attributes": [
//    "Tamil",
//    "South Indian"
//  ]
// }

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

  @JsonIgnore
  @NotNull
  private String id;
  @JsonProperty("restaurantId")
    private String restaurantId;
  @JsonProperty("name")
    private String name;
  @JsonProperty("city")
    private String city;
  @JsonProperty("imageUrl")
    private String imageUrl;
  @JsonProperty("latitude")
    private Double latitude;
  @JsonProperty("longitude")
    private Double longitude;
  @JsonProperty("opensAt")
    private String opensAt;
  @JsonProperty("closesAt")
    private String closesAt;
  @JsonProperty("attributes")
    private List<String> attributes = new ArrayList<>();

  public Restaurant(Double latitude2, Double longitude2) {
    this.latitude = latitude2;
    this.longitude = longitude2;
  }

}

