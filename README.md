# Paperboy for iOS #

Simple RSS reader for iOS written in Scala. 

### How do I get set up? ###

* Install sbt: `brew install sbt`
* Run sbt: `sbt`
* To run the app on simulator run: `iphoneSim` inside of sbt prompt
* Continuous app redeploy: `~iphoneSim`
* To see complete list of tasks run `tasks`, or see [sbt-robovm project](https://github.com/roboscala/sbt-robovm)

### TODO ###

* Setup slf4j-based logger, add NSLog appender to it.
* More screens: feeds list, ability to read full article
* Database cache: ability to read feeds in offline
