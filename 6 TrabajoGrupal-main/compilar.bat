git clone https://github.com/Ian-YangMax/TrabajoGrupal.git && cd ./TrabajoGrupal && cd ./app-book && gradlew libertyPackage && docker build -t jaimesalvador/app-books:1.0.0 . && cd .. && cd ./app-author && docker build -t jaimesalvador/app-authors:1.0.0 . && cd .. && docker push jaimesalvador/app-books:1.0.0 && docker push jaimesalvador/app-authors:1.0.0 && docker compose up

