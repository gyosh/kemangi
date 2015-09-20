package com.gyosh.worker;

public class Parameter {
    private String ownStopwordFilename;

    private boolean useStem;
    private boolean useStopwordRemoval;
    private boolean usePrintHistogram;

    private double zipfLeftBound;
    private double zipfRightBound;

    public Parameter() {
        ownStopwordFilename = null;

        useStem = true;
        useStopwordRemoval = true;
        usePrintHistogram = false;

        zipfLeftBound = 0;
        zipfRightBound = 0;
    }

    public void setOwnStopwordFilename(String ownStopwordFilename) {
        this.ownStopwordFilename = ownStopwordFilename;
    }

    public void setUseStem(boolean useStem) {
        this.useStem = useStem;
    }

    public void setUseStopwordRemoval(boolean useStopwordRemoval) {
        this.useStopwordRemoval = useStopwordRemoval;
    }

    public void setUsePrintHistogram(boolean usePrintHistogram) {
        this.usePrintHistogram = usePrintHistogram;
    }

    public void setZipfLeftBound(double zipfLeftBound) {
        this.zipfLeftBound = zipfLeftBound;
    }

    public void setZipfRightBound(double zipfRightBound) {
        this.zipfRightBound = zipfRightBound;
    }

    public boolean isUseStem() {
        return useStem;
    }

    public boolean isUseOwnStopwordRemoval() {
        return ownStopwordFilename != null;
    }

    public String getOwnStopwordFilename() {
        return ownStopwordFilename;
    }

    public boolean isUsePrintHistogram() {
        return usePrintHistogram;
    }

    public boolean isUseStopwordRemoval() {
        return useStopwordRemoval;
    }

    public double getZipfLeftBound() {
        return zipfLeftBound;
    }

    public double getZipfRightBound() {
        return zipfRightBound;
    }
}
