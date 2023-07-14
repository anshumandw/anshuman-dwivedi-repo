
package com.crio.warmup.stock;


import com.crio.warmup.stock.dto.*;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;
import com.crio.warmup.stock.portfolio.PortfolioManagerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {

  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {

    List<String> symbolsList = new ArrayList<>();

    File fileSymbol = resolveFileFromResources(args[0]);

    ObjectMapper om = getObjectMapper();

    PortfolioTrade[] trade = om.readValue(fileSymbol, PortfolioTrade[].class);

    for (PortfolioTrade i : trade) {
      symbolsList.add(i.getSymbol());
    }

    return symbolsList;

  }

  //  Find out the closing price of each stock on the end_date and return the list
  //  of all symbols in ascending order by its close value on end date.

  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>

  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {

    List<PortfolioTrade> trade = readTradesFromJson(args[0]);

    String token = getToken();
    RestTemplate restTemplate = new RestTemplate();
    LocalDate endDate = LocalDate.parse(args[1]);
    List<TotalReturnsDto> totalReturnsDtos = new ArrayList<>();

    for (PortfolioTrade i : trade) {
      String symbol = i.getSymbol();
      String url = prepareUrl(i, endDate, token);
      TiingoCandle[] tiingoCandleObject = restTemplate.getForObject(url, TiingoCandle[].class);
      if (tiingoCandleObject == null) {
        continue;
      }
      TotalReturnsDto totalReturns =
          new TotalReturnsDto(symbol, tiingoCandleObject[tiingoCandleObject.length - 1].getClose());

      totalReturnsDtos.add(totalReturns);

    }
    
    Collections.sort(totalReturnsDtos, new sortTotalReturnsDtoComparator());

    List<String> sortedSymbols = new ArrayList<>();

    for (TotalReturnsDto i : totalReturnsDtos) {
      sortedSymbols.add(i.getSymbol());
    }

    return sortedSymbols;
    
 }

 // Note:
 // Remember to confirm that you are getting same results for annualized returns as in Module 3.

 //  After refactor, make sure that the tests pass by using these two commands
 //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
 //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile

 public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
   File fileSymbol = resolveFileFromResources(filename);

   ObjectMapper om = getObjectMapper();

   PortfolioTrade[] trade = om.readValue(fileSymbol, PortfolioTrade[].class);

   List<PortfolioTrade> portfolioTrade = Arrays.asList(trade);

   return portfolioTrade;
 }

  //  Build the Url using given parameters and use this function in your code to cann the API.

  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    String symbol = trade.getSymbol();
    LocalDate startDate = trade.getPurchaseDate();
     return "https://api.tiingo.com/tiingo/daily/"+ symbol +"/prices?startDate=" + startDate.toString() + "&endDate="+ endDate + "&token=" + token;
  }

  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.

  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/home/crio-user/workspace/anshuman-dwivedi186-ME_QMONEY_V2/qmoney/bin/main/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@5542c4ed";
     String functionNameFromTestFileInStackTrace = "mainReadFile";
     String lineNumberFromTestFileInStackTrace = "29";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }

  //  Now that you have the list of PortfolioTrade and their data, calculate annualized returns
  //  for the stocks provided in the Json.
  //  Use the function you just wrote #calculateAnnualizedReturns.
  //  Return the list of AnnualizedReturns sorted by annualizedReturns in descending order.

  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.

  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest
  
  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
     return candles.get(0).getOpen();
  }

  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
     return candles.get(candles.size()-1).getClose();
  }

  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    String url = prepareUrl(trade, endDate, token);
	  RestTemplate restTemplate = new RestTemplate();
	  TiingoCandle[] tiingoCandleObject = restTemplate.getForObject(url, TiingoCandle[].class);
	  List<Candle> candles = Arrays.asList(tiingoCandleObject);
     return candles;
  }

  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {
        List<PortfolioTrade> trade = readTradesFromJson(args[0]);
        String token = getToken();
        LocalDate endDate = LocalDate.parse(args[1]);
        List<AnnualizedReturn> annualizedReturn = new ArrayList<>();
        for(PortfolioTrade i : trade) {
        List<Candle> candle = fetchCandles(i, endDate, token);
        if(candle == null) {
          continue;
        }
        annualizedReturn.add(calculateAnnualizedReturns(endDate, i,
            getOpeningPriceOnStartDate(candle), getClosingPriceOnEndDate(candle)));
        }
        Collections.sort(annualizedReturn, new sortAnnualizedReturns());
         return annualizedReturn;
  }

  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn

  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {

        Double total_num_years = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.24;
        Double totalReturn = (sellPrice - buyPrice) / buyPrice;
        Double annualizedReturns = Math.pow((1 + totalReturn), (1 / total_num_years)) - 1;

      return new AnnualizedReturn(trade.getSymbol(), annualizedReturns, totalReturn);
  }

  public static String getToken() {
    return "f37b170e4eaba1ba48319e9b28cd5753fba5c9c9";
  }

  //  Once you are done with the implementation inside PortfolioManagerImpl and
  //  PortfolioManagerFactory, create PortfolioManager using PortfolioManagerFactory.
  //  Refer to the code from previous modules to get the List<PortfolioTrades> and endDate, and
  //  call the newly implemented method in PortfolioManager to calculate the annualized returns.

  // Note:
  // Remember to confirm that you are getting same results for annualized returns.

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
       String file = args[0];
       LocalDate endDate = LocalDate.parse(args[1]);
       String contents = readFileAsString(file);
       
       ObjectMapper objectMapper = getObjectMapper();

       RestTemplate restTemplate = new RestTemplate();

       PortfolioTrade[] portfolioTrades = objectMapper.readValue(contents, PortfolioTrade[].class);
      
       PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager(restTemplate);
       return portfolioManager.calculateAnnualizedReturn(Arrays.asList(portfolioTrades), endDate);
  }


  private static String readFileAsString(String file) throws UnsupportedEncodingException, IOException, URISyntaxException {
    return new String(Files.readAllBytes(resolveFileFromResources(file).toPath()), "UTF-8");
  }

  
  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());
  }
}

