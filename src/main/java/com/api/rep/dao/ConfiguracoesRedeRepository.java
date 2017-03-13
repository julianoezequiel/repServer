package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.ConfiguracoesRede;

@Repository
public interface ConfiguracoesRedeRepository extends JpaRepository<ConfiguracoesRede, Integer> {

}
