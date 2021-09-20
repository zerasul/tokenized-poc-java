# Authorization Module

This file contains all the information about the authorization Module. This module have 2 main features:

1. Allow access to all the API; using Oauth 2.0.
2. Create new Users using Users API.

## Oauth 2

This API uses Oauth 2.0 as authorization and authentication process.

In this case, we use a authorization code, for get the access code for all of the resources.

The next Flow show the authorization Process:

1. Connect to authorization Service; with the oauth parameters (grant_type, Client_id, redirect_uri)

```
https://auth-server:9000/oauth2/authorize?response_type=code&client_id=articles-client&scope=articles.read&redirect_uri=http://127.0.0.1:8080/authorized
```

2. Insert your credentials on the Login Page.

3. You must be redirected to the Authorized Page with the parameters code and state.

```
http://localhost:8080/authorize?code=AbCdEfGHiJK12345&state=xyz
```

4. Use grand code for get a new Token from Authorization Server.

```

POST http://localhost:9000/oauth/token

grant_type=authorization_code
&code=AbCdEfGHiJK12345
&redirect_uri=http://localhost:8080/authorized
```

5. Get the token from the response and use as 'Bearer Token' for all the API requests.

```json
{
        "access_token":"2YotnFZFEjr1zCsicMWpAA",
        "token_type":"example",
        "expires_in":3600,
        "refresh_token":"tGzv3JOkF0XG5Qx2TIKWIA"
}
```

## User API

For create a new User for log in our system, you can create a new user.

You can create a new user using the next Endpoint:

```
POST http://localhost:8080/user/

{"
    "username": "user1",
    "password": "password1",
    "email": "email@email.com",
    "phone": "+3466778833"
}
```

This endpoint return you Saved User. Now You can Log in Using Oauth to Our System.

**NOTE**: For demostration pruposes all the users use the same ```client_id``` and ```client_secret```.

**NOTE2**: You need to add to your ```/etc/hosts``` or ```c:\Windows\System32\Driver\etc\hosts``` the next line
```127.0.0.1 auth-server```. This is needed for demostration pruposes and for minimize CORS errors.
