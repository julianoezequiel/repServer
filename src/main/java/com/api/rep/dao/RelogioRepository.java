package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Relogio;

@Repository
public interface RelogioRepository extends JpaRepository<Relogio, Integer> {

}
