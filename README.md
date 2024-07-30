# Smallest number

## About
Finding the smallest number that can be divided by a sequential set of numbers.

The solution of the problem is done using mathematical formula.

To see other attempts on solving with other solution, please see `multiplesolutions` branch or https://github.com/chrisnu/smallestnumber/tree/multiplesolutions.

## How to run

```bash
$ mvn clean install
$ mvn spring-boot:run
```

Project runs on port 8080.

To test the api:

- http://localhost:8080/api/smallest?start=1&end=10
- http://localhost:8080/api/smallest?start=1&end=15
- http://localhost:8080/api/smallest?start=1&end=25