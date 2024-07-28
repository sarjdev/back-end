package dev.sarj.sarjdev.repository;

import dev.sarj.sarjdev.entity.domain.dao.AccessLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends CrudRepository<AccessLog, Long> {
}
