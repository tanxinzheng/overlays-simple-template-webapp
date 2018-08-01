# Web Application Simple

- run for Docker
```docker
docker run --name xmomen-app \
 -p 8080:8080 \
 xmomen-app:latest
```

# Requirement
- Spring boot
- Maven
- Vue

# Issue

- Fixed npm install chromedriver fail
npm install chromedriver --chromedriver_cdnurl=http://cdn.npm.taobao.org/dist/chromedriver

## JWT Token

- refresh token 并发请求问题 
1. 解决方案：多线程并发时，后端监听refresh接口，只运行一个线程执行，其他线程监听正在执行的线程结果，直接返回最新的token。