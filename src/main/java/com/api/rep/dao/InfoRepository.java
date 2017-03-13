package com.api.rep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rep.entity.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {

}
