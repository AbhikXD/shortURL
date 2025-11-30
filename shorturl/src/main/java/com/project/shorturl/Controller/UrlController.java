package com.project.shorturl.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.shorturl.Models.UrlRequest;
import com.project.shorturl.Service.ShortUrlService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UrlController {

    private ShortUrlService shortUrlService;

    public UrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/create")
    public String createShortUrl(@RequestBody UrlRequest urlReq) {
        return shortUrlService.urlShorten(urlReq.getLongUrl());
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToLongUrl(@PathVariable String shortUrl) {
        String longUrl = shortUrlService.getLongUrl(shortUrl);
        System.out.println("Redirecting to: " + longUrl);
        if (longUrl != null) {
            return ResponseEntity.status(302).header("Location", longUrl).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
