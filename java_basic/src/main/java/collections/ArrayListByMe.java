package collections;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ArrayList实现
 *
 * @author sunxy
 * @date 2020-05
 */
public class ArrayListByMe<E> implements Serializable {

    private final Object[] EMPTY_ELEMENT_DATA = {};

    transient Object[] elementData;

    private int size;

    private static final long serialVersionUID = 1126243657350546428L;

    public ArrayListByMe() {
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    public ArrayListByMe(int initCapacity) {
        if (initCapacity > 0) {
            this.elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("非法输入:" + initCapacity);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elementData[i]);
        }
        return stringBuilder.toString();
    }

    public void add(E obj) {
        // 什么时候扩容
        if (size == elementData.length) {
            // 如何扩容
            elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
        }
        this.elementData[size++] = obj;
    }

    private void rangCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("out of index");
        }
    }

    public E get(int index) {
        rangCheck(index);
        return elementData(index);
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    public void set(int index, E obj) {
        rangCheck(index);
        elementData[index] = obj;
    }

    public void remove(int index) {
        rangCheck(index);
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    public void remove(E ele) {
        for (int i = 0; i < size; i++) {
            E e = get(i);
            if (e.equals(ele)) {
                // 移除该元素
                remove(i);
            }
        }
    }


}
