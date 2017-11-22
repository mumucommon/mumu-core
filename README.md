# mumu-core 核心工具包
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/mumucommon/mumu-core/blob/master/LICENSE) 
[![Build Status](https://travis-ci.org/mumucommon/mumu-core.svg?branch=master)](https://travis-ci.org/mumucommon/mumu-core)
[![codecov](https://codecov.io/gh/mumucommon/mumu-zbus/branch/master/graph/badge.svg)](https://codecov.io/gh/mumucommon/mumu-core)

***mumu-core是一个工具核心包，主要包含web项目经常使用的一些组件，包含:***
-  mybatis的dao封装和mybatis配置文件扫描器
-  使用redis缓存的基本配置工具类
-  druid数据源回调函数(加密数据库密码)
-  基本实体封装
-  常用的枚举类型
-  日志收集配置类
-  分页信息配置类
-  响应码配置类
-  各种加密工具类
-  各种数据校验类（字符校验、手机号码邮箱校验）
-  序列化工具类（包含java序列化工具、jboss marshalling序列化工具类）
-  日期工具类（日期转字符串、字符串转日期）

## 简要说明
   通过使用mumu-core组件可以大大减少项目创建的复杂度，实现开箱即用的特性。
   
## chanage log
2017-11-22 
- 添加 ==MumuCacheComponent==工具，该工具是一个抽象类，实现缓存的基本功能（缓存判断、缓存熔断等），然后将获取缓存、缓存删除、缓存对象等接口做成抽象接口，供实现的实现根据项目的情况选择不同的缓存框架（redis、memcache、zalcast等）。
- 添加 ==MumuLogComponent==工具，该工具也是一个抽象类，实现日志数据拦截，然后将日志数据导向到抽象方法中，供实现着来接受日志（控制台打印、kafka、rocketmq、mysql等）。

## 联系方式
**以上观点纯属个人看法，如有不同，欢迎指正。  
email:<babymm@aliyun.com>  
github:[https://github.com/babymm](https://github.com/babymm)**