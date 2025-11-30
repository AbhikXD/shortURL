package com.project.shorturl.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import com.project.shorturl.Models.Urls;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository <Urls, Long> {

  //  @Query("Select url from Urls url where url.shortUrl = :shortUrl")
    Optional<Urls> findByShortUrl(String shortUrl);
}
