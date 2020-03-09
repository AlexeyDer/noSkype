[![Build Status](https://travis-ci.com/AlexeyDer/noSkype.svg?branch=release)](https://travis-ci.com/AlexeyDer/noSkype)
# noSkype
![Image](https://hsto.org/webt/5b/22/62/5b2262cea66f9381421890.png)
![Image](https://cdn.iconscout.com/icon/free/png-256/gradle-3-1175026.png)
![Image](https://www.matt-thornton.net/wordpress/wp-content/uploads/0dd7193f-e747-4a15-b797-818b9fac3656-mysql.png)
![Image](https://d1q6f0aelx0por.cloudfront.net/product-logos/644d2f15-c5db-4731-a353-ace6235841fa-registry.png)

Перед Вами web-приложение - чат с возможностью видеозвонка, созданный с помощью технологии **webRTC**.

# Распоковка приложения
- скачать deploy.zip из раздела релизов
- разархивировать
- установить docker.io (sudo apt install docker.io)
- запустить docker.sh


- остановить докер "docker stop spring-rest-service-0.1.0"
- удалить докеры
- chmod +x dockerRm.sh
- sh dockerRm.sh

# Если через  команду "git clone"
Перед запуском
- gradle build && gradle bootJar (gradle версии 4.10 и выше)
- chmod +x run.sh
- sh run.sh

Безопасный запуск приложения из docker-а:
- chmod +x docker.sh
- sh docker.sh

Удалить все образы можно с помощью:
- chmod +x dockerRm.sh
- sh dockerRm.sh
