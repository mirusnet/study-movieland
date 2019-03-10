package com.mirus.movieland.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:testContext.xml", "file:src/main/webapp/WEB-INF/movieland-servlet.xml", "classpath:spring/root-context.xml"})
public class ScheduledTaskTest {

    @Value("${genre.clean.cron.expression}")
    private String genreCacheCleanCronExpression;

    @Test
    public void testCacheCleanCronPresense() {
        Assert.assertEquals("0 0 0/4 * * *", genreCacheCleanCronExpression);
    }

}