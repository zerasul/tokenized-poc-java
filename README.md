# Tokenized-poc-java

This is the Tokenized Money PoC implementation Repository. This document contains all the information about this implementation. You can see the next Modules:

* [Gateway Module](gateway): Gateway for access all the API.
* [Configuration Service](config-service): Configuration Service, for acces all the externalized Properties.
* [Authorization Service](auth-service): Authorization Service; for Authentication and Authorization using Oauth 2.0 and create new Users.
* [Accounts Service](accounts-service): Account Service with Accounts, Transfers and Deposit Operations. All operations are secured for use Authorization Service.

You can see all the source code; and each module have his own readme file. This project Uses Maven as configuration and construction Tools. But You can use Gradle too (you need to create all the gradle files).
