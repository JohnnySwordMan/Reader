### 开发TODO    

+ [x] 新增buildSrc，使用最新的kotlin+buildSrc方式，处理库依赖
+ [ ] 收藏数据打通
+ [ ] 将RxJava改成Coroutines



### 待整理TODO   

+ [ ] kotlin化，整理一下哪些写法更加kotlin化，这个需要平时多看github上的优秀项目啊
+ [ ] buildSrc
+ [ ] [自定义注解](https://www.kotlincn.net/docs/reference/annotations.html)
+ [ ] Deprecated
+ [ ] crossinline：内联，但是lambda表达式中不能return。想想为啥这里要设置成crossinline呢？[crossinline](https://zhooker.github.io/2018/10/15/Kotlin%E4%B8%ADinline-noinline-crossinline%E7%9A%84%E5%8C%BA%E5%88%AB/)


### 知识点  


##### buildSrc  

1. 将Versions和Libs放到Dependency，一直编译错误？
2. [Gradle’s Kotlin DSL BuildSrc](https://medium.com/swlh/gradles-kotlin-dsl-buildsrc-4434100a07d7)
3. [[译]Kotlin + buildSrc：更好的管理Gadle依赖](https://juejin.im/post/6844903615346245646)



##### 其他  

1. [kotlin的Deprecated注解](https://zhuanlan.zhihu.com/p/32890550)



##### 思考  

1. 重构和优化，在某种程度上，是一对反义词，重构往往会导致代码的复杂，优化就会去复杂。
我在写代码的时候，为啥我老是觉得创建多个对象会导致性能下降呢？记住，你在写代码，追求完美的代码，肯定是需要封装的，面向对象，面向接口。