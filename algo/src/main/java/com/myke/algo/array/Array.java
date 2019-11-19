package com.myke.algo.array;

/**
 * Array
 *
 * @Author: zhangjianbin
 * @Date: 2019/11/19 12:57
 */
public class Array {
    //定义整型数据data保存数据
    public int data[];
    //定义数组长度
    private int n;
    //定义中实际个数
    private int count;

    //构造方法，定义数组大小
    public Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        //一开始一个数都没有存所以为0
        this.count = 0;
    }

    //根据索引，找到数据中的元素并返回
    public int find(int index) {
        //校验索引是否合法
        if (index < 0 || index >= count) {
            return -1;
        }
        return data[index];
    }

    //插入元素
    public boolean insert(int index, int value) {

        //数组中无元素
        if (index == count && count == 0) {
            data[index] = value;
            ++count;
            return true;
        }

        // 数组空间已满
        if (count == n) {
            System.out.println("没有可插入的位置");
            return false;
        }
        // 如果count还没满，那么就可以插入数据到数组中
        // 位置不合法
        if (index < 0 || index > count) {
            System.out.println("位置不合法");
            return false;
        }

        // 位置合法: 如果插入的索引位置 小于 count 的个数时，将数据中的值统一向后移一位
        for (int i = count; i > index; --i) {
            //例如： i 为 1 时，将 索引 0 位置的值 向后移动，赋值到 索引 1 上
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;

        System.out.println("数据插入后：=======>>");
        printAll();

        return true;

    }

    //根据索引，删除数组中元素
    public boolean delete(int index) {
        if (index < 0 || index >= count) {
            return false;
        }
        //从删除位置开始，将后面的元素向前移动一位
        for (int i = index + 1; i < count; ++i) {
            data[i - 1] = data[i];
        }
        --count;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(6);
        //array.printAll();

        //相同索引插入数据时，后面的会索引值会插入当前索引值的前面
        array.insert(0, 3);
        array.insert(0, 4);

        array.insert(1, 5);


        array.insert(3, 9);
        array.insert(3, 10);
        array.insert(3, 11);

        //没有可插入的位置，改数据插入不到 数组中
        array.insert(4, 12);

        //array.printAll();
    }

}
