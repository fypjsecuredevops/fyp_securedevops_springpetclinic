package ZapModel;

public class ZapRiskLevels {
    private int high;
    private int medium;
    private int low;
    private int informational;

    public ZapRiskLevels(int high, int medium, int low, int informational) {
        this.high = high;
        this.medium = medium;
        this.low = low;
        this.informational = informational;
    }

    @Override
    public String toString() {
        return high+";"+medium+";"+low+";"+informational;
    }

    public int getHigh() {
        return high;
    }

    public int getMedium() {
        return medium;
    }

    public int getLow() {
        return low;
    }

    public int getInformational() {
        return informational;
    }
}