
@tag
Feature: Delete books from list
  

@tag1
  Scenario Outline:  Delete books with year of publication
    Given list of books
    
      | Lalka 										| 1990 |1|
      | Cham  										| 1991 |2|
      | Annie of Green Gables 		| 2005 |3|
      | Zasypie wszystko, zawieje | 1990 |4|
      | Legendy warszawskie 			| 1985 |5|
    
    When we delete books by list of years <years>
    
   
    
    Then book with title <title> should be deleted 

    Examples:
      | years 		| title							        |
      | 1990,1985 | Lalka                  		|
      | 1990,1985 | Zasypie wszystko, zawieje |
      | 1990,1985 | Legendy warszawskie       |
      | 2005 			| Annie of Green Gables     |
    

    