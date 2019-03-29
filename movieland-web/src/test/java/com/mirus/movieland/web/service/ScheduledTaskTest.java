package com.mirus.movieland.web.service;


import com.mirus.movieland.web.config.RootConfig;
import com.mirus.movieland.web.config.TestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, TestContext.class})
public class ScheduledTaskTest {

    @Value("${genre.clean.cron.expression}")
    private String genreCacheCleanCronExpression;

    @Test
    public void testCacheCleanCronPresense() {
        Assert.assertEquals("0 0 0/4 * * *", genreCacheCleanCronExpression);
    }

    @Value("${currency.update.cron.expression}")
    private String currencyUpdateCronExpression;

    @Test
    public void testCurrencyUpdateCronPresense() {
        Assert.assertEquals("0 0 8 * * *", currencyUpdateCronExpression);
    }
}