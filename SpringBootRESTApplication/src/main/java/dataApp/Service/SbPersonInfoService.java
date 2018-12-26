package dataApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dataApp.Model.SbPersonInfo;
import dataApp.Repository.SbPersonInfoRepository;

@Service
public class SbPersonInfoService {

	@Autowired
	private SbPersonInfoRepository personRepository;

	// Create records
	public SbPersonInfo create(String firstName, String lastName, int age) {
		return personRepository.save(new SbPersonInfo(firstName, lastName, age));
	}

	// Retrieve all records
	public List<SbPersonInfo> getAll() {
		return personRepository.findAll();
	}

	// fetch data by firstName
	public SbPersonInfo getByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}

	// update by firstname
	public SbPersonInfo update(String firstName, String lastName, int age) {
		SbPersonInfo p = personRepository.findByFirstName(firstName);
		p.setLastName(lastName);
		p.setAge(age);
		return personRepository.save(p);
	}

	// delete
	public void deleteAll() {
		personRepository.deleteAll();
	}

	// delete by first name
	public void delete(String firstName) {
		SbPersonInfo p = personRepository.findByFirstName(firstName);
		personRepository.delete(p);
	}
}
