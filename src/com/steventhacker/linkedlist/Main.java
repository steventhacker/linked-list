package com.steventhacker.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		List<Integer> intList = new LinkedList<>();
		intList.add(0);
		intList.add(19);
		intList.add(1);
		intList.add(8);
		intList.add(19);
		intList.add(2);
		intList.add(133);
		intList.add(3);
		intList.add(900);
		intList.add(4);

		List<Integer> retainList = new ArrayList<>();
		retainList.add(0);
		retainList.add(1);
		retainList.add(900);

		intList.retainAll(retainList);

		for (Integer integer : intList) {
			System.out.println(integer);
		}
	}
}
