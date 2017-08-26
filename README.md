#工具核心包
工具包包含一些经常使用到的工具和一些web服务器经常需要定义的一些实体类。方便web项目的开发和创建。

##工具类 <br/>
    HttpClientUtil 封装了各种方法（get、post、delete、put等）调用<br/>
    
    SecurityUtil 封装了安全工具，对数据进行加密和解密。<br/>
    
    SerializeUtils 使用java默认的序列化工具对数据进行序列化和解序列化。<br/>
    
    ValidateUtils 对各种数据进行校验,邮箱、手机号码、座机等。<br/>
    
    UploadUtil 解析http post请求中的文件上传<br/>

##注解工具
     MumuCache注解主要对一些接口的方法进行数据缓存的作用，当对方法进行该注解的时候，方法调用的时候会获
     取该注解类，从而对该数据进行缓存。目前缓存方式为redis缓存服务器。

     MumuLimit注解主要对一些接口做限制的作用，当一个接口我们想要限制访问次数，可以使用该注解来实现。

     MumuLog日志注解，当我们想要对一些接口进行日志记录的时候，我们会在controller、service接口上添加该注解，
     标记该注解的方法会保存该操作记录，目前支持保存到数据库。使用日志注解可以精确的对一些方法日志记录，而不
     是将所有的接口都记录到日志服务器中。
     
##数据模型
    封装了基本的mybatis工具类，报错BaseDao、BaseDaoImpl。方便使用mybatis操作数据库。
