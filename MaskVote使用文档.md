# MaskVote使用文档

## 环境准备

- hyperledger fabric 2.0.1

- docker



## 示例虚拟机

示例的虚拟机中采用的centos系统，已经预先安装好fabric以及docker，开机的密码为xidian2019。并且其中已经添加了智能合约，只需通过脚本启动fabric即可。

以下所有流程如无特殊声明，均是在超级管理员的模式下进行的。进入root模式的具体命令如下：

```
[lxq@localhost ~]$ su
密码：
```

其中密码为：root

**注意：虚拟机每次关机或者挂起后，fabric都会停止运行，再次启动虚拟机时，需要重新进行部署！**



## 部署流程

在部署之前确保虚拟机中的网络正常。即输入``` ifconfig ```命令后，可以显示出ens33网卡的信息，ip地址在192.168.x.x网段之内，如下如所示。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630183041228.png)

### 添加智能合约（首次部署必须）

将智能合约 ```abstore.go```复制到地址```/opt/golang/src/github.com/hyperledger/fabric-samples/chaincode/abstore/go```下。abstore.go文件已经预先存放在根目录下。

具体操作流程如下：

```
[root@localhost lxq]# cp abstore.go /opt/golang/src/github.com/hyperledger/fabric-samples/chaincode/abstore/go/
cp：是否覆盖"/opt/golang/src/github.com/hyperledger/fabric-samples/chaincode/abstore/go/abstore.go"？ yes
```

**注意，这里要输入yes，不可以用回车代替，否则不会被覆盖。**

### 利用脚本启动fabric

开启一个终端，进入root模式下，切换到部署脚本的路径下，执行脚本，用于启动fabric。具体的命令如下，顺次执行即可。

```
[root@localhost lxq]# cd /opt/golang/src/github.com/hyperledger/fabric-samples/first-network
[root@localhost first-network]# ./byfn.sh down
Stopping for channel 'mychannel' with CLI timeout of '10' seconds and CLI delay of '3' seconds
Continue? [Y/n] Y
proceeding ...
[root@localhost first-network]# ./byfn.sh generate
Generating certs and genesis block for channel 'mychannel' with CLI timeout of '10' seconds and CLI delay of '3' seconds
Continue? [Y/n] Y
proceeding ...
[root@localhost first-network]# ./byfn.sh up
Starting for channel 'mychannel' with CLI timeout of '10' seconds and CLI delay of '3' seconds
Continue? [Y/n] Y
proceeding ...

```

命令行出现如下画面，代表成功。如果启动失败，重新执行顺次执行部署脚本的三条命令即可。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630175148123.png)



### 验证fabric是否启动（非必须操作）

为验证fabric已经成功启动，可以进入到docker中查看。

具体执行命令如下：

```
[root@localhost first-network]# docker exec -it cli bash
bash-5.0# peer channel list
2021-06-30 09:53:11.417 UTC [channelCmd] InitCmdFactory -> INFO 001 Endorser and orderer connections initialized
Channels peers has joined: 
mychannel
bash-5.0# peer channel getinfo -c mychannel
2021-06-30 09:53:19.370 UTC [channelCmd] InitCmdFactory -> INFO 001 Endorser and orderer connections initialized
Blockchain info: {"height":7,"currentBlockHash":"M2cE3hfeAhEuRjfMVkTFiTO1v1ZUhZjd9rlRAZgEGXk=","previousBlockHash":"gV1Kv1zeI9tMQu3nytqlIPukRQtB7k878wEu0xIc7i4="}
bash-5.0# exit
exit
[root@localhost first-network]# 

```

由以上指令可以看出，在docker中已经存在对应的容器，当前链的高度为7，这是fabric的初始高度。



### 复制config配置文件

由于每次虚拟机启动后，fabric需要重新启动，因此需要新的证书，需要将证书加入到java后端代码中。

```
[root@localhost first-network]# tar cvf crypto-config.tar ./crypto-config
[root@localhost first-network]# mv crypto-config.tar /home/lxq/
```

执行上述两条命令之后，可以在根目录下看到一个名为```crypto-config.tar```的压缩包，将压缩包从虚拟机中导出到实体机中。



### 修改java后端程序

首先检查后端代码中的```src/main/resources/connection.json```文件的ip地址是否与虚拟机的IP地址一致，如果不一致需要将json文件中的所有IP地址都进行替换，ip地址后的端口号不要更改。

将crypto-config解压，解压后在crypto-config文件夹下应该有以下两个文件。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630190356910.png)

将crypto-config文件夹放在```src/main/resources```目录下，替换原有的crypto-config。

替换完成后，启动相应的后端代码即可。



## MaskVote使用流程

### 打开网页

在MaskVote中，存在三种角色，分别是发布者、计票员和投票员。

- 发布者：发布选举和表决事务（选举为三选一，表决为二选一）。对应```localhost:8082```端口。

- 计票员：在投票过程中，负责计票的角色。对应```localhost:8081```端口。
- 投票员：在投票过程中，负责投票的角色。对应```localhost:8080```端口。

### 发布者

发布者发布选举或表决事务，如图所示。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630192218005.png)

注意，候选项目和投票人数，固定为3和4，在后端已经固定写死的，如果后期有需要，可以再进一步的修改。

此时可以点击立即提交按钮。

点击后，会出现如下页面：

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630193007589.png)

保留住搜索码（后期会改为事务凭证），搜索码会作为查询投票事务的凭证。



### 计票员

#### 计票员注册

进入计票员页面，点击创建新用户，网页会以弹窗的形式返回一个注册码，根据这个注册码进入计票员角色，如下图所示。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630192759382.png)

注册码一般而言，每次生成都是不同的。

#### 计票员计票

待投票人投票完成且到截止时间之后，计票员开始计票。待三个计票员完成计票之后，分别进行计算中间变量，完成之后可以将结果上链，并可以在查看结果处进行查看。

**注意：必须要所有的计票员都完成计票之后，才可以计算中间变量。**



### 投票员

#### 投票员注册

第一步和计票员类似，创建四个投票员用户。

#### 投票员投票

进入角色后，界面如下图所示，填入之前保存的搜索码，进行投票

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630194536167.png)

进入界面，在倒计时之内完成投票操作。

![](https://img-for-md-1306026404.cos.ap-beijing.myqcloud.com/img/image-20210630194814767.png)

在投票结果上链之后，可以在查看结果处查看。



## 注意事项：

1. 必须保证ip地址一致。
2. 发布选举事务时，必须选择三个候选项和四个投票员。
3. 发布表决事务时，必须选择四个投票员进行表决。
4. 三个计票员的注册必须在投票员之前，否则无法生成联合公钥。
5. 四个投票员必须全部投票。
6. 三名计票员在计票的过程中，每个人都做完第一步也就是点击页面的开始计票的按钮之后，才能进行下一步，就是点击计算中间变量，负责计票失败。算法要求所致。
7. 每名计票员计票完成之后，必须确保自己的结果上链。