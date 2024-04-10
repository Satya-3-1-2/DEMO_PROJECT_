package model;

public class AnalyticsData {
    private int id;
    private int urlId;
    private int clicks;

    public AnalyticsData() {
    }

    public AnalyticsData(int urlId, int clicks) {
        this.urlId = urlId;
        this.clicks = clicks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
