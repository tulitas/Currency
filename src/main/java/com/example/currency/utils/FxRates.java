package com.example.currency.utils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "FxRates")
public class FxRates {

    private List<FxRate> fxRateList;

    @XmlElement(name = "FxRate")
    public List<FxRate> getFxRateList() {
        return fxRateList;
    }

    public void setFxRateList(List<FxRate> fxRateList) {
        this.fxRateList = fxRateList;
    }
}
