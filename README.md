#Unikorn
[![Build Status](https://travis-ci.org/statement2020/unikorn.svg?branch=master)](https://travis-ci.org/statement2020/unikorn)

**Make testing your SQL integrations easy.**

Testing that your data has arrived in a database can be tedious,
time consuming, and unwieldy in code. Many applications use Spring Data to
manage their database connections, and often it's prudent to use a different
technology to verify the data. 

You may find your code is littered with `Connection` and `PreparedStatement` and this 
comes with its own set of challenges in terms of management.

This library aims to provide a way of providing a unified query and test mechanism
inspired by [Rest Assured](https://github.com/rest-assured/rest-assured)

## How To Use

Set up your database configuration (you'll need your driver on the classpath):
```java
final UnikornConfiguration config = new UnikornConfiguration("jdbc://url/",
                                                             "com.my.db.Driver",
                                                             "user",
                                                             "password");
final Unikorn unikorn = new Unikorn(config);
```

And query & test away:

```java
final Map<String, Object> expectedRow = new HashMap<>();
expectedRow.put("name", "Test User");
expectedRow.put("phone", 07111 111122);
unikorn.executeQuery("select * from user")
       .hasSize(1)
       .containsExactlyRow(expectedRow);
```

