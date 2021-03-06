## ExStream
A wrapper interface for Stream with exception support.

### Usage
Given some `Stream<T>` named `source`
```java
ExStream<T> exStream = ExStreamBuilder.of(source)
```
Now one can use with throwable functions. 

All terminal operations will throw `ExStreamException` with the cause being the original exception thrown in the exceptional method.
```java
exStream.exFilter(Functions::someExceptionalPredicate)
        .exMap(Functions::someExceptionalMapper)
        .exReduce(Functions::nonExceptionalReducingFunction)
        .exFindFirst()
```

Good old `Collectors` work
````java
List<T> list = exStream.exCollect(Collectors.toList())
````

It's possible to obtain a Stream instance with `exStream.toStream()`. 
This instance can throw `ExStreamRuntimeException` on any terminal operation.

### Maven coordinates

You can use a released version from maven repositories

````xml
<dependency>
    <groupId>io.github.pak3nuh.util</groupId>
    <artifactId>exstream</artifactId>
    <version>LATEST</version>
</dependency>
````