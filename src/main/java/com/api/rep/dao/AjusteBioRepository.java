package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.AjustesBio;

@Repository
public interface AjusteBioRepository extends JpaRepository<AjustesBio, Integer> {

}
