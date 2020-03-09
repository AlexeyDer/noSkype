[![Build Status](https://travis-ci.com/AlexeyDer/noSkype.svg?branch=release)](https://travis-ci.com/AlexeyDer/noSkype)
# noSkype
![Image](https://hsto.org/webt/5b/22/62/5b2262cea66f9381421890.png)
![Image](https://cdn.iconscout.com/icon/free/png-256/gradle-3-1175026.png)
![Image](https://www.matt-thornton.net/wordpress/wp-content/uploads/0dd7193f-e747-4a15-b797-818b9fac3656-mysql.png)
![Image](https://d1q6f0aelx0por.cloudfront.net/product-logos/644d2f15-c5db-4731-a353-ace6235841fa-registry.png)

# Перед Вами web-приложение - чат с возможностью видеозвонка, созданный с помощью технологии **webRTC**.

## Перед сборкой и запуском программы необходимо:
* В **application.yml**, который находиться **в папке main** указать путь к вашей базе данных и пароль для входа
* Также необходимо сделать во втором файле **application.yml**, который находиться **в папке test** и указать путь к тестовой базе данных,
чтобы сборка проходила с тестированием
* Настройка **nginx** для установки **https**
```
- chmod +x run.sh
- sh run.sh
```
**ВАЖНО!!** Не забыть при входе на сайт включить протокол **https://**,
а не **http://**, иначе программа не будет передавать изображение
# Сборка программы
С тестированием
```
gradle build
```
Без тестирования
```
gradle build -x test
```
# Создания файла .jar
```
gradle bootJar
```
# Запуск программы
```
gradle bootRun
```
## Безопасный запуск приложения из docker-а:
```
- скачать deploy.zip из раздела релизов
- разархивировать
- установить docker.io (sudo apt install docker.io)
- запустить docker.sh (chmod +x docker.sh && sudo sh docker.sh)

- остановить докер "docker stop spring-rest-service-0.1.0"
- удалить докеры
- chmod +x dockerRm.sh
- sh dockerRm.sh
```
## Удалить все образы можно с помощью:
```
- chmod +x dockerRm.sh
- sh dockerRm.sh
```
### Команда проекта:
* Alexey Derevtsov - Team Leader
* Victoria Gatsulia - Developer
