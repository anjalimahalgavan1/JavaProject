package dataApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataApp.Model.SbPersonInfo;
import dataApp.Service.SbPersonInfoService;

@RestController
public class SbPersonInfoController {

	@Autowired
	private SbPersonInfoService personService;

	@PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		SbPersonInfo p = personService.create(firstName, lastName, age);
		return p.toString();
	}

	@GetMapping(value = "/getByUserFirstName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SbPersonInfo getPersonInfo(@RequestParam String firstName) {
		return personService.getByFirstName(firstName);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SbPersonInfo> getAll() {
		return personService.getAll();
	}

	@PutMapping(value = "/updateUser", headers = "Accept=application/json")
	public String update(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		SbPersonInfo p = personService.update(firstName, lastName, age);
		return p.toString();
	}

	@DeleteMapping(value = "/deleteUserByFirstName", headers = "Accept=application/json")
	public String delete(@RequestParam String firstName) {
		personService.delete(firstName);
		return "Deleted user information" + firstName;
	}

	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		personService.deleteAll();
		return "Deleted all records";
	}
}
