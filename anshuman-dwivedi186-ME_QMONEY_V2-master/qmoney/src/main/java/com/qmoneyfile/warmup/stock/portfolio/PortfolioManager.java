
package com.qmoneyfile.warmup.stock.portfolio;

import com.qmoneyfile.warmup.stock.dto.AnnualizedReturn;
import com.qmoneyfile.warmup.stock.dto.PortfolioTrade;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDate;
import java.util.List;

public interface PortfolioManager {


  //CHECKSTYLE:OFF


  List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate)
  ;
}

