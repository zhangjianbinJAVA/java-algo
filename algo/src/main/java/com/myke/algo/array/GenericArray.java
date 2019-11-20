package com.myke.algo.array;

/**
 * GenericArray
 *
 * @Author: zhangjianbin
 * @Date: 2019/11/20 11:09
 */
public class GenericArray<T> {
    private T[] data;
    private int size;

    // 无参构造方法，默认数组容量为10
    public GenericArray() {
        this(10);
    }


    // 根据传入容量，构造Array
    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }


    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取当前元素个数
    public int count() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    // 修改 index 位置的元素
    public void set(int index, T e) {
        checkIndex(index);
        data[index] = e;
    }

    // 获取对应 index 位置的元素
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    // 查看数组是否包含元素e
    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 获取对应元素的下标, 未找到，返回 -1
    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 在 index 位置，插入元素 e, 时间复杂度 O(m+n)
    public void add(int index, T e) {
        checkIndexForAdd(index);
        // 如果当前元素个数等于数组容量，则将数组扩容为原来的2倍
        if (size == data.length) {
            resize(2 * data.length);
        }

        //如查插入的索引小于数组的最大索引，则将数组中的数据向后移
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }


    // 向数组头插入元素
    public void addFirst(T e) {
        add(0, e);
    }

    // 向数组尾插入元素
    public void addLast(T e) {
        add(size, e);
    }

    // 删除 index 位置的元素，并返回
    public T remove(int index) {
        checkIndex(index);

        T ret = data[index];
        //将删除位置后面的 数据向前移动，
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //最后一个索引设置为 空，因为删除了一个，数据向前移动了
        data[size] = null;

        // 缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    // 删除第一个元素
    public T removeFirst() {
        return remove(0);
    }

    // 删除末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除指定元素
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }


    // 扩容方法，时间复杂度 O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        //将老数据赋值到新数组中
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //检查索引是否合法
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Require index >=0 and index < size.");
        }
    }


    //检查添加数据时,索引是否会法
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(" Require index >=0 and index <= size.");
        }
    }


    public static void main(String[] args) {
        GenericArray<Integer> array = new GenericArray<>(20);

        array.add(0, 1);
        array.add(1, 60);
        array.add(2, 6);
        array.add(3, 67);

        System.out.println("数组中添加的值" + array);

        array.addFirst(15);
        array.addLast(60);
        System.out.println("数组修改值后" + array);


        System.out.println("数组中是否有60 :" + array.contains(60));
        System.out.println("数组中60的索引 :" + array.find(60));


        array.remove(0);
        System.out.println("移除索引0; 数组的值" + array);

        array.removeElement(6);
        System.out.println("移除数值6; 数组的值" + array);


        array.removeFirst();
        array.removeLast();
        System.out.println("移除数首和尾; 数组的值" + array);


        System.out.println("移除数组异常");
        array.remove(50);

    }


}
