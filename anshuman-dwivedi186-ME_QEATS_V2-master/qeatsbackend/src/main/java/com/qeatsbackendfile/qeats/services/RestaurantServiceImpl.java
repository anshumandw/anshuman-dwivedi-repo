package com.qeatsbackendfile.qeats.services;

import com.qeatsbackendfile.qeats.dto.Restaurant;
import com.qeatsbackendfile.qeats.exchanges.GetRestaurantsRequest;
import com.qeatsbackendfile.qeats.exchanges.GetRestaurantsResponse;
import com.qeatsbackendfile.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;


  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {

    Double latitude = getRestaurantsRequest.getMatitude();
    Double longitude = getRestaurantsRequest.getMongitude();
    Double servingRadiusInKms = normalHoursServingRadiusInKms;
    if (isPeakHours(currentTime)) {
      servingRadiusInKms = peakHoursServingRadiusInKms;
    }
    
    List<Restaurant> restaurants = restaurantRepositoryService.findAllRestaurantsCloseBy(
            latitude, longitude, currentTime, servingRadiusInKms);

    return new GetRestaurantsResponse(restaurants);

  }

  private boolean isTimeWithInRange(LocalTime now, LocalTime startTime, LocalTime endTime) {
    return now.isAfter(startTime) && now.isBefore(endTime);
  }

  public boolean isPeakHours(LocalTime timeNow) {
    return isTimeWithInRange(timeNow, LocalTime.of(7, 59, 59), LocalTime.of(10, 00, 01))
      || isTimeWithInRange(timeNow, LocalTime.of(12, 59, 59), LocalTime.of(14, 00, 01))
      || isTimeWithInRange(timeNow, LocalTime.of(18, 59, 59), LocalTime.of(21, 00, 01));
  }


}

