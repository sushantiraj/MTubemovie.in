package com.MTube.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MTube.entity.Movie;

public interface movieRepository extends JpaRepository<Movie,Integer>{

}
