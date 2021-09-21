# Accounts Service

This is the accounts service that defines all the APIS needed for works with the accounts (Wallets), and his movements.

## API

In this microservice, we have the next REST API:

* Accounts: allow to interact with the accounts of the user.
* Deposit: Allow to create Money Deposit in one of the wallet of our users.
* Transfer: allow to Transfer Money withing accounts.

### Accounts

This Rest API allows to search create, update, delete accounts (Wallets).

```curl
endpoint: http://localhost:8082/accounts/
```

#### Get Accounts From User

```curl
GET <endpoint>/user/{userid}
```

Gets a list of the User's Accounts.

Parameters:

userid(Path): User Id (can be obtained from the Connected user).

returns:

List of Accounts:

```json
[
    {
        "id": 1,
        "name": "account1",
        "number": "1111111-11111-1111",
        "user": 1,
        "created": "21/09/2021 13:00:00",
        "movementList"[<list of Movements>]
    }
]
```

#### Get Account From Number

Get the account associated with a number.

```curl
GET <endpoint>/{number}
```

parameters:

number (path): account's Number.

returns:

Account Object:

```json
    {
        "id": 1,
        "name": "account1",
        "number": "1111111-11111-1111",
        "user": 1,
        "created": "21/09/2021 13:00:00",
        "movementList"[<list of Movements>]
    }
```

#### Create Account

Create a new Account

```curl
POST <endpoint>/accounts
```

parameters:

Account Input Object (body):

```json
{
    "name": "account name",
    "user": 1
}
```

returns:

New Created Account:

```json

{
    "id": 1,
    "name": "account1",
    "number": "1111111-11111-1111",
    "user": 1,
    "created": "21/09/2021 13:00:00",
}
```

#### Update Existing Account

Update an existing Account.

```curl
PUT <endpoint>/accounts/{number}
```

parameters:

number (path): Account's Number.

Account Intput Object (body):

```json
{
    "name": "account name",
    "user": 1
}
```

returns:

Updated Account:

```json

{
    "id": 1,
    "name": "account1",
    "number": "1111111-11111-1111",
    "user": 1,
    "created": "21/09/2021 13:00:00",
    ...
}
```

#### Delete Account

Delete an existing account

```curl
DELETE <endpoint>/accounts/{number}
```

Parameters:

number (path): Account's Number.

returns:

200: OK

