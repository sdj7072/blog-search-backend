# blog-search-backend
> Backend API service for Blog search 
   
## 1. 개발 환경
* Java 17
* Gradle 7.6.1
* Spring Boot 3.0.4
* Intellij IDEA Ultimate
* H2 DB
   
## 2. External Library
* Lombok : 제공되는 여러 Java Annotation을 기반으로 Code를 간결화 하여 개발 생산성 제고
* Swagger UI : API Resource 시각화
   
## 3. URLs
* Swagger UI : http://localhost:8080/swagger-ui.html
* H2 Console : http://localhost:8080/h2-console
   
## 4. Excutable JAR
* URL : https://drive.google.com/file/d/1qBshXY41McSAiuTwL3mznUtOXzAijvLf/view?usp=share_link
* shell
```sh
java -jar -Dfile.encoding=UTF-8 search-0.0.1-SNAPSHOT.jar
```
   
## 5. API 명세
### 5.1 블로그 검색
* Method : GET
* URI : /api/vi/search/blog
* Request
   * String platform
      * 검색 Platform을 정의합니다.
      * 기본값 : KAKAO
      * 입력 제한 : KAKAO, NAVER
   * String query
      * 검색어 입니다.
   * String sort
      * 정렬 방식을 정의합니다.
      * 기본값 : ACCURACY
      * 입력 제한 : ACCURACY(정확도순), RECENCY(최신순)
   * Int page
      * 조회 결과 페이지(숫자)를 정의합니다.
      * 기본값 : 1
      * 입력 제한 : 최소 1, 최대 50
   * Int size
      * 페이지당 조회 결과(블로그 검색수)를 정의합니다.
      * 기본값 : 10
      * 입력 제한 : 최소 1, 최대 50
* Response

   
### 5.2 인기 검색어 조회
   
   
## 6. Reference
* KAKAO Developers : <https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog>
* NAVER Developers : <https://developers.naver.com/docs/serviceapi/search/blog/blog.md>
* Spring Boot : <https://spring.io/projects/spring-boot#learn>
* Project Lombok : <https://projectlombok.org>
* Swagger : <https://swagger.io/>
* Baeldung : <https://www.baeldung.com>

* Blog : <https://congsong.tistory.com/26>
* Blog : <https://freehoon.tistory.com/112>
