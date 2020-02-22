## TODO

### Formularz logowania
1. Własny formularz logowania. Stwórz konfigurację security, mapowania w kontrolerze na /login. Ważne jest wykorzystanie dobrych nazw z formularza login.html (username, password jako nazwy przesyłanych parametrów) oraz postowanie na endpoint /login. Dodanie permitAll na „/login"
2. Po pomyślnym zalogowaniu wyświetl nazwę zalogowanego użytkownika. Wykorzystaj zdefiniowają przestrzeć "xmlns:sec" w pliku index.html (doc https://github.com/thymeleaf/thymeleaf-extras-springsecurity)
3. Możemy nadpisać domyślne nazwy atrybutów z formularza oraz endpoint na który wykonujemy POST. Możemy to zrobić aby ukryć fakt, że używamy spring security pod spodem
    * zmień nazwy atrybutów na user / pass a endpoint na, który POST-ujesz na /doLogin (konfiguracja Spring Security oraz login.html)
4. Dodamy funkcję logouta. Jest tak samo konfigurowalna jak formularz logowania. Ważne jest to, że jak mamy włączoną obronę przed CSRF to działa wyłącznie /logout POST. Możemy dla próby wyłączyć aby przetestować. Dodaj do index.html buttona, który będzie POST-ował na endpoint /logout. Zmień endpoint /logout na /doLogout

### Remember me
1. Zadanie #1
    * Włącz w SecurityConfig opcję rememberMe
    * Dodaj na formularzu logowania (login.html) checkboxa z atrybutem name="remember-me" - jest to domyślna wartość oczekiwana przez Spring Security
    * Sprawdź w opcjach konsoli developerskiej chrome ciasteczka. Czy pojawiło się nowe? Spróbuj usunąć JSESSIONID i odśwież stronę. Zdekoduj w base64 dekoderze ciasteczko remember-me
2. Zadanie #2
    * Customizacja ustawień remember me. Zmień klucz na "some-key", nazwę ciasteczka na "rememberMe" i nazwę parametru z formularza logowania na "rememberMe". Nanieś odpowiednie zmiany w login.html

### UserDetailsService
1. Zadanie #1
    * zdefiniuj użytkownika in memory nadpisując metodę configure(AuthenticationManagerBuilder auth). Usuń propertiesy z application.properties
2. Zadanie #2
    * Stwórz własną implementację UserDetailsService wykorzystując gotowe UserRepository - pamiętaj o kontrakcie interfejsu. Jako implementację UserDetails wykorzystaj https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/User.html, jako GrantedAuthority -> SimpleGrantedAuthority
    * Stwórz beana PasswordEncoder NoOpPasswordEncoder
    * Skonfiguruj własną implementację UserDetailsService w metodzie configure(AuthenticationManagerBuilder auth)
    
### Roles
1. Wyświetl formularz dodawania nowych książek wyłącznie dla użytkowników z rolą ADMIN. Wykorzystaj do tego przestrzeń "xmlns:sec"

### Session management
1. Ustaw maksymalnie jedną aktywną sesję dla użytkownika - przetestuj logując się ponownie w trybie incognito
2. Poeksperymentuj z ustawieniami sessionFixation()