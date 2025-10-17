# Interview question for Software Developers in Test

Welcome to the Mediafly Software Developer in Test interview. This repository is going to test your knowledge of code
review and automated testing practices.

Please fork this repository (only fork main) and pull it down locally. Do not commit code to the original repository.

In the first part of the interview, you will review the existing tests. You may use any tools you like such as
code coverage reports to determine their effectiveness. You can make changes to existing tests if you find an issue.
Expect this part of the interview to take about 20 minutes.

In the second part of the interview, you can go ahead and write your own tests to improve the coverage of the tests that
already exist. You can build on existing frameworks or add your own. Expect this part of the interview to take about
40 minutes.

Correcting or introducing new tests may uncover bugs in the application! Don't worry about fixing them, just point them
out to the interviewer.

## Application

The purpose of this application is to provide API endpoints for an address entry form. The application consists of a 
number of CRUD endpoints to manage countries and cities.

### Countries

The application allows the user of the API to add and retrieve countries. An endpoint is also provided to search the 
list of countries for countries that start with the specified substring. The following limits apply:
- A country name can only be up to 100 characters long
- The search endpoint will not work for inputs of less than 2 characters

### Cities

The application allows the user of the API to add and retrieve cities. An endpoint is also provided to search the
list of cities for cities that start with the specified substring. For any endpoint that retrieves cities, a country
can optionally be specified. In this case, only cities from that country are returned.  The following limits apply:
- A city name can only be up to 100 characters long
- A city must belong to a valid country


