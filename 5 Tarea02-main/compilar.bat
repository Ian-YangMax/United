git clone https://github.com/Ian-YangMax/Tarea02.git && cd ./Tarea02 && cd ./app-books && gradlew jar && gradlew copyLibs && docker build -t jaimesalvador/app-books:1.0.0 . && cd .. && cd ./app-authors && gradlew quarkusBuild && docker build -t jaimesalvador/app-authors:1.0.0 . && cd .. && docker compose up

