package com.sandeep.kattis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

/**
 * This is a solution for the Teque Problem from {@code Kattis}
 *
 * @see <a href="https://open.kattis.com/problems/teque"> Teque</a>
 * <Bold>This one has very huge test files. This will not be accepted without very very fast I/O</Bold>
 */
public class TequeDriver {

    ArrayCircularDequeue top = new ArrayCircularDequeue();
    ArrayCircularDequeue bottom = new ArrayCircularDequeue();

    public void addLast(int a) {
        bottom.addLast(a);
        updateLists();
    }

    public void addFirst(int a) {
        top.addLast(a);
        updateLists();
    }

    public void updateLists() {
        int half = size() / 2;

        if (top.size() > bottom.size()) {
            int count = half - bottom.size();
            for (int i = 0; i < count; i++) {
                int num = top.removeFirst();
                bottom.addFirst(num);
            }
        } else {
            int count = half - top.size() + (size() % 2 == 0 ? 0 : 1);
            for (int i = 0; i < count; i++) {
                int num = bottom.removeFirst();
                top.addFirst(num);
            }
        }
    }

    public void addMiddle(int a) {
        bottom.addFirst(a);
        updateLists();
    }

    public int size() {
        return top.size() + bottom.size();
    }

    public int get(int i) {

        if (i > top.size() - 1) {
            return bottom.get(i - top.size());
        } else {
            return top.get(top.size() - i - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput scan = new FastInput();
        FastOutput writer = new FastOutput();
        int ops = scan.scanInt();
        TequeDriver driver = new TequeDriver();
        for (int i = 0; i < ops; i++) {
            String op = scan.scanString();
            int num = scan.scanInt();
            if (op.equals("push_back")) {
                driver.addLast(num);
            } else if (op.equals("push_front")) {
                driver.addFirst(num);
            } else if (op.equals("push_middle")) {
                driver.addMiddle(num);
            } else {
                writer.println(driver.get(num));
            }
        }
        writer.close();
    }
}

class ArrayCircularDequeue {
    static final int MAX = 1000000;
    int arr[];
    int head;
    int tail;
    int size;

    public ArrayCircularDequeue() {
        arr = new int[MAX];
        head = -1;
        tail = 0;
        this.size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i) + ", ");
        }
        if (sb.length() > 2) {
            sb.deleteCharAt(sb.length() - 2);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");

        return sb.toString();
    }

    public int get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int resolvedIndex = head + index;

        if (resolvedIndex >= MAX) {
            resolvedIndex -= MAX;
        }

        return arr[resolvedIndex];
    }

    public int size() {
        return size;
    }

    public void addFirst(int num) {
        if (head == -1) {
            // likely the first element to add
            head = 0;
            arr[head] = num;
            size++;
            return;
        }

        int prevIndex = head - 1;
        if (prevIndex < 0) {
            prevIndex = MAX + prevIndex;
        }
        head = prevIndex;
        arr[head] = num;
        size++;
    }

    public void addLast(int num) {
        if (head == -1) {
            // likely the first element to add
            head = 0;
            arr[tail] = num;
            size++;
            return;
        }

        int nextIndex = tail + 1;
        tail = nextIndex;
        arr[tail] = num;
        size++;
    }


    public int removeFirst() {
        int resolvedIndex = head + 1;

        if (resolvedIndex >= MAX) {
            resolvedIndex -= MAX;
        }
        int deleted = arr[head];
        head = resolvedIndex;
        size--;
        return deleted;
    }

    public int removeLast() {
        int resolvedIndex = tail - 1;

        if (resolvedIndex < 0) {
            resolvedIndex = MAX + resolvedIndex;
        }
        int deleted = arr[tail];
        tail = resolvedIndex;
        size--;
        return deleted;
    }
}

/**
 * A Fast input class to read huge test files
 */
class FastInput {
    private byte[] buf = new byte[1024];
    private int index;
    private InputStream in;
    private int total;

    public FastInput() {
        in = System.in;
    }

    public int scan() throws IOException {
        if (total < 0)
            throw new InputMismatchException();
        if (index >= total) {
            index = 0;
            total = in.read(buf);
            if (total <= 0)
                return -1;
        }
        return buf[index++];
    }

    public int scanInt() throws IOException {
        int integer = 0;
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n)) {
            if (n >= '0' && n <= '9') {
                integer *= 10;
                integer += n - '0';
                n = scan();
            } else throw new InputMismatchException();
        }
        return neg * integer;
    }

    public double scanDouble() throws IOException {
        double doub = 0;
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n) && n != '.') {
            if (n >= '0' && n <= '9') {
                doub *= 10;
                doub += n - '0';
                n = scan();
            } else throw new InputMismatchException();
        }
        if (n == '.') {
            n = scan();
            double temp = 1;
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    temp /= 10;
                    doub += (n - '0') * temp;
                    n = scan();
                } else throw new InputMismatchException();
            }
        }
        return doub * neg;
    }

    public String scanString() throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = scan();
        while (isWhiteSpace(n))
            n = scan();
        while (!isWhiteSpace(n)) {
            sb.append((char) n);
            n = scan();
        }
        return sb.toString();
    }

    private boolean isWhiteSpace(int n) {
        if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
            return true;
        return false;
    }
}

/**
 * A Fast output class to write output for huge number of test cases
 */
class FastOutput {
    private final BufferedWriter bw;

    public FastOutput() {
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void print(Object object) throws IOException {
        bw.append("" + object);
    }

    public void println(Object object) throws IOException {
        print(object);
        bw.append("\n");
    }

    public void close() throws IOException {
        bw.close();
    }
}
