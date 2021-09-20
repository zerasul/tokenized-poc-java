# Gateway Module

This file contains information about the Gateway Module. This module contains all the gateway configuration as a entrypoint access to all the APIs.

You can acces to the next API:

* /user/: Access to User Creation API(Auth-service).
* /accounts/: Access to Account API (Accounts-service).
* /transfer/: Access to Transfer API (Accounts-service).
* /deposit/: Access to Deposit API (Accounts-service).

## Traceability

This Gateway contains one globalFilter that logs all the inforamtion of the request and response for traceability pruposes.

This logs are only for demostration Pruposes; in real cases, you can use ELK Stack or anothers implementations.
