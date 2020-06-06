#### 序列化相关
* `transient Object[] elementData` 存放数据的数组默认不进行序列化
* `private void writeObject()` 用来自定义序列化规则
* `private void readObject()` 用来自定义反序列化规则
* 如果对象实现`Externalizable`接口,则可以自定义序列化和反序列化规则(优先级高于上面)
* 针对单例对象进行反序列化时会与原来对象不一致可通过在对象中添加`readResolve`方法 使用方式如下
```java

public class Singleton {
    
    private static volatile Singleton INSTANCE = null;
    
    private Singleton(){
    }
    
    public static Singleton getInstance(){
        if (INSTANCE == null){
            synchronized(Singleton.class){
                if(INSTANCE == null){
                    singleton = new Singleton();
                }
            }
        }
    }
    private Singleton readResolve(){
        return getInstance();
    }
}

```

> subList   子依赖主,子中任意增删改等操作均会和主的modCount作对比,因此对主进行增删改后对子的遍历都可能会抛异常
> 而对子进行增删改查操作时