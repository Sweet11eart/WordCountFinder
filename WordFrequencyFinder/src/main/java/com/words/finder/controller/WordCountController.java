package com.words.finder.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.words.finder.data.WordCount;
import com.words.finder.service.WordCountService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@RestController
public class WordCountController {

	@Autowired
	private WordCountService countService;

	@RequestMapping(value = "/wordFinder", consumes = { "multipart/form-data" }, method = RequestMethod.POST)
	public ResponseEntity<List<WordCount>> wordCount(@RequestParam("file") MultipartFile file) {
		try {
			List<WordCount> list = countService.find(file.getInputStream());
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}
}