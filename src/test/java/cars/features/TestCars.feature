Feature: Сайт Cars

  Scenario Outline:
    Поиск двух случайных моделей машин на сайте cars.com и запись
    их характеристик

    Given Производим настройку браузера и открываем главную страницу "https://cars.com"
    When Переходим в категорию "Specs & Reviews", для поиска машины
    And Вводим случайные данные модели, марки и года выпуска машины и сохраняем их в <файл>
    Then Если начения успешно введены, то выполним поиск
    Then Успешно открылась страница выбранной модели
    When Переходим в меню во вкладку "parameter", но если вкладка отсуствует,повторить предыдущий пункт с записью в <файл>
    Then Открылась страница с характеристиками выбранной модификации

    Examples:
      | файл      |
      | firstCar  |