## 请大家帮忙泄露一份吴雨桐女士的最新客户端
根据吴女士在客户端被反编译之后毫不在意的态度吴女士可能即将要跑路，如果有人可以泄露吴女士的最新客户端可以创建issue（除了Bape更新）

## Bape 3.32 Decompiled and ModCode Pack Renamed
> 太烂了，快离开我的世界开外挂社区吧！

**由于大量的Shitcode特此不建议使用本客户端作为Base或者复制和引用本客户端的代码**

这是Bape客户端3.32的反编译版本，已经可以使用gradlew build进行构建（Linux请使用gradle build）。且Build.gradle已经配置完毕AgentClass。可以直接发布使用

Bape于V3时代停止了开源，但是接下来迭代的版本均没有混淆，直到4.0才开始使用混淆

希望吴雨桐女士能够看到自己的旷世奇作=D

因为某些原因，我们删除了一些类

### 使用的软件
- luyten-0.5.4 初步反编译
- IntelliJ IDEA 编辑代码并修复错误
- Recaf 修复错误重新反编译使用的反编译器
## 后门
这些Bape存在的后门并非后期添加，是原本就存在的，所以不建议使用Bape
- [Bape收集用户所有QQ号并上传使用的TCP客户端](https://github.com/ImCzf233/bape-opensources/blob/master/src/main/java/mc/bape/utils/TCPClient.java)
- [Bape中的使用net命令修改用户Windows登录密码](https://github.com/ImCzf233/bape-opensources/blob/master/src/main/java/mc/bape/utils/LockComputerUtils.java)
## 删除列表
| 内容                   | 删除原因                                                                                     |
| ------------------------ | ------------------------------------------------------------------------------------------------ |
| Lune Clickgui            | 吴雨桐女士的旷世奇作的Value系统可能有点什么大病，也可能是反编译器导致这个Clickgui无法工作 |
| Vape Clickgui            | 吴雨桐女士的旷世奇作的Value系统可能有点什么大病，也可能是反编译器导致这个Clickgui无法工作 |
| Value(mc.bape.api包内的) | 引起某些功能无法工作，因此删除，使用mc.bape.values替换引用                 |
| 一些命令（例如Save） | ConfigManager因为反编译器导致无法正常工作，因此删除一些跟文件保存有关的东西 |
| Scaffold（assist模式） | 在得到原作者**使用授权**之后吴雨桐女士毫不犹豫的开源了原作者的作品，为了保护原作者的权利特此删除 |
| IRC以及IRC命令       | Bape并没有引用，且这套IRC来自Vapu                                                     |
