<h1>Semester 4 | Java | Sprint 1

*Server (Spring Boot), HTTP Client, Command Line Interface*</h1>

This is a group project for our 4nd Semester at Keyin College's Software Development Program.

⠀⠀⠀⠀ ❗ ⠀➝⠀This repository contains **Part 1** of the assignment. *(Spring Boot Server)*

⠀⠀⠀⠀☞ ➝⠀**Part 2** can be found [**HERE**](https://github.com/KeyinTeamAwesome/Sem4_Sprint1_Part2). *(HTTP Client/CLI/Testing)*

---

## **Part 1:** Spring Boot Server

This **Java (Maven)** project uses **Spring Boot** and **MySql** to create a server program that connects to a database and implements an API that is accessible via HTTP.

---

### **Entities**

|    Entity    | Fields                                                                         | 
| :----------: | :----------------------------------------------------------------------------- | 
|    Cities    | id *(int)*, cityName *(String)*, cityState *(String)*, cityPopulation *(int)*  |
|  Passengers  | id *(int)*, firstName *(String)*, lastName *(String)*, phoneNumber *(String)*  |
|   Airports   | id *(int)*, name *(String)*, code *(String)*                                   |
|   Aircraft   | id *(int)*, type *(String)* airlineName *(String)*, numberOfPassengers *(int)* |

---

### **Endpoints**

#### **Questions**

|   Method    | URL                                 | Question                                               |
| :---------: | :---------------------------------- | :----------------------------------------------------- |
|     GET     | localhost:8080/cities_airports      | What airports are in what cities?                      |
|     GET     | localhost:8080/aircraft_passengers  | List all aircraft passengers have travelled on?        |
|     GET     | localhost:8080/aircraft_airports    | Which airports can aircraft take off from and land at? |
|     GET     | localhost:8080/airports_passengers  | What airports have passengers used?                    |

#### **Passengers**

|   Method    | URI                                 |
| :---------: | :---------------------------------- |
|  GET (All)  | localhost:8080/passengers           |
| GET (By ID) | localhost:8080/passenger/{id}       |
|     POST    | localhost:8080/passengers           |
|     PUT     | localhost:8080/passenger/{id}       |
|    DELETE   | localhost:8080/passenger/{id}       |

#### **Cites**

|              Method                | URI                                       | Query Parameter        |
| :--------------------------------: | :---------------------------------------- | :--------------------- |
|             GET (All)              | localhost:8080/cities                     |                        |
|            GET (By ID)             | localhost:8080/city/{id}                  |                        |
|   GET (Passengers By Last Name)    | localhost:8080/airport/passengers_search  | ?lastName=\<String>    |
|                POST                | localhost:8080/cities                     |                        |
|                PUT                 | localhost:8080/city/{id}                  |                        |
|               DELETE               | localhost:8080/city/{id}                  |                        |

#### **Airports**

|              Method                | URI                                       | Query Parameter        |
| :--------------------------------: | :---------------------------------------- | :--------------------- |
|             GET (All)              | localhost:8080/airports                   |                        |
|            GET (By ID)             | localhost:8080/airport/{id}               |                        |
|   GET (Passengers By Last Name)    | localhost:8080/airport/passengers_search  | ?lastName=\<String>    |
|                POST                | localhost:8080/airports                   |                        |
|                PUT                 | localhost:8080/airport/{id}               |                        |
|               DELETE               | localhost:8080/airport/{id}               |                        |
 
#### **Aircraft**

|              Method                | URI                                       | Query Parameter        |
| :--------------------------------: | :---------------------------------------- | :--------------------- |
|             GET (All)              | localhost:8080/aircraft/                  |                        |
|            GET (By ID)             | localhost:8080/aircraft/{id}              |                        |
|       GET (Airports By Name)       | localhost:8080/aircraft/airports_search   | ?airportName=\<String> |
|   GET (Passengers By Last Name)    | localhost:8080/aircraft/passengers_search | ?lastName=\<String>    |
|                POST                | localhost:8080/aircraft                   |                        |
|                PUT                 | localhost:8080/aircraft/{id}              |                        |
|               DELETE               | localhost:8080/aircraft/{id}              |                        |

---

### Contributors

<table>
  <tr>
    <th>Author</th>
    <th>GitHub</th>
  </tr>
  <tr>
    <td>Makenzie Roberts</td>
    <td>
      <a href="https://github.com/MakenzieRoberts"><img height="50px" src="https://avatars.githubusercontent.com/u/100213075?v=4"></a>
    </td>
  </tr> 
  <tr>
    <td>Kara Balsom</td>
    <td>
      <a href="https://github.com/kbalsom"><img height="50px" src="https://avatars.githubusercontent.com/u/100210446?v=4"></a>
    </td>
  </tr>
  <tr>
    <td>David Turner</td>
    <td>
      <a href="https://github.com/DeToxFox"><img height="50px" src="https://avatars.githubusercontent.com/u/95373983?v=4"></a>
    </td>
  </tr>
      <td>Glen May</td>
    <td>
      <a href="https://github.com/ellis0n"><img height="50px" src="https://avatars.githubusercontent.com/u/100211236?v=4"></a>
    </td>
  </tr>
    </tr>
      <td>Terrance Power</td>
    <td>
      <a href="https://github.com/Tpower16"><img height="50px" src="https://avatars.githubusercontent.com/u/100700181?v=4"></a>
    </td>
  </tr>

</table>
