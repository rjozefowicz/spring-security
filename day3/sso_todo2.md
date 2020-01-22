* Dopisać kontroler IndexController z @RequestMappingiem na „/„ i pobrać w metodzie z SecurityContextHoldera aktualnego usera i przeanalizować co mamy w środku
* Mamy implementację Authentication jako OAuth2Authentication, który w środku ma
    * userAuthentication: UsernamePasswordAuthenticationToken
* Dopisać w IndexControllerze pobieranie z OAuth2Authentication nazwy użytkownika + zdjęcia i zwrócić je do widoku index
* Stworzyć index.html, który wyświetli nazwę użytkownika pobraną z gugla + zdjęcie
* Trzeba dodać zależność do ThymeLeafa
