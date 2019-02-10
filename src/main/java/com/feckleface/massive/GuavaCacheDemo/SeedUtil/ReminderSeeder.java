package com.feckleface.massive.GuavaCacheDemo.SeedUtil;

import com.feckleface.massive.GuavaCacheDemo.model.Reminder;
import com.feckleface.massive.GuavaCacheDemo.repository.ReminderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReminderSeeder implements CommandLineRunner {

    ReminderRepository repository;

    public ReminderSeeder(ReminderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Reminder("Buy Wood", "Need wood for shelving unit in geek room", "Leigh"));
        repository.save(new Reminder("Build Shelves", "Build Shelves to hold all my shit", "Leigh"));
        repository.save(new Reminder("Paint Shelves", "Think the shelves should be nice and black", "Leigh"));
        repository.save(new Reminder("Print out Hotel Receipt", "Need to claim back some shit for work", "Leigh"));
        repository.save(new Reminder("Print out NCP Receipt", "Still need to claim back that car parking from work", "Leigh"));
    }
}
