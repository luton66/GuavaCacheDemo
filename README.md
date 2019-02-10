GuavaCacheDemo

This is a very basic Google GuavaDemo up and running. 

There is a basic RestController which calls a single method returning a very specific 
Reminder object. 

The idea is that the Reminder is held in cache for 10 seconds only, and a log is 
produced every time that the object is called from the Repo.

The result is that even if the RestController is hit repeatedly in quick succession
the item will only be called once every 10 seconds at most.