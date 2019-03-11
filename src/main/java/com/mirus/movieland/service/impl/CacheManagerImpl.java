package com.mirus.movieland.service.impl;

import com.mirus.movieland.service.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheManagerImpl implements CacheManager {
    @Override
    @CacheEvict(value = "genres", allEntries = true)
    public void cleanGenreCache() {
        log.info("Cleaning genres cache ...");
    }
}
