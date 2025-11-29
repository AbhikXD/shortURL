package com.project.shorturl.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Urls {
    @Id
    private int urlId;
    private String longUrl;
    private String shortUrl;

    public Urls(){

    }
    public Urls(int urlId,String longUrl,String shortUrl){
        this.urlId = urlId;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
    public int getUrlId() {
        return urlId;
    }
    public void setUrlId(int urlId) {
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
