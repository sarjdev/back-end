package dev.sarj.sarjdev.service.accesslog;

import dev.sarj.sarjdev.entity.domain.dao.AccessLog;
import dev.sarj.sarjdev.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;

    @Override
    public void save(AccessLog accessLog) {
        accessLogRepository.save(accessLog);
    }
}
