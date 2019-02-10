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

    /**
     * In this case a CacheBuilder is used to actual build the reminderCache.
     * Now, when the CacheBuilder is used, it's mostly self explanatory Except for the two arguments I passed in.
     * The argument passed in to the build command, is the name of the ReminderCacheLoader (defined as an inner
     * class below) which has two arguments. The repository (the name of where the items are loaded from - presumably
     * a rest endpoint or URL endpoint is acceptable, and the class that is being cached.
     *
     * @param repository the respository
     */
    @Autowired
    public ReminderCacheUtil(final ReminderRepository repository) {
        this.repository = repository;

        reminderCache = CacheBuilder.newBuilder()
                                    .expireAfterWrite(10, TimeUnit.SECONDS)
                                    .build(new ReminderCacheLoader<>(repository, Reminder.class));

    }

    /**
     * The retrieveValue method simply refers to where the values are being called from.
     *
     * @param reminderName a reminderName
     * @return a reminder
     */
    public Reminder retrieveValue(String reminderName) {
        try {
            LOGGER.info(reminderCache.stats().toString());
            return reminderCache.get(reminderName);
        } catch (ExecutionException e) {
            System.out.println("Fuck it");
        }

        return null;
    }

    /**
     * The ReminderCacheLaoder class extends CacheLoader, and needs two things. The Key value, and the Value in this
     * case a class of reminderClass.
     *
     * Note the String here is not the requested value from the above class.
     *
     * @param <T> in this case T is Reminder.class
     */
    public class ReminderCacheLoader<T> extends CacheLoader<String, T> /* Refering to the key and value */{

        private final Logger LOGGER = LoggerFactory.getLogger("ReminderCacheLoader.class");

        Class<T> reminderClass;
        ReminderRepository repository;

        public ReminderCacheLoader(ReminderRepository repository, Class<T> reminderClass) {
            this.reminderClass = reminderClass;
            this.repository = repository;
        }

        /*
        This is where the String value being requested actually goes.
         */
        @Override
        public T load(String s) throws Exception {

            LOGGER.info("Actually Loading Value From Repository");
            return (T) repository.findByName(s);
        }
    }

}
