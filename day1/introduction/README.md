## Wprowadznie

**HTTP BASIC:**

1. Włącz uwierzytelnianie HTTP BASIC
2. Skonfiguruj AuthenticationEntryPoint:
    * Zwracaj kod odpowiedzi 401
    * Zwracaj header HTTP WWW-Authenticate: Basic (ew z ustawionym realmem)
    * Zwróć JSON-a { "message": "unauthenticated" }. Zwróć albo literlanie stringa albo wykorzystaj ObjectMappera

**SecurityContextHolder:**

1. Zwróć w IndexController nazwę zalogowanego użytkownika

**Publicznie dostępne zasoby:**

1. Endpoint /contact powinien być publicznie dostępny
2. Dodaj w IndexController mapowanie na endpoint /contact