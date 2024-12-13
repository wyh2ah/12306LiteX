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
    
    
