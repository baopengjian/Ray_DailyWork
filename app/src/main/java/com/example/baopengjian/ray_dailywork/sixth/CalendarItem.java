package com.example.baopengjian.ray_dailywork.sixth;

/**
 * Created by Ray on 2019-7-18.
 */
public class CalendarItem {

    public static final String CPMC = "cpmc";
    public static final String CPDM = "cpdm";
    public static final String QGJE = "qgje";
    public static final String CPLX = "cplx";
    public static final String CPLXSM = "cplxsm";
    public static final String ZDSY = "zdsy";
    public static final String CPQX = "cpqx";
    public static final String ZGSY = "zgsy";
    public static final String FXJB = "fxjb";
    public static final String CPSYSM = "cpsysm";
    public static final String XSZT = "xszt";
    public static final String WEEK = "week";
    public static final String RQ = "rq";

    public String eventId;
    public String cpmc;
    public String cpdm;
    public String qgje;
    /**
     * 产品类型。0-新客专享 1-收益凭证 2-信托 3-私募基金 4-资管产品
     */
    public String cplx;
    public String cplxsm;
    public String zdsy;
    public String cpqx;
    public String zgsy;
    public String fxjb;
    public String cpsysm;
    public String xszt;
    public String rq;
    public String week;
    public String txjssj;
    public String txkssj;

    public String getCplxsm() {
        return cplxsm;
    }

    public void setCplxsm(String cplxsm) {
        this.cplxsm = cplxsm;
    }

    public String getTxjssj() {
        return txjssj;
    }

    public void setTxjssj(String txjssj) {
        this.txjssj = txjssj;
    }

    public String getTxkssj() {
        return txkssj;
    }

    public void setTxkssj(String txkssj) {
        this.txkssj = txkssj;
    }

    public boolean isRemind = false;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getCpdm() {
        return cpdm;
    }

    public void setCpdm(String cpdm) {
        this.cpdm = cpdm;
    }

    public String getQgje() {
        return qgje;
    }

    public void setQgje(String qgje) {
        this.qgje = qgje;
    }

    public String getCplx() {
        return cplx;
    }

    public void setCplx(String cplx) {
        this.cplx = cplx;
    }

    public String getZdsy() {
        return zdsy;
    }

    public void setZdsy(String zdsy) {
        this.zdsy = zdsy;
    }

    public String getCpqx() {
        return cpqx;
    }

    public void setCpqx(String cpqx) {
        this.cpqx = cpqx;
    }

    public String getZgsy() {
        return zgsy;
    }

    public void setZgsy(String zgsy) {
        this.zgsy = zgsy;
    }

    public String getFxjb() {
        return fxjb;
    }

    public void setFxjb(String fxjb) {
        this.fxjb = fxjb;
    }

    public String getCpsysm() {
        return cpsysm;
    }

    public void setCpsysm(String cpsysm) {
        this.cpsysm = cpsysm;
    }

    public String getXszt() {
        return xszt;
    }

    public void setXszt(String xszt) {
        this.xszt = xszt;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setRemind(boolean remind) {
        isRemind = remind;
    }
}
