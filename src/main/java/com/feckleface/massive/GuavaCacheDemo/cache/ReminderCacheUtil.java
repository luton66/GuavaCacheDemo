package com.feckleface.massive.GuavaCacheDemo.cache;

import com.feckleface.massive.GuavaCacheDemo.model.Reminder;
import com.feckleface.massive.GuavaCacheDemo.repository.ReminderRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Basic implementation  
 *
 * @author Leigh Edwards
 */
@Component
public class ReminderCacheUtil {

    private static Logger LOGGER = LoggerFactory.getLogger("ReminderCacheUtil.class");

    private final static String URL_VALUE = "http://localhost:57018";

    private final LoadingCache<String, Reminder> reminderCache;

    private ReminderRepository repository;

    @Autowired
    public ReminderCacheUtil(final ReminderRepository repository) {
        this.repository = repository;

        reminderCache = CacheBuilder.newBuilder()
                                    .expireAfterWrite(10, TimeUnit.SECONDS)
                                    .build(new ReminderCacheLoader<>(repository, Reminder.class));

    }

    public Reminder retrieveValue(String reminderName) {
        try {
            LOGGER.info(reminderCache.stats().toString());
            return reminderCache.get(reminderName);
        } catch (ExecutionException e) {
            System.out.println("Fuck it");
        }

        return null;
    }

    public class ReminderCacheLoader<T> extends CacheLoader<String, T> {

        private final Logger LOGGER = LoggerFactory.getLogger("ReminderCacheLoader.class");

        Class<T> reminderClass;
        ReminderRepository repository;

        public ReminderCacheLoader(ReminderRepository repository, Class<T> reminderClass) {
            this.reminderClass = reminderClass;
            this.repository = repository;
        }

        @Override
        public T load(String s) throws Exception {

            LOGGER.info("Actually Loading Value From Repository");
            return (T) repository.findByName(s);
        }
    }

}
