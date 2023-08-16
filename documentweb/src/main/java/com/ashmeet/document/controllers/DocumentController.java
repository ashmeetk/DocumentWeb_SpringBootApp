package com.ashmeet.document.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ashmeet.document.entities.Document;
import com.ashmeet.document.repos.DocumentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DocumentController {
	
	@Autowired
	private DocumentRepository repo;
	
	@RequestMapping("displayUpload")
	public String displayUpload(ModelMap modelMap)
	{
		returnAllDocs(modelMap);
		return "documentUpload";
	}

	private void returnAllDocs(ModelMap modelMap) {
		List<Document> documents = repo.findAll();
		modelMap.addAttribute("documents", documents);
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") Long id, ModelMap modelMap)
	{
		Document document = new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		repo.save(document);
		returnAllDocs(modelMap);
		return "documentUpload";
	}
	
	@RequestMapping("/download")
	public StreamingResponseBody downloadDocument(@RequestParam("id") long id, HttpServletResponse response)
	{
		Document document = repo.findById(id).get();
		byte[] data = document.getData();
		
		response.setHeader("Content-Disposition", "attachment;filename=" + document.getName());
		
		return outputStream -> { outputStream.write(data); };
	}
}
