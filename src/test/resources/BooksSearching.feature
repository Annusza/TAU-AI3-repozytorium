
Feature: Select a book by author's Surname


  #Scenario Outline: Find author's surname by regex
    #Given I have author's surname <authorSurname>
    #When I ask for a regex <regex>
    #Then I get result <result>
#
  #Examples:
    #| authorSurname  | regex  | result |
    #| Prus       		 | P.*s   | Prus   |
    #| Prus         	 | P...   | Prus   |
    #| Głowacki			 | P...   | brak   |
    
    Scenario Outline: Find books with given author's surname
    
   Given the list of books
   #|name      |surname      |title  |id|yearOfPublication|
   |Bolesław  |Prus         |Lalka  |1 |1990             |
   |Bolesław  |Prus         |Dzieci |2 |1980             |
   |Aleksander|Głowacki     |Lalka  |3 |1990             |
   
   When we find books starting with surname <regex>
   Then we have a result list <title>
   
   Examples:
   |regex      |title   |
   |Prus     |Lalka |
   |Prus     |Dzieci|
   #|Głowacki |brak   |
   
