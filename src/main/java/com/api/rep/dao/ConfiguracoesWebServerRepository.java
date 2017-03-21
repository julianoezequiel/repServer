package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.ConfigurcacoesWebServer;

@Repository
public interface ConfiguracoesWebServerRepository extends JpaRepository<ConfigurcacoesWebServer, Integer> {

}