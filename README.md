<div align='center'>
     此处有一张炫酷的头图
     <br/>
     <sub> (请自行脑补，笑) </sub>
</div>
<h1 align='center'> Boson </h1>

<div align="center">
  <strong>波色子</strong>
  <sub><br>一个高性能的管理后端<br></sub>
</div>

<br>

<div align='center'>
  <a href = "LICENSE">
     <img src = "https://img.shields.io/github/license/cgglyle/boson.svg" alt = "LICENSE"/>
  </a>
  <a href = "forks">
     <img src = "https://img.shields.io/github/forks/cgglyle/boson.svg" alt = "forks"/>
  </a>
  <a href = "stars">
     <img src = "https://img.shields.io/github/stars/cgglyle/boson.svg" alt = "stars"/>
  </a>
  <a href = "watchers">
     <img src = "https://img.shields.io/github/watchers/cgglyle/boson.svg" alt = "watchers"/>
  </a>
</div>

<div align="center">
  <sub>此架构由
    <a href="https://github.com/cgglyle">cgglyle</a> 和
    <a href="https://github.com/cgglyle/boson/graphs/contributors">
      贡献者们
    </a>
    倾力 ❤︎ 打造</sub>
</div>

### 快速开发环境 
您只需要在源码库中下载`docker-compose.yaml`,`boson.sql`,`BosonStart.sh`三个文件。之后执行`BosonStart.sh`文件即可。

#### 注意
您需要注意在根目录中的`docker-compose.yml`文件中的配置。在项目跟目录中有`boson.sql`SQL文件，您需要将SQL文件移动至
`~/boson/mysql/source`目录中（如果没有需要自行创建`mkdir -p ~/boson/mysql/source`），在启动docker时，会自动将SQL语句执行，注入数据。

#### Windows用户注意！
启动脚本是为Linux系统设计的，如果您需要在Windows上启动项目，您需要更改一些配置。

如下：
```docker-compose
mysql:
    volumes:
      - ~/boson/mysql/mydir:/mydir
      - ~/boson/mysql/mysql:/var/lib/mysql
      # 数据库还原目录 可将需要还原的sql文件放在这里
      - ~/boson/mysql/source:/docker-entrypoint-initdb.d
mongo:
    volumes:
      - ~/boson/mongo/db:/data/db    # 挂载数据文件，根据实际路径修改 ：前的路径
      - ~/boson/mongo/log:/var/log/mongodb  # 挂载日志文件，根据实际路径修改 ：前的路径
      - ~/boson/mongo/config:/etc/mongo  # 挂载配置文件，根据实际路径修改 ：前的路径
```
您需要注意在Windows系统中不具备`~/xxx`目录，你需要手动更换挂载点地址。

如：`- D:/boson/mysql/mydir:/mydir` 你只需要将`:`前的地址换成您的本机挂载点地址。

特别的是，您需要将`boson.sql`文件移动至数据库还原目录，`- ~/boson/mysql/source:/docker-entrypoint-initdb.d`来初始化开发环境。

**在您更改本地挂载点之后，您需要手动启动项目，使用`docker-compose up -d`命令启动项目。**

在MacOS环境开发本项目时注意修改`application`中的`log4j2.xml`文件中的日志存放目录，以免出现无法创建日志的情况。

### 小提示
当前项目只有后端，前端请前往[Fermion](https://github.com/cgglyle/fermion)。
这是一个专门为Boson开发的前端项目，波色子费米子哥俩好。

### 维护者
[@cgglyle](https://github.com/cgglyle)

### 贡献
随意提交[Issues](https://github.com/cgglyle/boson/issues/new)或[Pull requests](https://github.com/cgglyle/boson/pulls)  
***请贡献者开发时尽量保证系统的弹性，以保证后续开发的便捷，以及维护的便利***

### 贡献者

### 鸣谢
<img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.png" width = "300" height = "300" alt="JetBrains Logo (Main) logo.">

在此特别感谢[JetBrains](https://www.jetbrains.com)对开源事业做出的贡献，您可以在[JetBranins开源支持](https://jb.gg/OpenSourceSupport)中查看到更多信息。

### License
[GPL v3](https://www.gnu.org/licenses/gpl-3.0.html) © cgglyle

### 项目增长幅度
![](https://starchart.cc/cgglyle/boson.svg)