package ua.edu.ucu.utils;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private int size;
    private int capacity;
    private Object[] array;

    public ImmutableArrayList(int cap) {
        size = 0;
        capacity = cap;
        array = new Object[cap];
    }

    public ImmutableArrayList() {
        size = 0;
        capacity = 1;
        array = new Object[1];
    }

    public ImmutableArrayList(Object[] arr, int extracap) {
        size = arr.length;
        capacity = arr.length + extracap;
        array = new Object[capacity];
        System.arraycopy(arr, 0, array, 0, size);
    }

    public ImmutableArrayList(Object[] arr) {
        size = arr.length;
        capacity = arr.length + 1;
        array = new Object[capacity];
        System.arraycopy(arr, 0, array, 0, size);
    }



    private ImmutableArrayList getCopy() {
        return new ImmutableArrayList(this.array);
    }

    private ImmutableArrayList getExtendedCopy() {
        return new ImmutableArrayList(this.array, capacity * 2 + 1);
    }

    private boolean enoughSpaceFor(int numExtra) {
        return this.size + numExtra <= this.capacity;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return add(this.size, e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        ImmutableArrayList newArrayList;
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Object[] extra = new Object[]{e};
        return addAll(index, extra);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableArrayList newArrayList;
        if (!enoughSpaceFor(c.length)) {
            newArrayList = getExtendedCopy();
        } else {
            newArrayList = getCopy();
        }
        while (!newArrayList.enoughSpaceFor(c.length)) {
            newArrayList = newArrayList.getExtendedCopy();
        }

        int i = 0;
        while (i < c.length + size) {
            if (i < index) {
                newArrayList.array[i] = array[i];
            } else if (i < index + c.length) {// +1
                newArrayList.array[i] = c[i - index];
            } else {
                newArrayList.array[i] = array[i - c.length];
            }
            i++;
        }
        newArrayList.size = size + c.length;
        return newArrayList;

    }

    @Override
    public Object get(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableArrayList newArrayList = new ImmutableArrayList(this.capacity);
        for (int i = 0; i < size - 1; i++) {
            if (i < index) {
                newArrayList.array[i] = array[i];
            } else {
                newArrayList.array[i] = array[i + 1];
            }
        }
        newArrayList.size = size - 1;
        return newArrayList;
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableArrayList newArrayList = getCopy();
        newArrayList.array[index] = e;


        return newArrayList;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (array[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList(1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}