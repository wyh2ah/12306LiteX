### 后端接口列表

- **注册用户**
  - 方法：`POST`
  - URL：`/api/register`
  - 描述：注册新用户。
  - 输入：username, password
  - 返回值：字符串，描述注册成功或失败
  
- **用户登录**
  
  - 方法：`POST`
  
  - URL：`/api/login`
  
  - 描述：用户登录，获取数据库自增userid。
  
  - 输入：username, password
  
  - 返回值：
  
    ```json
    [  
    	{
            "status": "success"
            "userID": ${userid}
        }
        OR
        {
            "status": "fail"
            "message": ${message}
        }
    ]
    ```
  
- **修改密码**
  
  - 方法：`POST`
  
  - URL：`/api/user/change_password`
  
  - 描述：用户修改密码
  
  - 输入：userid, oldPassword, newPassword 
  
  - 返回值：
  
    ```json
    "Reset Password Success"  or "Reset Fail, Incorrect Old Password"
    ```
  
    
  
  **修改用户信息**
  
  - 方法：`POST`
  
  - URL：`/api/user/change_information`
  
  - 描述：修改除密码外所有用户信息
  
  - 输入：
  
    ```json
    {
        "id": 1,
        "username": "alan",
        "password": "1234",
        "fname": "James",
        "lname": "Bond",
        "birthDate": "2024-12-05T10:00:00",
        "gender": "male",
        "nationality": "American",
        "email": "james.bond@example.com",
        "phone": "+1-234-567-890"
    }
    ```
  
    
  
  - 返回值：
  
    ```json
    {
        "id": 1,
        "username": "alan",
        "password": "1234",
        "fname": "James",
        "lname": "Bond",
        "birthDate": "2024-12-05T10:00:00",
        "gender": "male",
        "nationality": "American",
        "email": "james.bond@example.com",
        "phone": "+1-234-567-890"
    }
    ```
    
  
  **获得用户信息**
  
  - 方法：`POST`
  
  - URL：`/api/user/change_information`
  
  - 描述：修改除密码外所有用户信息
  
  - 输入：
  
    userid
  
    
  
  - 返回值：
  
    ```json
    {
        "id": 1,
        "username": "alan",
        "password": "1234",
        "fname": "James",
        "lname": "Bond",
        "birthDate": "2024-12-05T10:00:00",
        "gender": "male",
        "nationality": "American",
        "email": "james.bond@example.com",
        "phone": "+1-234-567-890"
    }
    ```
  
    

**查询行程**

- 方法：`POST`

- URL：`/api/user/search`

- 描述：输入出发站，到达站，日期，查询对应形成

- 输入：

  depart_station, arrival_station, datetime

  ```
  Pittsburgh, Ann Arbor, 2024-12-23
  ```

  ```
  Boston, Orlando, 2024-12-23
  ```

  

