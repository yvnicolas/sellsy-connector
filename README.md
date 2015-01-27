# sellsy-connector
Java client for Sellsy Api Connection (see api.sellsy.com)

## How to use it

Clone this repository

Install the jar on your local maven

```
mvn clean install
```

In your target project pom.wml, include the following dependency :

```
	<dependency>
			<groupId>com.sellsy</groupId>
			<artifactId>sellsy-api-java-client</artifactId>
			<version>1.1.0-RELEASE</version>
		</dependency>
```
		
NB : test files include selssy connexion keys that you should update to refer to your own proper keys.
