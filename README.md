# Spring Cloud Streams Workshop with PubSub+

This project contains both the codelab and code for a Spring Cloud Streams Workshop with PubSub+.
The workshop can be self-guided or instructor led. 
* For self guided you can get started by using the CodeLab located here: <TODO: Enter URL> 
* For instructor led you can contact solace at info@solace.com 

Note that there are multiple branches in this project. The "-solution" branches include the completed code, whereas the other branches are defined below:  
* pcf - This branch is meant to be used if you're using the PubSub+ Tile with Pivotal Cloud Foundry (PCF)
* openshift - This branch is meant to be used if you're running PubSub+ inside of OpenShift
* master - This branch is meant to be used if you're using any other PubSub+ deployment (Solace Cloud, a local docker instance, a Solace appliances, etc)

## Exploring the Workshop
More information is in the CodeLab linked above, but if you just want to mess around on your own the projects are Spring Boot Maven projects and can be run individually. Note that you'll need a PubSub+ Service to connect to - check out the **Resources** section to get one setup! 

```bash
mvn spring-boot:run
```

## License

This project is licensed under the Apache License, Version 2.0. - See the [LICENSE](LICENSE) file for details.

## Resources

- Get started for FREE with [Solace Cloud](https://console.solace.cloud)
- Get started locally (also FREE!) with Docker: [Get Started with Software](https://solace.com/software/getting-started/)
- The Solace Developer Portal website at: https://solace.com/developers
- Check out the [Solace blog](https://solace.com/blog) for interesting discussions around Solace technology
- Contact our Developer Advocates on Twitter [@SolaceDevs](https://twitter.com/SolaceDevs)
