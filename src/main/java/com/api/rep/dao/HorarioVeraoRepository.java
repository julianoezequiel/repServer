package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.HorarioVerao;

@Repository
public interface HorarioVeraoRepository extends JpaRepository<HorarioVerao, Integer> {

}
