# 竞赛板块-ZH_CN

## 分页查询竞赛数据

```java
@PostMapping("/adminQueryGameInformationByPage")
@ApiOperation("分页查询竞赛数据")
public String adminQueryGameInformationByPage(@RequestBody AdminGetArticleByPaginVo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageBo)
```

**分页查询竞赛数据**

​	**Aop层：**

​		 无操作，用户、游客、管理员共享此接口。

​	**业务层：**

​		 根据AdminQueryGameInformationByPageVo查询条件进行分页查询。

## 通过id获取比赛赛事

```java
@ApiOperation("通过id获取比赛赛事")
@GetMapping("/getEventById")
public String getEventById(@RequestParam("toekn")String token ,@RequestParam("id")Long id)
```

**通过id获取比赛赛事**

​	  **AOP层：**

​			无任何操作

​	 **业务层**

​			首先先对登入权限进行鉴权，然后获取比赛信息，接下来获取该用户是否报名比赛（但是此后续业务逻辑没有进行开发。）

​	 **未来计划任务**

​			如果已经报名了则检查检测是否被封禁和是否满足获取题库去检查检测是否被封禁如果已经被封禁则不尽兴操作和是否满足获取题库如果不满足则不获取满足则相反。但是该业务不着急开发。

## 获取未来赛事

```java
@ApiOperation("查询未开始的比赛或者赛制")
@GetMapping("/futureEvents")
public String getFutureEvents()
```

**通过id获取比赛赛事**

​	  **AOP层：**

​			无任何操作

​	 **业务层**

​			获取未来进行时赛事和过去完成时赛制。

