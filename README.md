GuavaCacheDemo

This is a very basic Google GuavaDemo up and running. 

There is a basic RestController which calls a single method returning a very specific 
Reminder object. 

The idea is that the Reminder is held in cache for 10 seconds only, and a log is 
produced every time that the object is called from the Repo.

The result is that even if the RestController is hit repeatedly in quick succession
the item will only be called once every 10 seconds at most.




TEMPORARY ENTRY

Have a page on intranets (all of them)
Documentation instructing new users how to onboard with services.
Accompanied by a list of "supported users"
Warnings of what will happen if users do not follow this protocol. 
Warnings that support cannot be offered to consumers who have not followed this process.


Support Options

	What are the channels of support that need to be followed for this. 
	What are the first points of contact. 
	How can we offer the best support. 
	Need to be able to offer an initial page that users can refer to

User Support

	Chat room / Incidents raised. 

Ultimately we will need some sort of buffer between us and consumers, otherwise we will be the 
direct point of contact evertime. 

Project Release & Regular Email. 
	- "You are currently shown as the point of contact for project ... "
	  "If this is incorrect please reply to .... "

	  Release email
	  - "You are shown as the point of contact for the following project ... "
	  	"This to inform you, of the following changes to ... "

	  	"Please inform us immediately if this is not suitable for reason x,y,z"

	  This is followed one week later by another email