<h1><center>Java Project Proposal</center></h1>

<h2><center>12306LiteX: A Simplified China's 12306 train ticket booking platform</center></h2>

[TOC]

# Team Members

- Yuheng Wu, `NetID`: yw5372
- Xingyu Han, `NetID`: xh2787
- Weizhen Zhou, `NetID`: wz3008

# Overview

- 12306 is the official online ticket booking platform for China’s railway system, operated by China Railway Corporation. It's one of the most widely used systems in China, serving millions of users daily, especially during peak travel seasons like the Chinese New Year, where demand for tickets skyrockets. The platform enables users to book tickets, check train schedules, manage reservations, and handle refunds.
- Our goal is to build a simplified ticket booking system inspired by the 12306 platform, allowing users to search for train schedules, check ticket availability, book tickets, and manage their bookings. The system will be developed in Java using Spring to leverage modern frameworks for database management, efficient processing, and a user-friendly interface.
- As for innovation, we plann to add an AI-based module to guide user through jumping to different asked panels as well as give tunned suggestions.

# Java Features Used

- GUI
- Threads
- Database
- Spring Framework
- [[maybe_unused]]Networking
- [[maybe_unused]]Security

# Basic Functions

## User Interface

- User login: User can login with different accounts. Each account holds user's information and user's ticket information.
- User information lookup and edit: User can update their information and search for their bought tickets.
- Ticket consult: User can consult train's combination from departure location to arrival location, provided with date and other sorting options. There are other small features like switch departure to arrival button, system time display, etc.
- Ticket consult result: This panel shows the result of the consult, and the result can be switched between different days, and each result listed should include informations like departure, arrival, time, time spent, price, ticket left(different classes).
- Ticket booking and details: Clicking on one consult result will shift user to another panel that shows the details of the train choosed and let user choose the class they want(First class seat or second class seat).
- Ticket booking: After choose the specific routine with seat class, user can now choose whether to buy the ticket with a 10 min time limit, shown on the corner. If the time limit passed, the buying process will be canceled. The booking process is atomic, which grasps the ticket first.
- Ticket reschedule: User can pay the price gap to reschedule undepartured trains in their ticket management.
- Ticket cancel: User can cancel ticket that has not departured.

## Admin Interface

- If login with admin account, the application will display admin infos, including all trains schedules, current train location map, etc.

## AI (with OpenAI APIs) Interface

**TODO:XINGYU**

Test

# Notes

Since using the whole Chinese map as the real case is too complicated, we may use a major station list among Shanghai, Nanjin and Zhejiang metropolitan Area.
