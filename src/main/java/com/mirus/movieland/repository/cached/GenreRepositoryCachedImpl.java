package com.mirus.movieland.repository.cached;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Repository
@Primary
@RequiredArgsConstructor
public class GenreRepositoryCachedImpl implements GenreRepository {

    private GenreRepository genreRepository;

    private volatile List<Genre> genreCache;

    @Override
    public List<Genre> findAll() {
        return new ArrayList<>(genreCache);
    }

    @PostConstruct
    @Scheduled(cron = "${genre.clean.cron.expression}")
    public void refresh() {
        log.info("Refreshing genre cache ...");
        this.genreCache = new ArrayList<>(genreRepository.findAll());
        log.info("Finished refreshing genre cache");
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
