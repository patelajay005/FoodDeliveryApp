package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class SequnceCollectionDemo {

    public static void main(String[] args) {

//        var list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        list.forEach(System.out::println);

        SequencedCollection sequencedCollection = new ArrayList<Integer>();
        sequencedCollection.add(1);
        sequencedCollection.add(2);
        sequencedCollection.add(3);
        sequencedCollection.add(4);
        sequencedCollection.addFirst(5);

        System.out.println(sequencedCollection.reversed());
    }
}
