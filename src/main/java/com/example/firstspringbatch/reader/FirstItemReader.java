package com.example.firstspringbatch.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer> {

	List<Integer> list = Arrays.asList(1,2,3,4,5,6);
	int i=0;

	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside Item Reader");
		Integer item;
		if(i< list.size()) {
			item = list.get(i);
			i++;
			
			return item;
		}
		i=0; //will not be required if we are running once and execution stops
		return null; // to decide what to return based on input for eg Integer here
	}
	
}