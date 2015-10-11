package com.paper.model;


import java.math.BigDecimal;

public class InvoicedPrintingJob {
    
    private PrintingJob  printingJob;

    private BigDecimal   perUnitColorPrint;

    private BigDecimal   perUnitMonoPrint;

    private BigDecimal   colorPrintCost = BigDecimal.ZERO;

    private BigDecimal   monoPrintCost = BigDecimal.ZERO;

    private BigDecimal   totalCost= BigDecimal.ZERO;

    public InvoicedPrintingJob(PrintingJob printingJob) {
        this.printingJob = printingJob;
    }


    public BigDecimal getPerUnitColorPrint() {
        return perUnitColorPrint;
    }

    public void setPerUnitColorPrint(BigDecimal perUnitColorPrint) {
        this.perUnitColorPrint = perUnitColorPrint;
    }

    public BigDecimal getPerUnitMonoPrint() {
        return perUnitMonoPrint;
    }

    public void setPerUnitMonoPrint(BigDecimal perUnitMonoPrint) {
        this.perUnitMonoPrint = perUnitMonoPrint;
    }

    public BigDecimal getColorPrintCost() {
        return colorPrintCost;
    }

    public void setColorPrintCost(BigDecimal colorPrintCost) {
        this.colorPrintCost = colorPrintCost;
    }

    public BigDecimal getMonoPrintCost() {
        return monoPrintCost;
    }

    public void setMonoPrintCost(BigDecimal monoPrintCost) {
        this.monoPrintCost = monoPrintCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public PrintingJob getPrintingJob() {
        return printingJob;
    }
}
