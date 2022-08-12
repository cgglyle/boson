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

在项目根目录执行`docker-compose up -d`来启动服务所需的数据库环境。  

#### 注意
您需要注意在根目录中的`docker-compose.yml`文件中的配置。在项目跟目录中有`boson.sql`SQL文件，你需要将SQL文件移动至
`~/boson/mysql/source`目录中（如果没有需要自行创建`mkdir -p ~/boson/mysql/source`），在启动docker时，会自动将SQL语句执行，注入数据。
