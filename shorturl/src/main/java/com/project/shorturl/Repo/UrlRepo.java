package com.project.shorturl.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.shorturl.Models.Urls;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository <Urls, Integer> {

}
