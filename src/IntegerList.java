import java.util.ArrayList;
import java.util.List;

public class IntegerList {
    protected int[] data;
    protected int size;
    private static final int DEFAULT_SIZE = 10;

    public IntegerList( ) {
        data = new int[DEFAULT_SIZE];
    }

    public IntegerList(IntegerList toCopy) {
        /**
         * copy the original
         */
        this.data = toCopy.data;
        this.size = toCopy.size;
    }
    /**
     * TODO:
     * Implement:
     * get(int index) – Return the integer stored at index
     * add(int value) – Add a new value to the integer list
     * clear() – Deletes all integers
     * setCapacity(int n) – Reallocates the data array increasing or
     decreasing its size to n
     * toArray() – Returns a copy of the integer list and returns it
     *
     */
    public int get(int index) {
        return data[index];
    }

    public synchronized void add(int value) {
        setCapacity(size+1);
        data[size-1] = value;
    }

    public synchronized void clear() {
        for(int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
    }

    public synchronized void setCapacity(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("List size must be greater than 0");
        }

        int[] newArray = new int[n];

        if(n >= size) {
            for(int i = 0; i < size; i++) {
                newArray[i] = data[i];
            }
        } else if(n < size) {
            for(int i = 0; i < n; i++) {
                newArray[i] = data[i];
            }
        }

        this.data = newArray;
        this.size = n;

    }

    public int[] toArray() {
        return data;
    }

}