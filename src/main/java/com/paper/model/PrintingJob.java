package com.paper.model;


import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class PrintingJob {

    private String pageType;
    private int totalPages;
    private int colourPages;
    private int monochromePages;
    private boolean isDoubleSided;
    private BigDecimal cost;


    public PrintingJob(String pageType, int totalPages, int colourPages, boolean isDoubleSided) {
        this.pageType = pageType;
        this.totalPages = totalPages;
        this.colourPages = colourPages;
        this.isDoubleSided = isDoubleSided;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getColourPages() {
        return colourPages;
    }

    public int getMonochromePages() {
        return totalPages-colourPages;
    }

    public boolean isDoubleSided() {
        return isDoubleSided;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPageType() {
        return pageType;
    }

    static  DecimalFormat df = new DecimalFormat();
    static {
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
    }
    @Override
    public String toString() {
        return "PrintingJob{" +
                "pageType='" + pageType + '\'' +
                ", totalPages=" + totalPages +
                ", colourPages=" + colourPages +
                ", monochromePages=" + getMonochromePages() +
                ", isDoubleSided=" + isDoubleSided +
                ", cost=" + df.format(cost) +
                " (AUD) }";
    }
}
