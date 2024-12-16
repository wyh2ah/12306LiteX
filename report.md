<h1><center>12306LiteX: A Simplified 12306 train ticket booking platform</center></h1>

<h2><center>CS-GY 9053, Java Final Project Report, Fall 2024</center></h2>

<h5><center>Dec. 16</center></h5>

<h5><center>Yuheng Wu, netID: yw5372</center></h5>

<h5><center>Xingyu Han, NetID: xh2787</center></h5>

<h5><center>Weizhen Zhou, NetID: wz3008</center></h5>

### Overview

- 12306 is the official online ticket booking platform for China’s railway system, operated by China Railway Corporation. It's one of the most widely used systems in China, serving millions of users daily, especially during peak travel seasons like the Chinese New Year, where demand for tickets skyrockets. The platform enables users to book tickets, check train schedules, manage reservations, and handle refunds.
- Our goal is to build a simplified ticket booking system inspired by the 12306 platform, allowing the function:

1. ​	register and login
2. ​	administrator login
3. ​	change password and edit user profile
4. ​	search for train schedules by departure station, arrival station and date
5. ​	check train information including stop stations, seats available and name
6. ​	book ticket, pay for ticket and cancel ticket.

- The system will be developed in Java using Spring to leverage modern frameworks for database management, efficient processing, and a user-friendly interface.
- As for innovation, we plan to add an AI-based module to guide user through jumping to different asked panels as well as give tunned suggestions.



### Features

- GUI
- Threads
- Database
- Spring Framework
- Java RESTful APIs and Web Service
- JDBC
-  ...



### Basic Function

#### User Interface

- User login: User can login with different accounts. Each account holds user's information and user's ticket information.
- Administrator login: User can login as an administrator to view the information of platform including train map and train schedule.
- User information lookup and edit: User can update their information and search for their bought tickets.
- Password change: User can change their password with old password check.

#### Ticket Interface

- Ticket consult: User can consult train's combination from departure location to arrival location, provided with date and other sorting options. There are other small features like switch departure to arrival button, system time display, etc.
- Ticket consult result: This panel shows the result of the consult, and the result can be switched between different days, and each result listed should include informations like departure, arrival, time, time spent, price, ticket left(different classes).
- Ticket booking and details: Clicking on one consult result will shift user to another panel that shows the details of the train choosed and let user choose the class they want(First class seat or second class seat).
- Ticket booking: After choose the specific routine with seat class, user can now choose whether to buy the ticket with a 10 min time limit, shown on the corner. If the time limit passed, the buying process will be canceled. The booking process is atomic, which grasps the ticket first.
- Ticket payment: User can pay for their ticket right after booking or later in the Panel 'MyOrder' where they can view all of their tickets.
- Ticket cancel: User can cancel ticket before the departure time.

#### AI (with OpenAI APIs) Interface

We aim to develop an AI-based assistant within the app that can guide users efficiently, inspired by the customer support model in apps like Bank of America.

- User Interaction Panel: Users can click on a chat interface and type in their requests or questions. This could range from asking how to book a ticket to changing their password.

- Natural Language Processing: The AI assistant interprets the user's input by processing it through an AI API, transforming the user's request into actionable commands for the app.

- Detailed Guidance: The assistant will provide step-by-step instructions to guide users through the requested processes.

- Direct Navigation Links: After generating guidance, the AI assistant will offer a direct link within the chat interface. When clicked, this link will take users directly to the relevant feature page or function within the app.

- Feature Availability Feedback: If a requested feature is not yet supported in the app, the AI assistant will inform the user clearly.

- Contextual Replies for Off-Topic Queries: If the user’s input is unrelated to app services, the AI assistant will respond with a polite message to tell the user.



### Backend

![backend](C:\Users\zhouw\Desktop\CS9053\project\12306LiteX\images\backend.png)

For backend, we use Java RESTful APIs for web service and JDBC APIs for database query and update.

We create multiple `Controller`, `Repository` and `Service` entity to implement the function, with `Controller` for API address and HTTP function mapping, `Service` for function call and required process, `Repository` for database connection, utilizing JDBC APIs.

We create `DTO` class in folder `model` to normalize data transmission, we use Java Springboot Jdbc features and prepareStatements to replace our DTO class variables to fill the "?" in our SQL String, so there is no possibility to attacked by SQL injection.

We use oracle to design database schema, and MySQL for DDL and DML code.



### Frontend



### AI Interface



### GUI Preview

