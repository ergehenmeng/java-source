package com.eghm.reference;

/**
 * @author 殿小二
 * @date 2021/6/17
 */
public class Apple {
    
    private String name;
    
    public Apple(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
    
    @Override
    public String toString() {
        return "Apple{" + "name='" + name + '\'' + '}';
    }
}
