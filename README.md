URL shortening service is a service that enables users to capture long urls into very short urls and allows them to easily share these shortened urls with their target audience. Below is the problem with limited scope to demonstrate basic concepts behind implementation and design of such service. 
Requirements: 

1. URL and return a much shorter URL. 2. Take a shortened URL and redirect to the original URL. 3. Collect usage statistics for shortened URLs hits. 
Result expectations: 
  1. API 1: getShortenedURL 
    a. Input: (Long_url, client_id) 
    b. Output: (Short_url) 
    c. In case, getShortenedURL is called with the same Long_url, client_id more than 
      once, the output should have the same result returned each time. However, if client_id differs then even for the same Long_Url the output should have         different result (necessary for correctly capturing analytics).
      
  2. API 2: getOriginalURL 
        a. Input: Short url 
        b. Output: Long url 
        c. Optional: Redirection to original url via http code 307. 
  
  3. API3: getHitCount 
      a. Input: Short url 
      b. Output: Number of times ‘getOriginalURL’ function was called using that 
      particular short url. 
      
  4. JUNIT highlighting these three APIs covering edge cases. (Not Covered)

Assumptions/Clarification: 
      ● API1/API2/API3 can be hit in parallel via different client machines 

Simplications: 
● Assume single machine setup. 
● Data store to be stored in RAM. 
● A simple technique for conversion of long to short url could be storing a url against an incremental id (bigint) and encoding that to base 62 (Using characters: 0-9,a-z,A-Z). This encoded id in base 62 could be simply used as a short url returned in output. An 8 character shortened URL should be sufficient for any practical purposes. 
● Though simply returning raw iid encoded as base 62 has security concerns and preferable way would be to encode this using a hash function, this that can be excluded from current scope given the limited time. 


Build:
 mvn clean install -DskipTests
 java -jar target/url-api-0.0.1-SNAPSHOT.jar


Postman Api Collection lonk:
 https://www.getpostman.com/collections/95212fe554291109a7db


Load and Concurrency test using Apachebench 

ab -H 'clientId: gopal.nitncse49@gmail.com' -c 1 -n 1 http://localhost:8090/url/api/v0/getShortenedURL?url=https://www.google.com/search?q=java+api+check+header&oq=java+api+check+header+&aqs=chrome..69i57j0j69i64.17845j0j7&sourceid=chrome&ie=UTF-8

ab -H 'clientId: gopal.nitncse49@gmail.com' -c 4 -n 20 http://localhost:8090/url/api/v0/getShortenedURL?url=https://www.google.com/search?q=java+api+check+header&oq=java+api+check+header+&aqs=chrome..69i57j0j69i64.17845j0j7&sourceid=chrome&ie=UTF-81


References:
https://mkyong.com/java/java-md5-hashing-example/
https://www.geeksforgeeks.org/
