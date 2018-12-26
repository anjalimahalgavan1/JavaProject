package dataApp.test.controller;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import dataApp.Controller.SbPersonInfoController;
import dataApp.Model.SbPersonInfo;
import dataApp.Service.SbPersonInfoService;

public class SbPersonInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private SbPersonInfoService personInfoService;

	@InjectMocks
	private SbPersonInfoController personInfoController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(personInfoController).build();
	}

	@Test
	public void getAllUsersTest() throws Exception {
		List<SbPersonInfo> p = Arrays.asList(new SbPersonInfo("Daniel", "bose", 31),
				new SbPersonInfo("bill", "clinton", 91));

		when(personInfoService.getAll()).thenReturn(p);

		mockMvc.perform(get("/getAll")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].firstName", is("Daniel"))).andExpect(jsonPath("$[0].lastName", is("bose")))
				.andExpect(jsonPath("$[0].age", is(31))).andExpect(jsonPath("$[1].firstName", is("bill")))
				.andExpect(jsonPath("$[1].lastName", is("clinton"))).andExpect(jsonPath("$[1].age", is(91)));
		verify(personInfoService, times(1)).getAll();
		verifyNoMoreInteractions(personInfoService);

	}

	@Test
	// GET ("/getByUserFirstName")
	public void getUserByFirstNameTest() throws Exception {
		SbPersonInfo p1 = new SbPersonInfo();
		p1.setFirstName("james");
		p1.setLastName("william");
		p1.setAge(42);

		when(personInfoService.getByFirstName("james")).thenReturn(p1);

		String URI = "/getByUserFirstName";

		mockMvc.perform(get(URI).param("firstName", "james").contentType(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.lastName", is("william")))
				.andExpect(jsonPath("$.age", is(42)));
	}

	@Test
	// post("/createUser")
	public void CreateUserTest() throws Exception {

		SbPersonInfo mockPerson = new SbPersonInfo();
		mockPerson.setFirstName("Lily");
		mockPerson.setLastName("william");
		mockPerson.setAge(23);

		when(personInfoService.create(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(mockPerson);

		mockMvc.perform(post("/createUser").param("firstName", "Lily").param("lastName", "willaiam").param("age", "32")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	// put("/updateUser")
	public void updateTest() throws Exception {
		SbPersonInfo userInfo = new SbPersonInfo("Jia", "thomas", 23);

		when(personInfoService.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(userInfo);

		mockMvc.perform(put("/updateUser").param("firstName", "Reena").param("lastName", "willaiam").param("age", "37")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	// delete("/deleteUserByFirstName")
	public void deleteUserByFirstNameTest() throws Exception {
		SbPersonInfo user = new SbPersonInfo("Jia", "thomas", 23);
		mockMvc.perform(delete("/deleteUserByFirstName").param("firstName", "Lily")).andExpect(status().isOk());
	}

	@Test
	// delete("deleteAll")
	public void deleteAllUserTest() throws Exception {
		SbPersonInfo user = new SbPersonInfo("Jia", "thomas", 23);
		mockMvc.perform(delete("/deleteAll")).andExpect(status().isOk());
	}
}
