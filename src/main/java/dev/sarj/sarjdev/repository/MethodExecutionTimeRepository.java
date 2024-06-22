package dev.sarj.sarjdev.repository;

import dev.sarj.sarjdev.entity.domain.dao.MethodExecutionTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodExecutionTimeRepository extends CrudRepository<MethodExecutionTime, Long> {
}
