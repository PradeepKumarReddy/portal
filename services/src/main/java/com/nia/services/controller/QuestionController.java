package com.nia.services.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nia.services.entity.Question;
import com.nia.services.entity.QuestionOption;
import com.nia.services.excel.ExcelReader;
import com.nia.services.model.FileUpload;
import com.nia.services.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
public class QuestionController {

	private static final Logger logger = LogManager.getLogger(QuestionController.class);

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://temp//";

	@Autowired
	private QuestionRepository repo;

	@PostMapping("/question/add")
	public Question addQuestion(@RequestBody Question question) {
		/*
		 * System.out.println(question);
		 * 
		 * for(QuestionOption option : question.getOptions()) {
		 * System.out.println(option.toString()); }
		 */

		Question question2 = new Question();
		question2.setQuestionDesc(question.getQuestionDesc());

		for (QuestionOption option2 : question.getOptions()) {
			QuestionOption questionOption = new QuestionOption();
			questionOption.setAnswer(option2.isAnswer());
			questionOption.setOptionDesc(option2.getOptionDesc());
			questionOption.setQuestion(question2);
			question2.getOptions().add(questionOption);
		}

		return repo.save(question2);
	}

	@GetMapping("/allQuestions")
	public List<Question> getAllQuestionsNotMappedToExam() {
		return repo.getAllQuestionsNotMappedToExam();
	}

	@PostMapping(path="/questions/upload")
	public FileUpload uploadFile(@RequestParam("file") MultipartFile uploadfile) {
		logger.debug("Single file upload!");
		FileUpload fileUpload = new FileUpload();
		fileUpload.setSuccess(false);
		if (uploadfile.isEmpty()) {
			fileUpload.setMessage("please select a file!");
			return fileUpload;
		}
		try {
			readUploadedFilesToQuestions(Arrays.asList(uploadfile));
			fileUpload.setSuccess(true);
			fileUpload.setMessage("Successfully uploaded - " + uploadfile.getOriginalFilename());
		} catch (Exception e) {
			fileUpload.setMessage("Bad Request");
		}

		return fileUpload;
	}
	
	//@PostMapping(path="/questions/upload", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> uploadFile_old(@RequestParam("file") MultipartFile uploadfile) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		try {
			saveUploadedFiles(Arrays.asList(uploadfile));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded 1- " + uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}
	
	@Autowired
	ExcelReader excelReader;

	// save file
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException, InvalidFormatException {

		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue; // next pls
			}
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			excelReader.convertExcelToEntity(file.getInputStream());
		}
	}
	
	// save file
	private void readUploadedFilesToQuestions(List<MultipartFile> files) throws IOException, InvalidFormatException {
		List<Question> questions = new ArrayList<>();
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue; // next pls
			}
			questions.addAll(excelReader.convertExcelToEntity(file.getInputStream()));
		}
		
		repo.saveAll(questions);
	}
}
