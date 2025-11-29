package com.project.shorturl.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Urls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;
    private String longUrl;
    private String shortUrl;

    public Urls(){

    }
    public Urls(long urlId,String longUrl,String shortUrl){
        this.urlId = urlId;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
    public long getUrlId() {
        return urlId;
    }
    public void setUrlId(long urlId) {
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
