
package com.crio.warmup.stock.portfolio;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {

  private RestTemplate restTemplate;

  // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  //CHECKSTYLE:OFF

  // TODO: CRIO_TASK_MODULE_REFACTOR
  //  Extract the logic to call Tiingo third-party APIs to a separate function.
  //  Remember to fill out the buildUri function and use that.


  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException {

      String url = buildUri(symbol, from, to);
	    TiingoCandle[] tiingoCandleObject = restTemplate.getForObject(url, TiingoCandle[].class);
	    List<Candle> candles = Arrays.asList(tiingoCandleObject);
      return candles;
  }

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {
       String uriTemplate = "https://api.tiingo.com/tiingo/daily/"+symbol+"/prices?"
            + "startDate="+startDate.toString()+"&endDate="+endDate.toString()+"&token=f37b170e4eaba1ba48319e9b28cd5753fba5c9c9";

            return uriTemplate;
  }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate) {
        List<AnnualizedReturn> annualizedReturn = new ArrayList<>();
        for(PortfolioTrade i : portfolioTrades) {
        List<Candle> candle;
        try {
          candle = getStockQuote(i.getSymbol(), i.getPurchaseDate(), endDate);
          annualizedReturn.add(calculateAnnualizedReturns(endDate, i,
          candle.get(0).getOpen(), candle.get(candle.size()-1).getClose()));
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
        }
        Collections.sort(annualizedReturn, getComparator());
         return annualizedReturn;
  }

  private static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
      PortfolioTrade trade, Double buyPrice, Double sellPrice) {

        Double total_num_years = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.24;
        Double totalReturn = (sellPrice - buyPrice) / buyPrice;
        Double annualizedReturns = Math.pow((1 + totalReturn), (1 / total_num_years)) - 1;

      return new AnnualizedReturn(trade.getSymbol(), annualizedReturns, totalReturn);
  }
}
