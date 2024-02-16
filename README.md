# Bukkit Library

Shared code for my Minecraft Bukkit plugins. Because I hate duplicating code over and over again.

## How to use

Add my Maven repository to your pom.xml:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/Programie/Common</url>
    </repository>
</repositories>
```

Add the library as a dependency:

```xml
<dependencies>
    <dependency>
        <groupId>com.selfcoders</groupId>
        <artifactId>bukkit-library</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```