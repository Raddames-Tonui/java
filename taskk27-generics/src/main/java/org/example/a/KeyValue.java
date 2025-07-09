package org.example.a;

public class KeyValue<K, V> {
    private K key;
    private V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {return key;};
    public V getValue() {return value;};

    public void setKey(K key) {this.key = key;};
    public void setValue(V value) {this.value = value;};

    public void display(){
        System.out.println("Key "+ key + "(" + key.getClass().getSimpleName() + ")" );
        System.out.println("Value " + value + "(" + value.getClass().getSimpleName() + ")");

    }
}
