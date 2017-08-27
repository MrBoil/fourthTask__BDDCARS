Feature: Сайт Cars

  Background:
    Производим настройку браузера

  Scenario Outline:
    Поиск двух случайных моделей машин на сайте cars.com и запись
    их характеристик

    Given Открываем главную страницу "parameter"
    When Переходим в категорию "parameter", для поиска машины
    Then Категория "parameter" упешно открыта
    When Вводим случайные данные модели, марки и года выпуска машины и сохраняем их в <файл>
    Then Значения успешно введены и открылась страница с описанием машины
    When Переходим в меню во вкладку "parameter" (если вкладка отсуствует,повторить предыдущий пункт с записью в <файл>)
    Then Открылась страница с характеристиками выбранной модификации
    When Записываем характеристики: Engine, Transmission в файл <файл>
    Then Характеристики успешно записаны
    Examples:
      | файл      |
      | firstCar  |
      | secondCar |

  Scenario:
    Сравнение двух ранее выбранных машин

    Given Открываем главную страницу "parameter"
    When Через меню "category" переходим в подкатегорию "subcategory"
    Then Открылась страница "parameter" для поиска предложений по покупке
    When В разделе Side-by-Side Comparisons переходим по кнопке 'Compare cars'
    Then Открылась страница для выбора авто для сравнения
    When Выбираем первую модель, отобранную в предыдущем сценарии из "file" и переходим по "button"
    And Выбираем вторую модель, из "file" и переходим по копке "button"
    Then Модели успешно выбраны для сравнения
    When Проверяем страницу сравнения 2-ух моделей на соотвествие характеристикам из "file1" и "file2"
    Then Характеристики авто на странице соответствуют выбранным в предыдущем сценарии