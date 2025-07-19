package com.tuling.springcloud.orders.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 常见的分布式事务解决方案
 *  1、seata阿里分布式事务框架 at (其实seata提供所有的模式，at tcc saga xa，默认at)
 *  2、消息队列              tcc  让人无语，谁会这么搞啊？
 *  3、saga                saga  这他妈傻逼吧？当对方服务不能被我们素影响时，只能用这种方式
 *  4、XA                   xa  支持XA协议的数据库都可以，sql执行后不commit，直到所有微服务的数据库的sql全部执行完成之后，TC会统一让所有sql进行commit！
 *
 *
 *  分布式事务的提交协议：2pc和3pc
 *  3PC难以实现所有现在如今大部分的分布式事务提交都是2PC，seata也是
 *
 *  2pc事务提交过程为：prepare and commit
 *  一、预处理阶段
 *      1、请求进来之后第一步会先找到一个事务管理器作为中间协调者而不是直接开启一个本地事务
 *      2、协调者会向所有这一次参与事务的服务发送预处理请求
 *      3、每一个服务都会预先运行各自的sql，看这次的sql能否执行成功，如果成功则保存各自事务的运行前结果和运行后结果
 *      4、预处理成功之后，每个服务都会想事务协调者返回一个应答信息，告知事务协调者是不是可以提交事务
 *  二、确认阶段
 *      5、如果所有事务的参与都可以提交事务，服务协调者通知所有服务，整个事务就被提交，有任何一个服务不能提交事务，则所有服务都需要回滚
 *      6、各个服务提交事务之后，继续返回一个消息给事务协调者，告知事务成功
 *      事实上事务不可能被百分百保证成功，因此第5步的时候一个服务挂到，其他的事务依然会提交，只能尽尽量保持事务成功率
 *      需要注意，这一阶段会导致所有参与事务的服务处于阻塞阶段
 *
 *      缺点：1、不能保证事务一定成功，事务参与者会被阻塞，影响性能
 *           2、依赖协调者，协调者挂了导致服务一直阻塞
 *           3、事务失败会导致数据库一致性出问题
 *
 *   at模式：
 *      1、auto trans，一种无侵入的分布式事务解决方案，由seata实现
 *      2、用户只需关注自己的业务sql
 *      3、过程中加锁
 *      具体实现过程具体参考springcloud.md的解释
 *   tcc模式：
 *      1、侵入性强，需要自己添加事务控制逻辑
 *      2、整个过程中基本没用锁，性能强
 *
 *   配置TC，1、包括设置保存事务信息的位置为mysql数据库
 *          2、设置配置中心为nacos
 *          3、设置注册中心为nacos
 *
 *   seata的搭建过程：
 *      1、设置数据源，默认为file，高可用下会被设置为mysql下，需要修改seata/conf/file.config文件，设置mysql连接信息
 *      2、运行对应的mysql文件，创建数据库表，这样就配置好了seata的统一数据源，数据源存放了
 *      3、配置nacos注册中心（为了让seata获取事务的参与者）和配置中心（TC也需要对应的配置）
 *=======配置好之后运行seata下的seata-server.bat文件启动seata，seata会被注册到nacso中
 *
 *
 *      4、给各个需要seata的导入seata的starter依赖
 *      5、再参与事务的微服务设计的数据库下创建undoLog表，预处理的数据都会存放在这个表中
 *      6、参与分布式事务的要配置seata的访问，（告诉微服务怎么去访问seata，见注1，需要预先运行seata-server.bat）
 *      7、配置seata的配置信息，就是seata\script\config-center\nacos\nacos-config.sh运行之后，nacos配置中心出来了7 8页配置
 *      8、把原先写@Transactional的地方改为@GlobalTransactional
 *
 *
 *    注1、这是seata配置文件中配置的，关于seata的服务信息，它是一个注册再127.0.0.1:8848这个nacos中的名称为seata-server(TC)的服务
 *    nacos {
 *     application = "seata-server"
 *     serverAddr = "127.0.0.1:8848"
 *     group = "SEATA_GROUP"
 *     namespace = ""
 *     cluster = "default"
 *     username = "nacos"
 *     password = "nacos"
 *   }
 */
@RestController
@RequestMapping("/seata")
public class SeataController {
}
