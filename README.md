# OAuth2.0Demo

#### 项目介绍
自己手动实现的`单点登录（SSO）`和`OAuth2.0授权`的Demo项目。 

#### 技术依赖 ####

- `Spring Boot`：项目基础架构
- `thymeleaf`：用于构建测试页面模板
- `MyBatis`：用于访问`MySQL`数据库 

#### 环境依赖 ####

- `JDK8+`
- `MySQL5.7+`
- `Redis集群`

#### 三个子项目说明 ####

- `ServerDemo`：`OAuth2.0授权`服务端项目，用于提供`OAuth2.0授权`接口，以及用于提供`单点登录（SSO）`服务
- `ClientDemo`：用于测试`OAuth2.0授权`的第三方客户端项目
- `SsoClientDemo`：用于测试`单点登录（SSO）`的客户端项目

------

#### ServerDemo项目 ####

##### 用户注册相关接口： #####

（1）用户注册：

**接口地址**：`http://127.0.0.1:7000/register`

**请求header**：`Content-Type: application/json;charset=UTF-8`

**请求body**：

```json
{"username":"Tom","password":"123456","mobile":"12306","email":"admin@zifangsky.cn"}
```

（2）登录地址：`http://127.0.0.1:7000/login`

（3）注销地址：`http://127.0.0.1:7000/logout`

（4）用户首页：`http://127.0.0.1:7000/user/userIndex`



##### OAuth2.0授权相关接口： #####

（1）客户端注册接口：

**接口地址**：`http://127.0.0.1:7000/oauth2.0/clientRegister`

**请求header**：`Content-Type: application/json;charset=UTF-8`

**请求body**：

```json
{"clientName":"测试客户端","redirectUri":"http://localhost:7080/login","description":"这是一个测试客户端服务"}
```

（2）授权页面：`http://127.0.0.1:7000/oauth2.0/authorizePage?redirectUri=http%3A%2F%2F127.0.0.1%3A7000%2Foauth2.0%2Fauthorize%3Fclient_id%3Dx3qwrgrO1wYdz72joZ8YyIuD%26scope%3Dbasic%26response_type%3Dcode%26state%3DAB1357%26redirect_uri%3Dhttp%3A%2F%2F127.0.0.1%3A7000%2Fuser%2FuserIndex&client_id=x3qwrgrO1wYdz72joZ8YyIuD&scope=basic`

（3）获取Authorization Code：

**接口地址：**`http://127.0.0.1:7000/oauth2.0/authorize?client_id=7Ugj6XWmTDpyYp8M8njG3hqx&scope=basic&response_type=code&state=AB1357&redirect_uri=http://192.168.197.130:7080/login`

（4）通过Authorization Code获取Access Token：

**接口地址**：`http://127.0.0.1:7000/oauth2.0/token?grant_type=authorization_code&code=82ce2bf34f5028d7e8a517ef381f5c87f0139b26&client_id=7Ugj6XWmTDpyYp8M8njG3hqx&client_secret=tur2rlFfywR9OOP3fB5ZbsLTnNuNabI3&redirect_uri=http://192.168.197.130:7080/login`

**返回如下**：

```json
{
	"access_token": "1.6659c9d38f5943f97db334874e5229284cdd1523.2592000.1537600367",
	"refresh_token": "2.b19923a01cf35ccab48ddbd687750408bd1cb763.31536000.1566544316",
	"expires_in": 2592000,
	"scope": "basic"
}
```

（5）通过Refresh Token刷新Access Token：

**接口地址**：`http://127.0.0.1:7000/oauth2.0/refreshToken?refresh_token=2.5c58637a2d51e4470d3e1189978e94da8402785e.31536000.1566283826`

**返回如下**：

```json
{
	"access_token": "1.adebb0a4522d5dae9eaf94a5af4fec070c4f3dce.2592000.1537508734",
	"refresh_token": "2.5c58637a2d51e4470d3e1189978e94da8402785e.31536000.1566283826",
	"expires_in": 2592000,
	"scope": "basic"
}
```

（6）通过Access Token获取用户信息：

**接口地址**：`http://127.0.0.1:7000/api/users/getInfo?access_token=1.adebb0a4522d5dae9eaf94a5af4fec070c4f3dce.2592000.1537508734`

