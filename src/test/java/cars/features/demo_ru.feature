Feature: Меню Users

  @telebreeze
  Scenario: Добавление нового пользователя
    Given Пользователь переходит на главную страницу
    When Пользователь @user.users.user1 заходит в панель оператора
    Then Страница панель оператора отображена
    When Открываем меню 'Subscribers'
      And Нажимаем на выпадающее меню панели 'Subscribers' и выбираем 'Add Customer'
      And Заполняем информацию типе и статусе пользователя 'subscriber1'
        |Type           |Subscriber                                     |
        |Status         |Active                                         |
      And Заполняем контактную информацию о пользователе 'subscriber1'
        |First Name     |Autotest                                       |
        |Last Name      |Test_@func.getRandomNumber('8')                |
        |E-mail         |testmail@func.getRandomNumber('8')@yandex.com  |
        |Country        |USA                                            |
        |State          |Alaska                                         |
        |State Contraction|AK                                           |
        |City           |Akutan                                         |
        |Address        |Test str, 1                                    |
        |Zip Code       |99553                                          |
        |Phone          |+1 907-999-9999                                |
      And Заполняем информацию аккаунте пользователя 'subscriber1'
        |Username       |user@func.getRandomNumber('8')                 |
        |Password       |password                                       |
        |Auth Method    |All                                            |
        |Max Concurrent Connections |0                                  |
        |Balance        |10.00                                          |
      And Заполняем информацию  Dealer Settings пользователя 'subscriber1'
        |Dealer         |Raj Garg                                       |
      And Заполняем Notes and Tag пользователя 'subscriber1'
        |Notes          |Autotest's note                                |
        |Tag            |Autotest                                       |
      And Нажимаем кнопку 'Create User' панели 'Submit'
    Then Произошел редирект на страницу пользователя 'subscriber1'
    When Открываем меню 'Subscribers'
      And Выполняем поиск пользователя 'subscriber1' среди 'Active Users' пользователей
      And Открываем карточку пользователя 'subscriber1'
    Then Данные пользователя 'subscriber1' корректны



