# Cocktails Application ScreenShots
https://www.thecocktaildb.com/ 
<table>
  <tr>
    <td>Home Screen Page</td>
     <td>Detail Page</td>
     <td>Favourite Page</td>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/8686269/133089772-969fdb27-e862-4bf4-a2e7-4c0506169275.png"width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/8686269/133089825-26127400-5251-4256-8618-fb02e05185bb.png"width=270 height=480></td>
    <td><img src="https://user-images.githubusercontent.com/8686269/133089878-de926e00-5215-4813-9c78-8041311cd153.png"width=270 height=480></td>
  </tr>
# Technologies used 
1 Kotlin Coroutines FLOW API:  A  data source or repository is typically a producer of UI data that has the View as the consumer that ultimately displays the data. Other times, the View layer is a producer of user input events and other layers of the hierarchy consume them. Layers in between the producer and consumer usually act as intermediaries that modify the stream of data to adjust it to the requirements of the following layer.A flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.

2 Channels Flow: Creates an instance of a coldFlow with elements that are sent to a SendChannel provided to the builder's block of code via ProducerScope

3 Hilt Dependency injection:  Hilt provides the following basic scopes as components.
Application
Activity / ActivityRetained
Fragment
Service
View / ViewWithFragment
By attaching @InstallIn annotation to a module, the module will get a limited lifetime belongs to the scope. And the lifecycle is managed by Hilt as components. 

4 Espresso 
5 Room
6 ViewModel
7 LiveData
8 Navigation
9 Paging
10 Data Binding
 

# Architecture Design
The Project follows a MVVM with Repository pattern architecture. This architecture was chosen for:
- Separation of Concerns that provides a way to testing the architecture components in isolation and allows for the View classes to be updated without modifying the ViewModel classes.
- Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.
- Communication between fragments using a ViewModel class removes the need for fragments to communicate via an Activity using callbacks.

The View classes use data binding to communicate updates to their respective ViewModel classes. The ViewModel classes communicate with a Repository class using LiveData. This is then passed back to the View classes observing this LiveData. The Repository class communicates with a RESTful API using Retrofit and caches the response to a local Room database.

![Alt text](app/docs/images/mvvm_architecture.png?raw=true "MVVM Architecture")

 </table>


