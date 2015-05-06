# Spring-Spark

This project couples spring and spark. Dependency injection in spark using spring is not possible without any further effort, because of the heavy usage of serialization. Therefore the Kryo Serializer is modified so it also injects dependencies, that were not serialized. The good thing about that is: 

 * The Kryo Serializer itself is not changed. Therefore this library is independent of the Spark-Kryo Serializer version (Except if they will start strange refactoring work)
 * You can use any Spring and Spark Version. The project is using the latest ones (Spring 4.1.6.RELEASE and Spark 1.3.1), but you can override these settings with your own pom.xml
 * Dependency injection in Spark using Spring. Yeah!

You have to add one bean to the project that starts the whole spring application.

The XML way:
```xml
<bean class="de.paraplu.springspark.util.SpringBuilder">
```

The Java Config way:
```java

@Configuration
@ComponentScan(basePackages = {"your.packages", "de.paraplu.springspark.util"})
public class AppConfiguration {
...
}
```

The SpringBuilder class uses a @PostConstruct annotation to create a static AutowireCapableBeanFactory. Therefore it has to be added as bean. This static AutowireCapableBeanFactory is then used in the deserialization part to autowire all fields with spring.


## Maven Repository

If there is enough interest I'll put this project into a maven repository. I've never done that, so it'll be a little bit work for me.