package com.paper.model;

public class PrintingJob {

    private     String      pageType;
    private     int         totalPages;
    private     int         colourPages;
    private     int         monochromePages;
    private     boolean     isDoubleSided;


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


    public String getPageType() {
        return pageType;
    }



}
