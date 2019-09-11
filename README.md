[![Build Status](https://travis-ci.org/AlexandrKaleganov/count_words.svg?branch=master)](https://travis-ci.org/AlexandrKaleganov/count_words)
[![codecov](https://codecov.io/gh/AlexandrKaleganov/count_words/branch/master/graph/badge.svg)](https://codecov.io/gh/AlexandrKaleganov/count_words)

# count_words
Приложение будет собирать статистику по количеству слов в бд
1. авторизуемся в приложении
2. Создаём бд указываем имя, ip, пароль, 
3. Получаем список всех приложений, приложения подтянуться из таблцы App из схемы app_statistic  с указанной бд (2 пункт)
4. Собираем статистику: выбираем бд, выбираем приложение по которым будет собрана статистика, указываем наименование статистики
(это также будет называться и таблица, в которую соберётся статистика) 