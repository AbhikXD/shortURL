package com.project.shorturl.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.shorturl.Models.Urls;
import com.project.shorturl.Repo.UrlRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {
    @Autowired
    UrlRepo urlRepo;
    @Autowired
    UrlEnvironmentService envService;
    public String urlShorten(String longUrl){
        
        Urls url = new Urls();
        url.setLongUrl(longUrl);
        Urls savedUrl = urlRepo.save(url);
        String shortUrl = encode(savedUrl.getUrlId());
        savedUrl.setShortUrl(shortUrl);
        urlRepo.save(savedUrl);
        String baseUrl = envService.getBaseUrl();
        return baseUrl + "/" + shortUrl;
    }

    public String getLongUrl(String shortUrl){
        Optional<Urls> url = urlRepo.findByShortUrl(shortUrl);
        if(url.isPresent()){
            return url.get().getLongUrl();
        }
        return null;
    }

    private String encode(long urlId){
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        while(urlId > 0){
            int remainder = (int)(urlId % 62);
            shortUrl.append(characters.charAt(remainder));
            urlId = urlId / 62;
        }
        String path = shortUrl.reverse().toString();
        return path;
    }

}
