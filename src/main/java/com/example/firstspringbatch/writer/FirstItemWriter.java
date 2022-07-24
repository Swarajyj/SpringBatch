package com.example.firstspringbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Long> {

	@Override
	public void write(List<? extends Long> items) throws Exception {
		//Here we wont get item on by one
		//Here param is list of items , size will be based on chunk size
		// TODO Auto-generated method stub
		
		System.out.println("Inside Item writer");
		items.stream().forEach(System.out::println);
		
		
	}

}
