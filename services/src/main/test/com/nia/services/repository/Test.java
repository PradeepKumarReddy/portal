package com.nia.services.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	
	public static void main(String[] str) {
		List<Long> quesCorrectOpts1 = new ArrayList<>();
		quesCorrectOpts1.add(1L);
		quesCorrectOpts1.add(2L);
		
		List<Long> userOps1 = new ArrayList<>();
		userOps1.add(1L);
		
		System.out.println("Case 1" + quesCorrectOpts1.equals(userOps1));
		
		List<Long> quesCorrectOpts2 = new ArrayList<>();
		quesCorrectOpts2.add(1L);
		quesCorrectOpts2.add(2L);
		
		List<Long> userOps2 = new ArrayList<>();
		userOps2.add(1L);
		userOps2.add(2L);
		
		System.out.println("Case 2" + quesCorrectOpts2.equals(userOps2));
		
		List<Long> quesCorrectOpts3 = new ArrayList<>();
		quesCorrectOpts3.add(1L);
		
		List<Long> userOps3 = new ArrayList<>();
		userOps3.add(1L);
		userOps3.add(2L);
		
		System.out.println("Case 3" + quesCorrectOpts3.equals(userOps3));
		
		
				
	}

}
