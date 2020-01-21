package com.mybatis.test;

import java.util.*;

public class StreamTest {


    private int size = 10000000;

    List<Integer> integers = null;

    public static void main(String[] args) {

        StreamTest test = new StreamTest();
        test.setup();
        test.streamMaxInteger();
        test.iteratorMaxInteger();
        test.forEachLoopMaxInteger();

        test.streamMaxInteger();
        test.iteratorMaxInteger();
        test.forEachLoopMaxInteger();
    }


    public void setup() {
        integers = new ArrayList<Integer>(size);
        populate(integers);
    }


    public void populate(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000000));
        }
    }

    public int iteratorMaxInteger() {
        Integer max = Integer.MIN_VALUE;
        long start = System.currentTimeMillis();

        for (Iterator<Integer> it = integers.iterator(); it.hasNext(); ) {
            max = Integer.max(max, it.next());
        }
        System.out.println("iteratorMaxInteger cost:" + (System.currentTimeMillis() - start));

        return max;
    }

    public int forEachLoopMaxInteger() {
        int max = Integer.MIN_VALUE;
        long start = System.currentTimeMillis();

        for (Integer n : integers) {
            max = Integer.max(max, n);
        }
        System.out.println("forEachLoopMaxInteger cost:" + (System.currentTimeMillis() - start));

        return max;
    }

    public int streamMaxInteger() {
        long start = System.currentTimeMillis();
        Wapper wapper = new Wapper();
        integers.forEach(e -> wapper.max = Integer.max(wapper.max, e));
        System.out.println("streamMaxInteger cost:" + (System.currentTimeMillis() - start));

        return wapper.max;
    }

    class Wapper {
        public int max = Integer.MIN_VALUE;
    }
}
