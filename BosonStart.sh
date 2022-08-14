#!/bin/bash
echo -e "\033[33m 启动Boson运行环境! \033[0m"
echo -e "\033[32m 是否启动环境搭建脚本？[y/N] \033[0m"
read -r -t 5 input
case $input in
[yY])
  echo -e "\033[31m 是否删除旧环境？[y/N] \033[0m"
  read -r -t 5 input
  case $input in
  [yY])
    echo -e "\033[31m 卸载旧环境 \033[0m"
    if docker-compose down; then
        if sudo rm -rf ~/boson; then
              echo -e "\033[32m 旧环境删除成功! \033[0m"
              echo -e "\033[32m 创建新目录! \033[0m"
              mkdir -p ~/boson/mysql/source
              # mkdir -p ~/boson/filebeat
              # mkdir -p ~/boson/logstash
              echo -e "\033[32m 导入数据库数据! \033[0m"
              cp boson.sql ~/boson/mysql/source/
              # cp filebeat.yml ~/boson/filebeat/filebeat.yml
              # cp logstash.conf ~/boson/logstash/
            else
              echo -e "\033[31m 旧环境删除失败! \033[0m"
              exit 1
        fi
        else
          exit 1
    fi
    ;;
  esac
  echo -e "\033[32m 执行Docker-compose! \033[0m"
  if docker-compose up -d; then
      echo -e "\033[32m Boson环境搭建完成! \033[0m"
    else
      echo -e "\033[32m Boson环境搭建完成! \033[0m"
      exit 1
  fi
esac



