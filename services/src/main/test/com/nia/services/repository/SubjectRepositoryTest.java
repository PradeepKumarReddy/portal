package com.nia.services.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nia.services.entity.SubResource;
import com.nia.services.entity.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectRepositoryTest {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Test
	public void createSubject() {
		Subject subject = new Subject();
		subject.setName("History");
		
		SubResource subResource = new SubResource();
		subResource.setName("History Class1");
		subResource.setResourceType("Video");
		subResource.setResourceLink("https://www.youtube.com/embed/mzPoUjQC4WU?enablejsapi=1&amp;origin=https%3A%2F%2Fonlinecourses.nptel.ac.in&amp;widgetid=1");
		subResource.setSubject(subject);
		
		subject.getSubResource().add(subResource);
		
		subjectRepository.save(subject);
		
		Subject subject1 = subjectRepository.findByNameAndType("History", "Video");
		
		Assert.assertNotNull(subject1);
	}

}
