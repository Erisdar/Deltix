# Deltix

[![Build Status](https://travis-ci.org/Erisdar/Deltix.svg?branch=master)](https://travis-ci.org/Erisdar/Deltix)

The program is written using functional and reactive paradigms. This system has two main services (MessengerService and 
MessageGeneratorService) and one UnicastProcessor. The MessageGeneratorService generates random messages and publishes them in 
the UnicastProcessor. The MessengerService subscribes to the UnicastProcessor and receives elements from it (limited by properties: 
MESSENGER_CAPACITY_PER_TIMEOUT and MESSENGER_TIMEOUT). The system also starts an additional thread, which clears the messenger queue 
once a time specified by the property: MESSENGER_TIMEOUT.

### Prerequisites

Check supported OS:

```
Windows 10 64-bit
```
## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Ivan Bialotski** - *Full development* - [Erisdar](https://github.com/Erisdar)

See also the list of [contributors](https://github.com/Erisdar/Internet-provider/graphs/contributors) who participated in this project.
