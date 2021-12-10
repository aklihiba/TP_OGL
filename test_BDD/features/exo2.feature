Feature: Naviger sur Ebay
  Scenario: ajouter un livre au panier
    Given I open opera
    When i visit "https://www.ebay.com"
    And I select the category Books and search for "Python in easy steps"
    And I submit
    And I select the Book "Python in easy steps by Mike McGrath Book The Fast Free Shipping"
    And i set the quantity to 1
    And i add it
    Then i get the page"https://cart.payments.ebay.com/"
    And I get the price "US $12.16"
    And I have 1 item in my basket