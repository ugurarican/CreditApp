package com.ugurarican.creditapp.entities;

import java.math.BigDecimal;

public class CreditResult {

    // Bu class ile onay ve limit bilgileri tanımlanmıştır.

    private boolean ratification;
    private BigDecimal limit;

    public static  final  CreditResult REDDI = new CreditResult(false,BigDecimal.ZERO);
    public  static  final  CreditResult TALEBINIZ_ALINDI = new CreditResult(true,BigDecimal.ZERO);

    public CreditResult(boolean ratification, BigDecimal limit){
        this.ratification = ratification;
        this.limit = limit;
    }

    public boolean isRatification(){
        return ratification;
    }

    public BigDecimal getLimit(){
        return limit;
    }

}


