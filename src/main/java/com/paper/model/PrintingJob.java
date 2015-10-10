package com.paper.model;


public class PrintingJob {

    private int totalPages;
    private int colourPages;
    private int monochromePages;
    private boolean isDoubleSided;


    public PrintingJob(int totalPages, int colourPages, boolean isDoubleSided) {
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



}
