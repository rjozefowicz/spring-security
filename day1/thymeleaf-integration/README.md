### Spring Security Thymeleaf integration

Dokumentacja https://github.com/thymeleaf/thymeleaf-extras-springsecurity

TODO:
1. Zdefiniuj in memory dwóch użytkowników zwracanych z UserRepository. Jeden z rolą ADMIN, drugi z rolą USER
2. Dodaj zależność do thymeleaf-extras-springsecurity
3. Dodaj przetrzeń sec w pliku index.html i wyświetl elementy DIV odpowiednie dla roli - inny dla roli ADMIN, inny dla roli USER
4. Wyświetl nazwę uwierzytelnionego użytkownika (usuń aktualną implementację wrzucającą do modelu username)
    * Wyświetl dodatkowo w nowym DIV role użytkownika
5. Wyświetl formularz dodawania nowej książki wyłącznie użytkownikowi mającemu dostęp do endpointa /newBook
    * W SecurityConfig dodaj nowy antMatcher na /newBook dla użytkownika z rolą ADMIN
6. Przerób TODO #3 aby wykorzystać atrybut Thymeleaf th:if i obiekt #authorization