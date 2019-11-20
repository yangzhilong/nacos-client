# nacos-client
### 1、add a namespace， copy Namespace ID

### 2、add data configurations in namespace
```
dataId:common.properties
goup:COMMON_GROUP
format:properties
content:
    common.name=lisi
```

```
dataId:nacos-client.properties
goup:TEST_GROUP
format:properties
content:
    config.name=wangwu
    config.age=40
```
#### sentinel config
```
dataId:nacos-client-sentinel
goup:DEFAULT_GROUP
format:JSON
content:
    [{
        "resource": "test",
        "count": 2,
        "grade": 1,
        "strategy": 0,
        "controlBehavior": 0
    }]
```

### 3、access url : http://localhost:10011/get
you can see result:
```
{"pro":"李四2","dto":"王五"}
```
If you have concurrent access or very fast continuous access, you see the following results:
```
{"error":"too busy"}
```
If have exception, you see the following results:
```
{"pro":"fallback","dto":"fallback"}
```