## X.509 Spring Security example

Certyfikaty w cert/
* certyfikat servera server/ wraz z keystorem i truststorem, które mają zainstalowany ten certyfikat
* dwa klienckie certyfikaty w client/
    * z rolą USER w MD/
    * z rolą ADMIN w MD_ADMIN/
    
Hasło do wszystkiego to **password**


Certyfikaty wygenerowane wg https://blog.codecentric.de/en/2018/08/x-509-client-certificates-with-spring-security/

###Server 
Serverowy certyfikat:
1. `openssl genrsa -aes256 -out serverprivate.key 2048`
2. `openssl req -x509 -new -nodes -key serverprivate.key -sha256 -days 1024 -out serverCA.crt`

Import to truststore:

`keytool -import -file serverCA.crt -alias serverCA -keystore truststore.jks`

Export CA cert to keystore

`openssl pkcs12 -export -in serverCA.crt -inkey serverprivate.key -certfile serverCA.crt -out keystore.p12`

Konwertowanie P12 do JKS

`keytool -importkeystore -srckeystore keystore.p12 -srcstoretype pkcs12 -destkeystore keystore.jks -deststoretype JKS`

###Client

Generowanie certyfikatów klienckich:

1. `openssl genrsa -aes256 -out clientprivate.key 2048`
2. `openssl req -new -key clientprivate.key -out client.csr`

Klient wysyła CSR do CA

`openssl x509 -req -in client.csr -CA serverCA.crt -CAkey serverprivate.key -CAcreateserial -out client.crt -days 365 -sha256`

###Testowanie z CURL-em

`curl -ik --cert client.crt --key clientprivate.key "https://localhost:8443/me"`