
## Authorization code flow

### 1. Get authorization code

```http://localhost:8080/oauth/authorize?grant_type=authorization_code&response_type=code&client_id=client-id1&state=1234```

### 2. Get tokens

```curl -X POST --user client-id1:some-secret1 http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=kVgpP8&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8081%2Fclient-id1%2Fcallback&scope=read"```