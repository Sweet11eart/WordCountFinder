package com.words.finder.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.words.finder.data.WordCount;
import com.words.finder.repo.WordCountRepo;

@Service
public class WordCountServiceImp implements WordCountService {

	@Autowired
	private WordCountRepo countRepo;

	@Override
	public List<WordCount> find(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		HashMap<String, Integer> countMap = new HashMap<>();
		while ((line = br.readLine()) != null) {
			if (line != null && line.contains(" ")) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (countMap.containsKey(word)) {
						countMap.put(word, countMap.get(word) + 1);
					} else {
						countMap.put(word, 1);
					}
				}
			}
		}
		List<WordCount> list = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
			WordCount count = new WordCount();
			count.setName(entry.getKey());
			count.setCount(entry.getValue());
			list.add(count);
		}
		List<WordCount> outlist = (List<WordCount>) countRepo.saveAll(list);
		return outlist;
	}

}