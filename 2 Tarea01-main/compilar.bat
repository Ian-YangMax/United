git clone https://github.com/Ian-YangMax/Trabajo01.git && cd ./Trabajo01 && cd ./app-authors && gradlew build && docker build -t ianyangmax/app-authors:1.0 . && cd .. && cd ./app-books && gradlew jar && gradlew copyLibs && docker build -t ianyangmax/app-books:1.0 . && cd .. && cd ./app-web && gradlew jar && gradlew copyLibs && docker build -t ianyangmax/app-web:1.0 . && docker push ianyangmax/app-web:1.0 && docker push ianyangmax/app-books:1.0 && docker push ianyangmax/app-authors:1.0 && cd .. && docker compose up


