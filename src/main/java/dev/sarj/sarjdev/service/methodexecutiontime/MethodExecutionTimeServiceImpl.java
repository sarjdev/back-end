package dev.sarj.sarjdev.service.methodexecutiontime;

import dev.sarj.sarjdev.core.aspect.performance.ElapsedType;
import dev.sarj.sarjdev.core.aspect.performance.Performance;
import dev.sarj.sarjdev.entity.domain.dao.MethodExecutionTime;
import dev.sarj.sarjdev.repository.MethodExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Performance(value = ElapsedType.SERVICE_ELAPSED, exclude = true)
public class MethodExecutionTimeServiceImpl implements MethodExecutionTimeService {

    private final MethodExecutionTimeRepository methodExecutionTimeRepository;

    @Override
    public void save(MethodExecutionTime executionTime) {
        methodExecutionTimeRepository.save(executionTime);
    }
}
