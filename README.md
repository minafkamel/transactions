# Transactions

This sample app displays a list of transactions from this [json](https://dl.dropboxusercontent.com/s/9c39669ux2jtarx/bookings.json)


# Screens 

The app consists of one screens: Transactions
<p align="center">
<img src="https://github.com/minafkamel/transactions/blob/master/media/Transactions.png"
         alt="Transactions" width="400" height="700">

# Architecture

The project follows [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) to ensure separation of concerns and single responsibility. It consists of three layers: Data, Domain and UI.

#### Data Layer:   
Consists of APIs and repositories (for e.g: `TransactionsApi` & `TransactionsRepository`) and raw entities. Responsible for network operations and caching data.

In the app, TransactionsApi API `s/9c39669ux2jtarx/bookings.json` response from is cached in order to minimise endpoint hits.

#### Domain Layer: 
Consists of UseCases where each of them performs a piece of logic (for e.g: `GetTransactionsUseCase`). This layer acts as a toolbox for the presentation layer for performing any "thinking". It also connects presentation layer to data layer.  

`GetTransactionsUseCase` calls `TransactionsRepository`'s `getTransactions`. It first looks into cached values in `Cache` and if not present, hits the endpoint.

#### UI layer (presentation): 
Activities, fragments, views... etc. Along with ViewModels which are responsible for controlling and providing framework ui components with data they should present.  

#### Extra: 
 Mappers: creates View Entities from raw data. It's responsible for formatting data to be shown in a screen

# Design Patterns

- *RxJava vs Live Data*: RxJava is used in data and domain layers to benefit from its operators and threading features while Live Data is used in UI layer for communication between view models and fragments/activities
- *Dependency injection*: The code heavily relies on dagger for dependency injection.
- *Assisted injection*: To inject parameters from fragments/activities to view models through constructors.
- *ArrangeBuilder*: Used to organise tests in a better way by builder the arrange part of each tests making them more readable and allows re-usability for whenever statements.

# Tests

23 unit tests have been added to ensure stability and less bugs:
- Data: `TransactionsRepositoryTest` (2)
- Domain: `GetTransactionsUseCaseTest` (1)
- UI: `TransactionsMapperTest` (18), `MainViewModelTest` (2)

# Layout
The app uses [ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout) to ensure flexibility and cleaner and less layered xml.

# Tech stack
- Third party libraries are organised in **Dependencies.kt**:
	-  Rxjava2,
	- Retrofit2
	- Dagger
	- Architecture components
	- Mockito 
	- Junit
