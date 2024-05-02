package com.words.finder.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.words.finder.data.WordCount;

@Repository
public interface WordCountRepo extends CrudRepository<WordCount, Long>{

}