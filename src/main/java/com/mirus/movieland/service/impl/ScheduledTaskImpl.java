package com.mirus.movieland.service.impl;

import com.mirus.movieland.service.CacheManager;
import com.mirus.movieland.service.ScheduledTask;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTaskImpl implements ScheduledTask {

    private final CacheManager cacheManager;

    @Override
    @Scheduled(cron = "${genre.clean.cron.expression}")
    public void cleanGenreCache() {
        cacheManager.cleanGenreCache();
    }
}
