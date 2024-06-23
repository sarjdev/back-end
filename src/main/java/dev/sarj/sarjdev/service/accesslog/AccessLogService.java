package dev.sarj.sarjdev.service.accesslog;

import dev.sarj.sarjdev.entity.domain.dao.AccessLog;

public interface AccessLogService {
    void save(AccessLog accessLog);
}
