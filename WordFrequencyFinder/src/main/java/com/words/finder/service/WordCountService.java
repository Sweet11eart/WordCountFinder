package com.words.finder.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.words.finder.data.WordCount;

public interface WordCountService {
	public List<WordCount> find(InputStream is) throws IOException;
}