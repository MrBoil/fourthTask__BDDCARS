Feature: Сайт Cars

  Scenario Outline:
    Search for two random cars models on cars.com website and record
    their characteristics

    Given We configure the browser and open the main page "https://cars.com"
    When We go to the category "Specs & Reviews", to search for a machine
      And Enter random data of the model, brand and year of manufacture of the machine and store them in <file>
    Then If the values ​​are successfully entered, then perform a search
    When We go to the "Trims" tab in the menu, but if the tab does not exist, repeat the previous items with the entry in the <file>
      And Click on the link to the modification selection page
    Then The page of the selected model from <file>
    When we write down the characteristics: Engine, Transmission in <file>
    Then The characteristics are successfully written in <file>
    Examples:
      | file      |
      | firstCar  |
      | secondCar |

  Scenario:
  Comparison of two previously selected cars on cars.com

    Given Open the main page "https://cars.com"
    When Through the menu "Buy" go to the subcategory "Research Car Models"
      And Go to the Side-by-Side Comparisons section of the 'Compare cars'
    When we select the first model selected in the previous scenario from "firstCar" and go to "Start Comparing Now"
      And Choose the second model from "secondCar" and go to the "Done"
    Then We check the comparison page of the two models for the "Engine" and "Transmission" matching from "firstCar" and "secondCar"
    Then Close the browser