package com.feckleface.massive.GuavaCacheDemo.repository;

import com.feckleface.massive.GuavaCacheDemo.model.Reminder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends MongoRepository<Reminder, String> {

    Reminder findByName(String title);

}
