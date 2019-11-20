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