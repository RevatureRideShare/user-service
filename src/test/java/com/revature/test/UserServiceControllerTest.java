package com.revature.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.User;
import com.revature.controller.UserController;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class TrainingLocationControllerTest {
  @Mock
  UserService userService;

  @InjectMocks
  UserController trainingLocationController;

  private MockMvc mvc;

  private User newUser;

  private User existingUser;

  private User nullUser;

  private User badFormatUser;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {

  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {

  }

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mvc = MockMvcBuilders.standaloneSetup(trainingLocationController).build();

    newUser = new User(4, "NewUser@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    existingUser = new User(1, "bmoney@gmail.com", "Brian", "Money", "3309842776",
        User.RideStatus.ACTIVE, User.Role.RIDER, true, 0);
    nullUser = null;
    badFormatUser = new User(7, "bf1@gmail.com", "", "Money", "3309842776", User.RideStatus.ACTIVE,
        User.Role.RIDER, true, 0);
  }

  @AfterEach
  void tearDown() throws Exception {

  }

  @Test
  void testCreateNewUser() throws JsonProcessingException, Exception {
    when(userService.createUser(newUser)).thenReturn(newUser);
    mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(newUser)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(newUser)));
  }

  @Test
  void testCreateExistingUser() throws JsonProcessingException, Exception {
    when(userService.createUser(existingUser))
        .thenThrow(new DuplicateKeyException("Object already exists in database"));

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Object already exists in database");

    mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(existingUser))
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateNullUser() throws JsonProcessingException, Exception {
    when(userService.createUser(nullUser)).thenThrow(NullPointerException.class);

    Map<String, Object> error = new HashMap<>();
    error.put("message", "Cannot pass in a null TrainingLocation object");

    mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(nullUser))
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(error)));
  }

  @Test
  void testCreateBadFormatUser() throws JsonProcessingException, Exception {
    when(userService.createUser(badFormatUser)).thenThrow(ConstraintViolationException.class);

    mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(badFormatUser))
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
  }

  @Test
  void testGetAllUsers() throws Exception {
    List<User> existingList = new LinkedList<>();
    existingList.add(existingUser);

    when(userService.getAllUsers()).thenReturn(existingList);
    mvc.perform(MockMvcRequestBuilders.get("/user")).andExpect(status().isOk())
        .andExpect(content().json(new ObjectMapper().writeValueAsString(existingList)));
  }

}

