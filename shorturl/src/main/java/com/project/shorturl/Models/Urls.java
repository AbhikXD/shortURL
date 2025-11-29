package com.project.shorturl.Models;

@Entity
public class Urls {
    private String urlId;
    private String longUrl;
    private String shortUrl;

    public Urls(){

    }
    public Urls(String urlId,String longUrl,String shortUrl){
        this.urlId = urlId;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
    public String getUrlId() {
        return urlId;
    }
    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }
    public String getLongUrl() {
        return longUrl;
    }
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
    public String getShortUrl() {
        return shortUrl;

    }
}
