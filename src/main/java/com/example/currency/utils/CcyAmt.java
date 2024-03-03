package com.example.currency.utils;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.Date;

public class CcyAmt {

    private String ccy;
    private String amt;

    @XmlElement(name = "Ccy")
    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    @XmlElement(name = "Amt")
    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
