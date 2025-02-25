<p align = "center">
  <img src = "https://i.imgur.com/iHgSlXk.png">
</p>

Karus Labs' mono-repository for spigot libraries & tools. 
* Allows Brigadier command framework usage in Spigot plugins
* Code-generation driven command framework 
* Compile-time plugin.yml generation tools

**Please view the [stable branch](https://github.com/Pante/Chimera/tree/stable) for a production version. Requires Java 11+. [Why Java 11?](https://github.com/Pante/Chimera/wiki/faq#why-does-the-project-require-java-11-and-above)**

**Read the [wiki](https://github.com/Pante/Chimera/wiki) to get started.**

[![CI/CD](https://github.com/Pante/Chimera/workflows/CI/CD/badge.svg)](https://github.com/Pante/Chimera/actions?query=workflow%3ACI%2FCD)
[![Funding](https://img.shields.io/badge/%F0%9F%A4%8D%20-sponsorship-ff69b4?style=flat-square)](https://github.com/sponsors/Pante)
[![Maintainability](https://api.codeclimate.com/v1/badges/d03deef9f37d3d90636d/maintainability)](https://codeclimate.com/github/Pante/Karus-Commons/maintainability)
[![Codecov](https://codecov.io/gh/Pante/Chimera/branch/master/graph/badge.svg)](https://codecov.io/gh/Pante/Chimera)
[![Stable Source Code](https://img.shields.io/badge/stable-branch-blue.svg)](https://github.com/Pante/Chimera/tree/stable)
[![Discord](https://img.shields.io/discord/140273735772012544.svg?style=flat-square)](https://discord.gg/uE4C9NQ)

<details>
    <summary>
        <b>Version Compatibility</b>
    </summary>

| Chimera Version | Minecraft Version |
|-----------------|-------------------|
| 4.8.0           | 1.16.3            |
| 4.7.1           | 1.16.1            |
| 4.6.1           | 1.15.2            |
| 4.3.0           | 1.14 - 1.14.4     |
| 4.1.0           | 1.13.2            |
</details>

#### Maven Repository
```XML
<repository>
  <id>chimera-releases</id>
  <url>https://repo.karuslabs.com/repository/chimera-releases/</url>
</repository>
```

***
#### Annotations - Contains general purpose annotations
[![releases-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-releases/com/karuslabs/chimera/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-releases/com/karuslabs/annotations/)
[![snapshots-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-snapshots/com/karuslabs/chimera/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-snapshots/com/karuslabs/annotations/)
[![javadoc](https://img.shields.io/badge/javadoc-4.8.0-brightgreen.svg)](https://repo.karuslabs.com/repository/chimera/4.8.0/annotations/apidocs/index.html)
```XML
<dependency>
    <groupId>com.karuslabs</groupId>
    <artifactId>annotations</artifactId>
    <version>4.8.0</version>
</dependency>
```

***
#### Commons - Contains the command framework and other common utilities for Spigot plugin development
[![releases-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-releases/com/karuslabs/chimera/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-releases/com/karuslabs/commons)
[![snapshots-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-snapshots/com/karuslabs/chimera/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-snapshots/com/karuslabs/commons)
[![javadoc](https://img.shields.io/badge/javadoc-4.8.0-brightgreen.svg)](https://repo.karuslabs.com/repository/chimera/4.8.0/commons/apidocs/index.html)
```XML
<dependency>
    <groupId>com.karuslabs</groupId>
    <artifactId>commons</artifactId>
    <version>4.8.0</version>
</dependency>
```

***
#### Scribe Annotations - Contains annotations that other scribe projects use to generate a plugin.yml
[![releases-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-releases/com/karuslabs/scribe-annotations/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-releases/com/karuslabs/scribe-annotations)
[![snapshots-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-snapshots/com/karuslabs/scribe-annotations/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-snapshots/com/karuslabs/scribe-annotations)
[![javadoc](https://img.shields.io/badge/javadoc-4.8.0-brightgreen.svg)](https://repo.karuslabs.com/repository/chimera/4.8.0/scribe/scribe-annotations/apidocs/index.html)

```XML
<dependency>
    <groupId>com.karuslabs</groupId>
    <artifactId>scribe-annotations</artifactId>
    <version>4.8.0</version>
</dependency>
```

***
#### Scribe Standalone - Standalone annotation processor that generates a plugin.yml
[![releases-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-releases/com/karuslabs/scribe-standalone/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-releases/com/karuslabs/scribe-standalone)
[![snapshots-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-snapshots/com/karuslabs/scribe-standalone/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-snapshots/com/karuslabs/scribe-standalone)
[![javadoc](https://img.shields.io/badge/javadoc-4.8.0-brightgreen.svg)](https://repo.karuslabs.com/repository/chimera/4.8.0/scribe/scribe-standalone/apidocs/index.html)
```XML
<!-- Standalone annotation processor, provides scribe-annotations as a transitive dependency -->
<dependency>
    <groupId>com.karuslabs</groupId>
    <artifactId>scribe-standalone</artifactId>
    <version>4.8.0</version>
</dependency>
```

***
#### Scribe Maven Plugin - Maven plugin that generates a plugin.yml and infers values from a project's pom.xml
[![releases-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-releases/com/karuslabs/scribe-maven-plugin/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-releases/com/karuslabs/scribe-maven-plugin)
[![snapshots-maven](https://img.shields.io/maven-metadata/v/https/repo.karuslabs.com/repository/chimera-snapshots/com/karuslabs/scribe-maven-plugin/maven-metadata.xml.svg)](https://repo.karuslabs.com/service/rest/repository/browse/chimera-snapshots/com/karuslabs/scribe-maven-plugin)
[![javadoc](https://img.shields.io/badge/javadoc-4.8.0-brightgreen.svg)](https://repo.karuslabs.com/repository/chimera/4.8.0/scribe/scribe-maven-plugin/apidocs/index.html)
```XML
<dependency>
  <groupId>com.karuslabs</groupId>
  <artifactId>scribe-annotations</artifactId>
  <version>4.8.0</version>
</dependency>

<plugin>
  <groupId>com.karuslabs</groupId>
  <artifactId>scribe-maven-plugin</artifactId>
  <version>4.8.0</version>
  <executions>
    <execution>
      <goals>
          <goal>scribe</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```
