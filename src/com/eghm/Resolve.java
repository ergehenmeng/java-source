package com.eghm;

public class Resolve<T> {

    private T item;

    public void say(Resolve<? super User> resolve){
    }


    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public static void main(String[] args) {
        Resolve<? super User> resolve = new Resolve<>();
        resolve.setItem(new User());
        
    }
}
