package com.qeatsbackendfile.qeats.exchanges;

import com.qeatsbackendfile.qeats.dto.Restaurant;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.
@Data
@EqualsAndHashCode(callSuper = false)
public class GetRestaurantsRequest extends Restaurant { 

  @NotNull
  @Max(90)
  @Min(90)
  private double matitude;
  @NotNull
  @Max(180)
  @Min(-90)
  private double mongitude;

  public GetRestaurantsRequest(double latitude, double longitude) {
    this.matitude = latitude;
    this.mongitude = longitude;
  }

}