**返回如下**：

```json
{
	"mobile": "110",
	"id": 1,
	"email": "admin@zifangsky.cn",
	"username": "admin"
}
```



##### 单点登录相关接口： #####

（1）获取Access Token：

**接口地址**：`http://127.0.0.1:7000/sso/token?channel=TEST_CLIENT1&redirect_uri=http://192.168.197.130:6080/login`

（2）校验Access Token，并返回用户信息：

**接口地址**：`http://127.0.0.1:7000/sso/verify?access_token=11.13dcfe75708be960abf054de0b5d242a5e2a9f10.2592000.1538209320`

**返回如下**：

```json
{
  "access_token": "11.13dcfe75708be960abf054de0b5d242a5e2a9f10.2592000.1538209320",
  "refresh_token": "12.750e9a2b560acfda8e712057e128b075a19edc93.31536000.1567153355",
  "expires_in": 2592000,
  "user_info": {
    "id": 1,
    "username": "admin",
    "password": "$5$B1pRvzEl$cIB/RBKJ8JYq5PEnuXggnA7nJQMx2/EF10lcbLonaP3",
    "mobile": "110",
    "email": "admin@zifangsky.cn",
    "createTime": "2017-12-31T16:00:00.000+0000",
    "updateTime": "2017-12-31T16:00:00.000+0000",
    "status": 1,
    "roles": [
      {
        "id": 1,
        "roleName": "manager",
        "description": "管理员",
        "funcs": null
      }
    ]
  }
}
```

（3）刷新Access Token：

**接口地址**：`http://127.0.0.1:7000/sso/refreshToken?refresh_token=12.750e9a2b560acfda8e712057e128b075a19edc93.31536000.1567153355`

返回如下：

```json
{
	"access_token": "11.eb9866fa4f6dd1d4d32acb72e3db33502a7541ed.2592000.1538536378",
	"refresh_token": "12.76ea056a721c7142c3a5c48d3f1e73f627c94c2e.31536000.1567231591",
	"expires_in": 2592000,
	"user_info": {
		"id": 1,
		"username": "admin",
		"password": "$5$B1pRvzEl$cIB/RBKJ8JYq5PEnuXggnA7nJQMx2/EF10lcbLonaP3",
		"mobile": "110",
		"email": "admin@zifangsky.cn",
		"createTime": "2017-12-31T16:00:00.000+0000",
		"updateTime": "2017-12-31T16:00:00.000+0000",
		"status": 1,
		"roles": [{
				"id": 1,
				"roleName": "manager",
				"description": "管理员",
				"funcs": null
			}
		]
	}
}
```

------

#### ClientDemo项目 ####

> **特别提示**：在测试代码的时候，最好将授权服务端和客户端分别运行于两个不同服务器上面，不然域名都是localhost会被浏览器判断为同一个网站。 

（1）登录地址：`http://192.168.197.130:7080/login`

（2）用户首页：`http://192.168.197.130:7080/user/userIndex`

------

#### SsoClientDemo项目 ####

> **特别提示**：在测试代码的时候，最好将单点登录服务端和客户端分别运行于两个不同服务器上面，不然域名都是localhost会被浏览器判断为同一个网站。 

（1）登录地址：`http://192.168.197.130:6080/login`

（2）用户首页：`http://192.168.197.130:6080/user/userIndex`

在启动`SsoClientDemo项目`并跳转到`ServerDemo项目`第一次登录成功之后，重启`SsoClientDemo项目`再次登录，可以发现这次是直接登录了（当然也可以把`SsoClientDemo项目`部署到多个服务器上面，先后登录查看效果）。

------

#### 详细的项目设计开发思路 ####

- [OAuth2.0协议入门（一）：OAuth2.0协议的基本概念以及使用授权码模式（authorization code）实现百度账号登录](https://www.zifangsky.cn/1309.html)
- [OAuth2.0协议入门（二）：OAuth2.0授权服务端从设计到实现](https://www.zifangsky.cn/1313.html)
- [OAuth2.0协议入门（三）：OAuth2.0授权与单点登录（SSO）的区别以及单点登录服务端从设计到实现](https://www.zifangsky.cn/1327.html)

