package com.qeatsbackendfile.qeats.controller;

import com.qeatsbackendfile.qeats.exchanges.GetRestaurantsRequest;
import com.qeatsbackendfile.qeats.exchanges.GetRestaurantsResponse;
import com.qeatsbackendfile.qeats.services.RestaurantService;
import java.time.LocalTime;
import javax.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: qeatsbackendfile_TASK_MODULE_RESTAURANTSAPI
// Implement Controller using Spring annotations.
// Remember, annotations have various "targets". They can be class level, method level or others.
@RestController
public class RestaurantController {

  private final Logger log = LoggerFactory.getLogger(RestaurantController.class);

  public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
  public static final String RESTAURANTS_API = "/restaurants";
  public static final String RESTAURANTS_API_URL = RESTAURANT_API_ENDPOINT + RESTAURANTS_API;
  public static final String MENU_API = "/menu";
  public static final String CART_API = "/cart";
  public static final String CART_ITEM_API = "/cart/item";
  public static final String CART_CLEAR_API = "/cart/clear";
  public static final String POST_ORDER_API = "/order";
  public static final String GET_ORDERS_API = "/orders";


  @Autowired
  private RestaurantService restaurantService;

  @GetMapping(RESTAURANTS_API)
  public ResponseEntity<GetRestaurantsResponse> getRestaurants(
       GetRestaurantsRequest getRestaurantsRequest) {

    log.info("getRestaurants called with {}", getRestaurantsRequest);
    GetRestaurantsResponse getRestaurantsResponse;

    //CHECKSTYLE:OFF
    getRestaurantsResponse = restaurantService
          .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
          
    log.info("getRestaurants returned {}", getRestaurantsResponse);
    //CHECKSTYLE:ON

    return ResponseEntity.ok().body(getRestaurantsResponse);
  }


  @GetMapping(RESTAURANTS_API_URL)
  public ResponseEntity<GetRestaurantsResponse> getHttpStatus(@Valid
        GetRestaurantsRequest getRestaurantsRequest) {

    log.info("getRestaurants called with {}", getRestaurantsRequest);
    GetRestaurantsResponse getRestaurantsResponse;

    //CHECKSTYLE:OFF
    getRestaurantsResponse = restaurantService
          .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
          
    log.info("getRestaurants returned {}", getRestaurantsResponse);
    //CHECKSTYLE:ON

    return ResponseEntity.ok().body(getRestaurantsResponse);
  }













}

