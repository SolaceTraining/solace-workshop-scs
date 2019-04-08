author: Marc DiPasquale
summary: Using Spring Cloud Streams w/ Solace PubSub+
id: solace-codelab-scs-1
categories: spring,scs,cloud-streams,solace,pubsub+,java,pcf,openshift
environments: Web
status: draft
feedback link: github.com/Mrc0113/solace-workshop-scs
analytics account: 0

# Developer Workshop: Using Spring Cloud Streams with Solace PubSub+

## CodeLab Overview
Duration: 0:10

Using Java & Spring Cloud Streams to create Event-Driven Applications
* The purpose of this codelab is to introduce java developers to creating event-driven applications with Spring Cloud Streams
* “Spring Cloud Stream is a framework for building highly scalable event-driven microservices connected with shared messaging systems."
* It is based on Spring Boot, Spring Cloud, Spring Integration and Spring Messaging

You're a Microservices Developer that works for an up and coming car company named Edison Automotives. Your boss is not the most adept in the use of social media but he's been hearing great things about twitter from his inner-circle and is a bit infatuated with tying it into Edison Automotive's every day business and culture....little does he know that his company does not exactly have the best products or reputation....

<INSERT INTRO STORY> 

Positive
: **Developer Resources** 
Spring Cloud Stream Project Home: [https://spring.io/projects/spring-cloud-stream](https://spring.io/projects/spring-cloud-stream) 
The latest GA version of Spring Clouds Streams as of the creation of this lab is Fishtown SR2. 
The Reference Guide for that version is available [here](https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.1.2.RELEASE/single/spring-cloud-stream.html).


## Set-up & Prerequisites
Duration: 0:10

<INSERT INFO FROM DOC THAT HEINZ CREATED>

## Section 1 - Deploy Your First Source & Sink
Duration: 0:20

* The marketing department wants to use the tweets to learn more about our customer’s thoughts.  Since you’re already getting them can you share them with marketing?
* That’s Possible!  Tell them to give me a call!


Positive
: SCS Provides 3 Binding Interfaces:
1 Sink - "Identifies the contract for the message consumer by providing the destination from which the message is consumed."
2 Source - "Identifies the contract for the message producer by providing the destination to which the produced message is sent."
3 Processor - "Encapsulates both the sink and the source contracts by exposing two destinations that allow consumption and production of messages."

<INSERT TECHNICAL STEPS>
Create Source [ Instructor ]
Creates Marketing Analytics Sink [Instructor]

Positive
: Notice that you didn't need to use any messaging APIs! This abstraction makes it possible for developers to concentrate on their business logic rather than learning proprietary messaging APIs and also allows for dynamically configuring one or more binders at runtime. 


## Section 2 - Discover the ease of 1-to-Many with Publish-Subscribe
Duration: 0:05

* Hey Tweet Master, I’m loving this twitter thing my buddy told me about!  I want our LED ribbon around the factory floor to become a “Tweet Board” and show all the tweets about our awesome vehicles. The factory team members are going to love it!
* That’s Possible!  I’ll get right on it – give me an hour.

<INSERT TECHNICAL STEPS>
Create Factory Tweet Board [ Attendees w/ instructor ]

Positive
: Notice that the publisher (Source) application did not need to be modified in order for another consumer (Sink) application to receive the stream of tweets. There are two takeaways here: 
1 The publish-subscribe paradigm allows for the publisher to send data once and not care whether 0,1,2 or 100 applications are subscribed on the other end. It just send the data and moves on. 
2 Developing event driven applications allows for decoupling of your sending and receiving applications. This powerful concept allowed our company to add new functionality without touching our already operational applications. 

## Section 3 - Creating your first Processor
Duration: 0:15

* Hey Tweet Master, I’ve got a problem with your work!  This twitter board is letting employees take credit for all the customer’s ideas.  I want to send the new feature tweets to my private page instead of the “Tweet Board.” Can you fix it?
* That’s Possible! I’ll do it right away – should be ready in 30 minutes.

<INSERT TECHNICAL STEPS>
Create Boss Sink [ Instructor]
Create Features Processor [Instructor]

Positive
: Notice that you created a custom processor binding to support multiple outputs since the default Processor binding only has one input and one output. 

## Section 4 - Painless Multi-protocol with MQTT
Duration: 0:10

* Good job “Tweet Master!”  Now everyone is looking at me like the genius I am.  Look at all those amazing tweets coming through.  Unfortunately only the people in the factory can see them.  Can you create a webpage so people in the offices can see them too? 

<INSERT TECHNICAL STEPS>
Creates MQTT Web App [ Instructor]

Positive
: Shows multi-protocol (Solace made that easy)

## Section 5 - Chain Multiple Processors Together
Duration: 0:10

* Hey Tweet Master – we’re still receiving a bunch of complaints and negative tweets…I’m looking like an idiot here.  Fix it now!  And while you’re at it create some upbeat positive tweets!  I don’t want people seeing our cars break down or catch on fire and explode! 

<INSERT TECHNICAL STEPS>
Attendees [along with instructor] create “Negative to Positive” processor
Students get a list of words that need to be changed..
("on fire”, "broken down”, “crashed”, “explosion”, “sucks”)


Positive
: Notice that multiple processors can easily be connected together in order to form a processing chain. 

## Section 6 - Review & Continue Learning!
Duration: 0:05

* Looks good “Tweet Master!” Now I can watch the tweets form my corner office! Take the rest of your day off and go get yourself a drink! 

<INSERT TECHNICAL STEPS>

## Markdown Syntax Backup
Duration: 0:01

``` Java
public static void main(String args[]){
  System.out.println("Hello World!");
  }
```

Positive
: This will appear in a positive info box.

Negative
: This will appear in a negative info box.

 [Download SDK](https://www.google.com)

Adding an image
![image_caption](https://s3-eu-west-1.amazonaws.com/released-artifacts-3.x/assets/tutorial_images/creating-styles/step1.png)

