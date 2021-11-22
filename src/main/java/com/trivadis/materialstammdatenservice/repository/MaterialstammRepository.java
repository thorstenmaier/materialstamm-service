package com.trivadis.materialstammdatenservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trivadis.materialstammdatenservice.domain.Materialstamm;

public interface MaterialstammRepository extends JpaRepository<Materialstamm, Long> {

}
