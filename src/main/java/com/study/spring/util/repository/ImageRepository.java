package com.study.spring.util.repository;

import com.study.spring.util.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

}
