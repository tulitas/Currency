package com.example.currency.utils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "FxRate")
public class FxRate {

    private String tp;
    private String dt;
    private List<CcyAmt> ccyAmts;

    @XmlElement(name = "Tp")
    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    @XmlElement(name = "Dt")
    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @XmlElement(name = "CcyAmt")
    public List<CcyAmt> getCcyAmts() {
        return ccyAmts;
    }

    public void setCcyAmts(List<CcyAmt> ccyAmts) {
        this.ccyAmts = ccyAmts;
    }
}

