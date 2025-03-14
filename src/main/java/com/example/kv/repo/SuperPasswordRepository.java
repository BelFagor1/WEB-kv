package com.example.kv.repo;

import com.example.kv.model.SuperPassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperPasswordRepository extends CrudRepository<SuperPassword, Long> {
}
