package com.crio.warmup.stock;

import java.util.Comparator;
import com.qmoneyfile.warmup.stock.dto.TotalReturnsDto;

public class sortTotalReturnsDtoComparator implements Comparator<TotalReturnsDto> {

    @Override
    public int compare(TotalReturnsDto o1, TotalReturnsDto o2) {
        if(o1.getClosingPrice() > o2.getClosingPrice()) {
            return 1;
        } else if(o1.getClosingPrice() < o2.getClosingPrice()) {
            return -1;
        } else return 0;
    }
    
}
