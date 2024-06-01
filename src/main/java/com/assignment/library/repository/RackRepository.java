package com.assignment.library.repository;

import com.assignment.library.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {
}
