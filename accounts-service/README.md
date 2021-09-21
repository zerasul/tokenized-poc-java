# Accounts Service

This is the accounts service that defines all the APIS needed for works with the accounts (Wallets), and his movements.

## API

In this microservice, we have the next REST API:

* Accounts: allow to interact with the accounts of the user.
* Deposit: Allow to create Money Deposit in one of the wallet of our users.
* Transfer: allow to Transfer Money withing accounts.

**NOTE**: You need a access token to acess to the API; please see Auth-service readme, for more information.

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
POST <endpoint>/
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
PUT <endpoint>/{number}
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
DELETE <endpoint>/{number}
```

Parameters:

number (path): Account's Number.

returns:

200: OK

### Deposit

This API allows to create Money deposit To existing Accounts.

```curl
endpoint: http://localhost:8082/deposit/
```

#### Get Deposit

Get an existing Deposit

```curl
GET <endpoint>/{id}
```

parameters:

id(path): Deposit Id

returns:

Movement Object:

```json
{
  "id":1,
  "transferDate": "21/09/2021 20:23:00",
  "originAccount": null,
  "destinationAccount": {account Object},
  "amount": 0.1,
  "currency": "BTC"
}
```

#### Create Deposit

Create a new Deposit

```curl
POST <endpoint>/
```

parameters:

New Deposit Input Object:

```json
{
    "destinationNumber":"1111-111-1111",
    "amount": 0.1,
    "currency": "BTC"
}
```

returns:

Created Movement Object:

```json
{
  "id":1,
  "transferDate": "21/09/2021 20:23:00",
  "originAccount": null,
  "destinationAccount": {account Object},
  "amount": 0.1,
  "currency": "BTC"
}
```

This is an Asynchrunous operation; in this case we use an event implementation for create a message qeue; in this operation we publish a new Movement Event and create in the listener the new Deposit.

The events messages implementation is only for demostration pruposes.

For more information please see ```MovementListener``` Class.

### Transfer

Allow to search and create Money transfers.

```curl
endpoint: http://localhost:8082/transfer/
```

#### Get Transfer

Get an existing transfer:

```
GET <endpoint>/{id}
```

Parameters:

id(paht): Transfer id.

returns:

```json
{
  "id":1,
  "transferDate": "21/09/2021 20:23:00",
  "originAccount": null,
  "destinationAccount": {account Object},
  "amount": 0.1,
  "currency": "BTC"
}
```

#### Create Transfer

Create a new transfer

```curl
POST <endpoint>/
```

Parameters:

New Transfer Input Object:

```json
{
    "originNumber": "11111-11-222",
    "destinationNumber":"1111-111-1111",
    "amount": 0.1,
    "currency": "BTC"
}
```

returns:

Created Transfer Object:

```json
{
  "id":1,
  "transferDate": "21/09/2021 20:23:00",
  "originAccount": {account Object},
  "destinationAccount": {account Object},
  "amount": 0.1,
  "currency": "BTC"
}
```

This is an Asynchrunous operation; in this case we use an event implementation for create a message qeue; in this operation we publish a new Movement Event and create in the listener the new Deposit.

The events messages implementation is only for demostration pruposes.

For more information please see ```MovementListener``` Class.
