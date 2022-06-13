# netty-raknet
High performance RakNet implementation
targeting unreliable and rate-limited client connections. It provides strict netty 
style server and client channels. 

This implementation uses [Netty](https://github.com/netty/netty) 
channels to provide a fast and effective [RakNet](http://www.raknet.net) server, 
offering the full feature set of the transport protocol, while providing
room for extension with any plugins or custom behavior. 

## Features
* Recylable objects:
  * Heavily used objects are recycled.
  * Reduces GC pressure.
  * Instrumented with Netty leak detection.
* Strict Netty patterns:
  * Uses Bootstrap and ServerBootstrap pattern.
  * Signals backpressure using Channel writability. 
  * Uses Netty ChannelOptions for channel config.
  * Follows the normal *bind* and *connect* patterns.
  * Accurate promise responses for *write*, *connect* and others. 
* 0-copy buffer interactions:
  * Retained buffer references throughout.
  * Composite buffers used for encapsulation and defragmentation. 
* Easy-to-use data streaming interface:
  * Configurable packet ID used for raw ByteBuf writing and reading.
  * Extensible to allow for multiple packet ID and channel configurations.
  * True to Netty form, the pipeline can be modified and augmented as needed.
* Advanced flow control
  * Back pressure signals useful for buffer limiting when client is overloaded. 
  * Pending frame-set limits reduce unnecessary resends during high transfer rates.
  * Resend priority based on frame sequence so you get older packets faster.
* Automated flush driver
  * Recommended to write to pipeline with no flush. 
  * Flush cycles condense outbound data for best use of MTU.
  
# Usage

## Maven
```xml
    <dependencies>
        <dependency>
            <groupId>network.ycc</groupId>
            <artifactId>raknet-server</artifactId>
            <version>0.8-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>network.ycc</groupId>
            <artifactId>raknet-client</artifactId>
            <version>0.8-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>codemc-repo</id>
            <url>https://repo.codemc.org/repository/maven-public</url>
        </repository>
    </repositories>
```

### Example

A good example can be seen in the simple 
[Hello World](https://github.com/yesdog/netty-raknet/blob/master/tests/src/test/java/network/ycc/raknet/HelloWorld.java) 
test case.

# [License](./LICENSE)
