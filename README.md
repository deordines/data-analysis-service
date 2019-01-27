
# Data Analysis

> A service to file processing.

This service monitors a folder every 3 seconds, pulls the available files and processes them.
At the end of each file processed, a report file are generated.


## Prerequisites

* JVM

## Build

```bash
./gradlew clean build
```

## Test

```bash
./gradlew clean test
```

## Coverage

```bash
./gradlew jacocoTestReport
```
Reports will be on the follow path:

```bash
${buildDir}/jacoco/index.html
```

## Run

With the project built, you must package them with the command:

```bash
./gradlew clean bootJar
```
After, go to the path:

```bash
${buildDir}/libs/
```
And execute the following command line in console:

```bash
java -jar ${jarName}.jar
```

## Properties

```property
monitor.fixed-rate = 3000 (ms)
path.in = '${user.home}/data/in'
path.out = '${user.home}/data/out'
path.processed = '${user.home}/data/processed'
path.error = '${user.home}/data/error'
format-type.extensions = ['dat']
parser.character.default = 'ç'
parser.character.items = ','
parser.character.item = '-'
```
To override any property, simply add the parameters that you want into final of command line.
> To lists, separate values with comma.

```bash
java -jar ${jarName}.jar --monitor.fixed-rate=${value} --format-type.extensions=${value1},${value2}
```

## How to use

When program running, put this file into folder "${HOMEPATH}/data/in".
> If this folder not exists, the programm will create it.

After analysis, check folder "${HOMEPATH}/data/out" to see report.

Filename:
```text
${filename}.dat
```

Content:
```text
001ç12345678901çSalesman 1ç1000
001ç12345678902çSalesman 2ç10000.99
002ç12345678000101çClient 1çBusiness Area 1
002ç12345678000102çClient 2çBusiness Area 2
003ç1ç[1-10-10.10]çSalesman 1
003ç2ç[1-10-10.10,2-20-20.99]çSalesman 2
003ç3ç[1-10-10.10,2-20-20.99,3-30-0.30]çSalesman 1
```

## License
[MIT](https://choosealicense.com/licenses/mit/)