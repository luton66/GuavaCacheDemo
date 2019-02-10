package com.feckleface.massive.GuavaCacheDemo.controller;

import com.feckleface.massive.GuavaCacheDemo.cache.ReminderCacheUtil;
import com.feckleface.massive.GuavaCacheDemo.model.Reminder;
import com.feckleface.massive.GuavaCacheDemo.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ReminderController {

    private ReminderRepository repository;
    private ReminderCacheUtil cache;

    @Autowired
    public ReminderController(final ReminderRepository repository, final ReminderCacheUtil cache) {
        this.cache = cache;
        this.repository = repository;
    }

    @GetMapping("/test")
    public Reminder test() {
        return cache.retrieveValue("Buy Wood");
        //        return repository.findByName("Buy Wood");
    }
}