- 返回值：

  ```json
  [
      {
          "pathId": 3,
          "stations": [
              "Pittsburgh",
              "Ann Arbor"
          ],
          "arrivalTimeList": [
              "2024-12-23 10:00:00",
              "2024-12-23 13:00:00"
          ],
          "prices_A": 946.52,
          "prices_B": 451.42,
          "prices_C": 301.08,
          "departStationId": "Pittsburgh",
          "arrivalStationId": "Ann Arbor",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Blue Arrow"
      },
      {
          "pathId": 5,
          "stations": [
              "Pittsburgh",
              "Ann Arbor"
          ],
          "arrivalTimeList": [
              "2024-12-23 11:00:00",
              "2024-12-23 13:00:00"
          ],
          "prices_A": 946.52,
          "prices_B": 451.42,
          "prices_C": 301.08,
          "departStationId": "Pittsburgh",
          "arrivalStationId": "Ann Arbor",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Silver Horizon"
      },
      {
          "pathId": 13,
          "stations": [
              "Pittsburgh",
              "Ann Arbor"
          ],
          "arrivalTimeList": [
              "2024-12-23 17:00:00",
              "2024-12-23 20:00:00"
          ],
          "prices_A": 946.52,
          "prices_B": 451.42,
          "prices_C": 301.08,
          "departStationId": "Pittsburgh",
          "arrivalStationId": "Ann Arbor",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Blue Arrow"
      },
      {
          "pathId": 15,
          "stations": [
              "Pittsburgh",
              "Ann Arbor"
          ],
          "arrivalTimeList": [
              "2024-12-23 19:00:00",
              "2024-12-23 21:00:00"
          ],
          "prices_A": 946.52,
          "prices_B": 451.42,
          "prices_C": 301.08,
          "departStationId": "Pittsburgh",
          "arrivalStationId": "Ann Arbor",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Silver Horizon"
      }
  ]
  ```
  
  ```Json
  [
      {
          "pathId": 1,
          "stations": [
              "Boston",
              "New York",
              "Baltimore",
              "Richmond",
              "Orlando"
          ],
          "arrivalTimeList": [
              "2024-12-23 09:00:00",
              "2024-12-23 11:00:00",
              "2024-12-23 13:00:00",
              "2024-12-23 15:00:00",
              "2024-12-23 17:00:00"
          ],
          "prices_A": 2366.3,
          "prices_B": 1128.55,
          "prices_C": 752.6999999999999,
          "departStationId": "Boston",
          "arrivalStationId": "Orlando",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Sunrise Express"
      },
      {
          "pathId": 11,
          "stations": [
              "Boston",
              "New York",
              "Baltimore",
              "Richmond",
              "Orlando"
          ],
          "arrivalTimeList": [
              "2024-12-23 13:00:00",
              "2024-12-23 15:00:00",
              "2024-12-23 17:00:00",
              "2024-12-23 19:00:00",
              "2024-12-23 21:00:00"
          ],
          "prices_A": 2366.3,
          "prices_B": 1128.55,
          "prices_C": 752.6999999999999,
          "departStationId": "Boston",
          "arrivalStationId": "Orlando",
          "aSeatsLeft": 5,
          "bSeatsLeft": 20,
          "cSeatsLeft": 70,
          "train_name": "Sunrise Express"
      }
  ]
  ```
  

**显示行程信息**

- 方法：`GET`

- URL：`/api/user/search/trip`

- 描述：输入出发站，到达站，日期，pathid，查询对应形成

- 输入：

  pathid, depart_station, arrival_station, datetime

  ```
  13, Pittsburgh, Ann Arbor, 2024-12-23
  ```

- 输出：

  ```json
  {
      "pathId": 13,
      "stations": [
          "Pittsburgh",
          "Ann Arbor"
      ],
      "arrivalTimeList": [
          "2024-12-23 17:00:00",
          "2024-12-23 20:00:00"
      ],
      "prices_A": 0.0,
      "prices_B": 0.0,
      "prices_C": 0.0,
      "departStationId": "Pittsburgh",
      "arrivalStationId": "Ann Arbor",
      "aSeatsLeft": 5,
      "bSeatsLeft": 20,
      "cSeatsLeft": 70
  }
  ```
  
  



**订票**

- 方法：`POST`

- URL：`/api/booking

- 描述：生成ticket记录，

- 输入：

  pathID, departureStationName, arrivalStationName, departureTime, seatLevel

  Example: 

  ```
  1, New York, Orlando, 2024-12-23 11:00:00, 2024-12-23 17:00:00
  ```

  
  
  

- 返回值：isSuccess



**查询订单**

- 方法：`POST`

- URL：`/api/user/tickets`

- 描述：输入userid， 返回每张票的信息包括始发站，到达站，价格，日期，座位等级，和invoice 两个状态

- 输入：

  userid

  ```
  2
  ```

  

- 输出：

  ```
  [
      {
          "ticketId": 1,
          "price": 1200.0,
          "departStationName": "New York",
          "arrivalStationName": "Detroit",
          "seatLevel": "A",
          "departureTime": "2024-12-23 07:00:00",
          "arrivalTime": "2024-12-23 16:00:00",
          "trainName": "Emerald Voyage",
          "invoiceId": 1,
          "validState": "valid",
          "paymentState": "True"
      },
      {
          "ticketId": 2,
          "price": 600.0,
          "departStationName": "New York",
          "arrivalStationName": "Detroit",
          "seatLevel": "C",
          "departureTime": "2024-12-23 14:00:00",
          "arrivalTime": "2024-12-23 23:00:00",
          "trainName": "Emerald Voyage",
          "invoiceId": 2,
          "validState": "null",
          "paymentState": "False"
      }
  ]
  ```
  
  
