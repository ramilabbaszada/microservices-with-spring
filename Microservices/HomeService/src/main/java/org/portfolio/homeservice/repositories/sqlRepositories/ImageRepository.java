package org.portfolio.homeservice.repositories.sqlRepositories;

import jakarta.transaction.Transactional;
import org.portfolio.homeservice.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {

    @Transactional
    @Modifying
    @Query("delete  from Image i where i.home.id=:id")
    void deleteByHomeId(Long id);

}
