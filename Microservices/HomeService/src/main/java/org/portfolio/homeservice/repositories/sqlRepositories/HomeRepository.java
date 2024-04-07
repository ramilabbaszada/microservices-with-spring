package org.portfolio.homeservice.repositories.sqlRepositories;

import org.portfolio.homeservice.entities.Home;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HomeRepository extends JpaRepository<Home,Long> {
}
