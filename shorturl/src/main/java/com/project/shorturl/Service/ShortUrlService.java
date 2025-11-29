package com.project.shorturl.Service;
import org.springframework.stereotype.Component;

import com.project.shorturl.Models.Urls;
import com.project.shorturl.Repo.UrlRepo;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ShortUrlService {
    @Autowired
    UrlRepo urlRepo;

    public void urlShorten(String longUrl){
        
        Urls url = new Urls();
        url.setLongUrl(longUrl);
        Urls savedUrl = urlRepo.save(url);

    }

}
