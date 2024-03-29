package com.qeatsbackendfile.qeats.repositoryservices;

import ch.hsr.geohash.GeoHash;
import com.qeatsbackendfile.qeats.dto.Restaurant;
import com.qeatsbackendfile.qeats.globals.GlobalConstants;
import com.qeatsbackendfile.qeats.models.RestaurantEntity;
import com.qeatsbackendfile.qeats.repositories.RestaurantRepository;
import com.qeatsbackendfile.qeats.utils.GeoLocation;
import com.qeatsbackendfile.qeats.utils.GeoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.devtools.autoconfigure.DevToolsProperties.Restart;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;


@Service
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService {


  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  private boolean isOpenNow(LocalTime time, RestaurantEntity res) {
    LocalTime openingTime = LocalTime.parse(res.getOpensAt());
    LocalTime closingTime = LocalTime.parse(res.getClosesAt());

    return time.isAfter(openingTime) && time.isBefore(closingTime);
  }

  // TODO: qeatsbackendfile_TASK_MODULE_NOSQL
  // Objectives:
  // 1. Implement findAllRestaurantsCloseby.
  // 2. Remember to keep the precision of GeoHash in mind while using it as a key.
  // Check RestaurantRepositoryService.java file for the interface contract.
  public List<Restaurant> findAllRestaurantsCloseBy(Double latitude,
      Double longitude, LocalTime currentTime, Double servingRadiusInKms) {

    List<Restaurant> restaurants = new ArrayList<>();
    ModelMapper modelMapper = modelMapperProvider.get();
    
    // Query query = new Query();
    
    // query.addCriteria(Criteria.where("id").in("restaurants"));
    // mongoTemplate.find(query, RestaurantEntity.class);
    List<RestaurantEntity> restaurantEntity = restaurantRepository.findAll();
    
    for (RestaurantEntity restaurantss: restaurantEntity) {
      if (isRestaurantCloseByAndOpen(restaurantss, 
          currentTime, latitude, longitude, servingRadiusInKms)) {

        Restaurant restaurant = modelMapper.map(restaurantss, Restaurant.class);
        restaurants.add(restaurant);

      }
    }
    
    // Query query = new Query();
    // query.addCriteria(Criteria.where())

    return restaurants;
  }








  // TODO: qeatsbackendfile_TASK_MODULE_NOSQL
  // Objective:
  // 1. Check if a restaurant is nearby and open. If so, it is a candidate to be returned.
  // NOTE: How far exactly is "nearby"?

  /**
   * Utility method to check if a restaurant is within the serving radius at a given time.
   * @return boolean True if restaurant falls within serving radius and is open, false otherwise
   */
  private boolean isRestaurantCloseByAndOpen(RestaurantEntity restaurantEntity,
      LocalTime currentTime, Double latitude, Double longitude, Double servingRadiusInKms) {
    if (isOpenNow(currentTime, restaurantEntity)) {
      return GeoUtils.findDistanceInKm(latitude, longitude,
          restaurantEntity.getLatitude(), restaurantEntity.getLongitude())
          < servingRadiusInKms;
    }

    return false;
  }



}

