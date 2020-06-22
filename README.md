Med Cabinet Backend API
=======================

Authenticating with OAUTH2
--------------------------

Authenticating with OAUTH2 is similar to JWT as both return a token upon success.
The main difference is the header. 

Example header:
`

`


Creating a new user
-------------------

Endpoint: `https://medcabinetjune2020.herokuapp.com/createnewuser`

Example JSON to send in the body
```
{
    "username": "Ian",
    "primaryemail": "mcelroyian@gmail.com",
    "password": "password"
}
```

Logs the new user in on success and returns the token:
```
{
    "access_token": "38759970-2c1b-4f11-841d-38b01a88aef0",
    "token_type": "bearer",
    "scope": "read trust write"
}
```

and the Location Header of the new user
`Location Header: localhost:2019/users/user/62`
