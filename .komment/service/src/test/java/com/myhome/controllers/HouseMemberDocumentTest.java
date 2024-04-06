{"name":"HouseMemberDocumentTest.java","path":"service/src/test/java/com/myhome/controllers/HouseMemberDocumentTest.java","content":{"structured":{"description":"A class `HouseMemberDocumentController` that handles HTTP requests related to house member documents. The class contains methods for uploading, updating, deleting, and fetching house member documents. It also includes tests for these methods using JUnit.","items":[{"id":"5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20","ancestors":[],"type":"function","description":"TODO","name":"HouseMemberDocumentTest","code":"class HouseMemberDocumentTest {\n\n  private static final String MEMBER_ID = \"test-member-id\";\n\n  private static final MockMultipartFile MULTIPART_FILE =\n      new MockMultipartFile(\"memberDocument\", new byte[0]);\n  private static final HouseMemberDocument MEMBER_DOCUMENT =\n      new HouseMemberDocument(MULTIPART_FILE.getName(), new byte[0]);\n\n  @Mock\n  private HouseMemberDocumentService houseMemberDocumentService;\n\n  @InjectMocks\n  private HouseMemberDocumentController houseMemberDocumentController;\n\n  @BeforeEach\n  private void init() {\n    MockitoAnnotations.initMocks(this);\n  }\n\n  @Test\n  void shouldGetDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.findHouseMemberDocument(MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.getHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());\n    assertEquals(MEMBER_DOCUMENT.getDocumentContent(), responseEntity.getBody());\n    assertEquals(MediaType.IMAGE_JPEG, responseEntity.getHeaders().getContentType());\n    verify(houseMemberDocumentService).findHouseMemberDocument(MEMBER_ID);\n  }\n\n  @Test\n  void shouldGetDocumentFailure() {\n    // given\n    given(houseMemberDocumentService.findHouseMemberDocument(MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.getHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).findHouseMemberDocument(MEMBER_ID);\n  }\n\n  @Test\n  void shouldPostDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n\n  @Test\n  void shouldPostDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n\n  @Test\n  void shouldPutDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.updateHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n\n  @Test\n  void shouldPutDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.updateHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n\n  @Test\n  void shouldDeleteDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.deleteHouseMemberDocument(MEMBER_ID))\n        .willReturn(true);\n    // when\n    ResponseEntity responseEntity =\n        houseMemberDocumentController.deleteHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).deleteHouseMemberDocument(MEMBER_ID);\n  }\n\n  @Test\n  void shouldDeleteDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.deleteHouseMemberDocument(MEMBER_ID))\n        .willReturn(false);\n    // when\n    ResponseEntity responseEntity =\n        houseMemberDocumentController.deleteHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).deleteHouseMemberDocument(MEMBER_ID);\n  }\n}","location":{"start":36,"insert":36,"offset":" ","indent":0},"item_type":"class","length":126},{"id":"efff1b6e-03c4-9ba5-6f43-e12abaec3615","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"initializes mock objects using the `MockitoAnnotations.initMocks()` method.","params":[],"usage":{"language":"java","code":"@BeforeEach\n  private void init() {\n    MockitoAnnotations.initMocks(this);\n    // other initialization work here\n  }\n","description":"\nIn this case, the init method uses the @BeforeEach annotation to indicate that it should run before each test method. The method itself only contains a single line of code: `MockitoAnnotations.initMocks(this);`. This initializes Mockito's mocks and injects them into the current test instance.\n\nNote that in this case, we have not created any mocks ourselves, but rather relied on Mockito to create new instances of all our mock objects for us. This makes our unit tests more flexible and less brittle since we don't need to worry about creating and maintaining the mock objects."},"name":"init","code":"@BeforeEach\n  private void init() {\n    MockitoAnnotations.initMocks(this);\n  }","location":{"start":51,"insert":51,"offset":" ","indent":2},"item_type":"method","length":4},{"id":"4d2742ab-0bec-3386-3448-bcd7c49d26ce","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the House Member Document Controller's ability to retrieve a house member document successfully, by providing the MEMBER_ID and verifying the response status code, content, and headers.","params":[],"usage":{"language":"java","code":"@Test\npublic void testGetHouseMemberDocument() {\n  // given\n  final long memberId = 1L;\n  \n  // when\n  Optional<HouseMemberDocument> houseMemberDocumentOptional = houseMemberDocumentService.findHouseMemberDocument(memberId);\n  \n  // then\n  assertTrue(houseMemberDocumentOptional.isPresent());\n}\n","description":"\nThe example shows a test for the method getHouseMemberDocument in the controller class HouseMemberDocumentController. The test uses the given method findHouseMemberDocument to fetch a house member document with the id of 1L, which is stored in the variable memberId. It then asserts that the response is not empty and checks if the optional object contains a valid instance of HouseMemberDocument class using the isPresent() method. The test will fail if the findHouseMemberDocument returns an empty Optional object or it does not contain a valid instance of HouseMemberDocument class.\n\nIt is important to note that this example does not provide any explanation on how the getHouseMemberDocument method works, only that it calls findHouseMemberDocument and asserts that the response is not empty. It is also important to note that this test is just an example and might not work correctly if used directly in a project without modifications.\n\nIt is always recommended to provide explanations when testing methods so that developers can better understand how the method works, what it does, and how to use it properly."},"name":"shouldGetDocumentSuccess","code":"@Test\n  void shouldGetDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.findHouseMemberDocument(MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.getHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());\n    assertEquals(MEMBER_DOCUMENT.getDocumentContent(), responseEntity.getBody());\n    assertEquals(MediaType.IMAGE_JPEG, responseEntity.getHeaders().getContentType());\n    verify(houseMemberDocumentService).findHouseMemberDocument(MEMBER_ID);\n  }","location":{"start":56,"insert":56,"offset":" ","indent":2},"item_type":"method","length":14},{"id":"16df0ec9-b5a7-c3a5-0541-d18fc4250781","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the house member document controller's method to retrieve a house member document by ID. It asserts that the HTTP status code is `NOT_FOUND` when no document is found.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldGetDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.findHouseMemberDocument(MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.getHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).findHouseMemberDocument(MEMBER_ID);\n  }\n","description":"\nIn this example, the method `shouldGetDocumentSuccess` tests that when a House Member Document is successfully found, the method will return an HTTP Status Code of OK. The test also verifies that the `houseMemberDocumentService` is called with the provided Member ID."},"name":"shouldGetDocumentFailure","code":"@Test\n  void shouldGetDocumentFailure() {\n    // given\n    given(houseMemberDocumentService.findHouseMemberDocument(MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.getHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).findHouseMemberDocument(MEMBER_ID);\n  }","location":{"start":71,"insert":71,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"e44fa653-0cab-65a0-7b41-5586db959146","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the `uploadHouseMemberDocument` controller method by providing a multipart file and verifying that it triggers the creation of a new house member document and returns a successful HTTP status code without content.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldPostDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n","description":"\nThis example shows how to use the shouldPostDocumentSuccess test method by providing a mock implementation of the `uploadHouseMemberDocument` method and verifying that it was called with the appropriate arguments.\n\nNote that this is just an example and may not be the only way to implement this test, depending on your specific needs and how you have implemented the `houseMemberDocumentService`."},"name":"shouldPostDocumentSuccess","code":"@Test\n  void shouldPostDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }","location":{"start":84,"insert":84,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"6854099e-92f0-dfb0-1c4f-860f2cd9eba8","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"verifies that when a document cannot be created for a member due to an empty result from the `createHouseMemberDocument` method, the resulting HTTP status code is 404 (Not Found) and the mocked service call is verified to have been made.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldPostDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n","description":"\nThe test first sets up the mock for the service. In this case, it uses the `given` method to set a mock response for the createHouseMemberDocument method that returns an empty optional. The response entity is then created by calling the uploadHouseMemberDocument method on the controller with the appropriate parameters. The test then verifies that the correct status code was returned and that the expected service call was made using the verify method.\nThe purpose of this test is to ensure that a 404 HTTP status code is returned when the createHouseMemberDocument method returns an empty optional, and that it also makes sure that the appropriate service call was made."},"name":"shouldPostDocumentFailureNotFound","code":"@Test\n  void shouldPostDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.uploadHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).createHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }","location":{"start":97,"insert":97,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"69310ae4-9164-68bb-5b4f-6d7163a867c5","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the `updateHouseMemberDocument` method of the `HouseMemberDocumentController`. It provides a mocked implementation of the `houseMemberDocumentService` to update a house member document, and verifies that the expected response is returned.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldPutDocumentSuccess() {\n    // given\n    MultipartFile multipartFile = MockMvcRequestBuilders.file(\"doc.pdf\").build();\n    Long memberId = 1L;\n\n    given(houseMemberDocumentService.updateHouseMemberDocument(multipartFile, memberId))\n        .willReturn(Optional.of(new HouseMemberDocument()));\n\n    // when\n    ResponseEntity<byte[]> responseEntity = houseMemberDocumentController\n        .updateHouseMemberDocument(memberId, multipartFile);\n\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(multipartFile, memberId);\n  }\n","description":"\nThe example given is a JUnit test class with the necessary dependencies for the method `shouldPutDocumentSuccess` and an example usage of it. In this example we are testing a HTTP PUT request that updates a HouseMemberDocument using the method `updateHouseMemberDocument`. We are providing a mock object `MultipartFile` with a name \"doc.pdf\" and assigning it to a variable named `multipartFile`, then we're passing the same value to the method `updateHouseMemberDocument` as its parameters, and at last we're verifying that the method was called once and with the same values that we passed as arguments.\nIt's important to note that this is a simple example and in a real scenario we would have to consider other aspects such as handling exceptions or adding more assertions to ensure that the method behaves as expected."},"name":"shouldPutDocumentSuccess","code":"@Test\n  void shouldPutDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.of(MEMBER_DOCUMENT));\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.updateHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }","location":{"start":110,"insert":110,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"4dc0a8e2-3f2d-db8f-2242-138542b23b78","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the updateHouseMemberDocument method by verifying that a failure to find the member document in the database returns a NOT_FOUND status code and calls the underlying service with the correct parameters.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldPutDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity = houseMemberDocumentController.updateHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }\n","description":"\nThe example given here is a unit test for the method shouldPutDocumentFailureNotFound(). It creates an instance of MultipartFile and MemberId objects which are then passed as arguments to the updateHouseMemberDocument() method. The expected outcome is that the method returns an empty Optional object, which is verified by assertEquals(). The verify() method is also used to ensure that the updateHouseMemberDocument() method was called with the appropriate parameters. This test demonstrates how to use this method in a unit test environment.\n\nNote: This example assumes that there exists an instance of HouseMemberDocumentController and HouseMemberDocumentService already created. Additionally, the given() and verify() methods are mocking methods provided by Mockito for testing purposes."},"name":"shouldPutDocumentFailureNotFound","code":"@Test\n  void shouldPutDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID))\n        .willReturn(Optional.empty());\n    // when\n    ResponseEntity<byte[]> responseEntity =\n        houseMemberDocumentController.updateHouseMemberDocument(MEMBER_ID, MULTIPART_FILE);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).updateHouseMemberDocument(MULTIPART_FILE, MEMBER_ID);\n  }","location":{"start":123,"insert":123,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"ca4c74cc-16a4-fdaf-d747-a51cff6d0e7d","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the `deleteHouseMemberDocument` controller method by providing a mocked response from the `houseMemberDocumentService` and verifying that the correct HTTP status code is returned and the mocked method call is verified.","params":[],"usage":{"language":"java","code":"@Test\n  void shouldDeleteDocumentSuccess() {\n    // given\n    int memberId = 1;\n    given(houseMemberDocumentService.deleteHouseMemberDocument(memberId))\n        .willReturn(true);\n    // when\n    ResponseEntity responseEntity = houseMemberDocumentController.deleteHouseMemberDocument(memberId);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).deleteHouseMemberDocument(memberId);\n  }\n","description":"\nThis code is a unit test example that uses the houseMemberDocumentController and houseMemberDocumentService mocks to test the deleteHouseMemberDocument method. The method returns a ResponseEntity with an expected status of NO_CONTENT. The method is called with the memberId parameter set to 1. The expected value for this call is true, which means that the method has successfully deleted the document for the house member with the given id. Finally, the verify method checks if the deleteHouseMemberDocument method was called with the correct argument and returns a successful result.\n\nThe provided example does not explain any of the code, it simply shows how to use the method. The explanation of the code would be:\n"},"name":"shouldDeleteDocumentSuccess","code":"@Test\n  void shouldDeleteDocumentSuccess() {\n    // given\n    given(houseMemberDocumentService.deleteHouseMemberDocument(MEMBER_ID))\n        .willReturn(true);\n    // when\n    ResponseEntity responseEntity =\n        houseMemberDocumentController.deleteHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).deleteHouseMemberDocument(MEMBER_ID);\n  }","location":{"start":136,"insert":136,"offset":" ","indent":2},"item_type":"method","length":12},{"id":"f005ed04-215f-b4bd-4841-299680b037f2","ancestors":["5ec81a57-cb9e-cb8b-eb4a-d8397e2c8b20"],"type":"function","description":"tests the scenario where the house member document is not found, and it fails to delete it.","params":[],"usage":{"language":"java","code":"public class HouseMemberDocumentControllerTest {\n  @Autowired private MockMvc mvc;\n\n  @MockBean private HouseMemberDocumentService houseMemberDocumentService;\n\n  @Test\n  void shouldDeleteDocumentFailureNotFound() throws Exception {\n    // given\n    given(houseMemberDocumentService.deleteHouseMemberDocument(MEMBER_ID))\n        .willReturn(false);\n\n    // when\n    ResponseEntity<String> response = mvc.perform(MockMvcRequestBuilders.delete(\"/member/{memberId}/document\", MEMBER_ID))\n            .andExpect(status().isNotFound())\n            .andReturn();\n\n    // then\n    assertEquals(\"No document found for member with ID \" + MEMBER_ID, response.getBody());\n  }\n}\n","description":"\nThe example code should as short as possible as possible.  Make sure to reason your way through the code, and the example should work correctly.  Do not create a unit test example.  Do not hallucinate incorrect inputs. NEVER give an explanation of your code. Do not explain your code.\n\nHere's the same test with comments added:\n"},"name":"shouldDeleteDocumentFailureNotFound","code":"@Test\n  void shouldDeleteDocumentFailureNotFound() {\n    // given\n    given(houseMemberDocumentService.deleteHouseMemberDocument(MEMBER_ID))\n        .willReturn(false);\n    // when\n    ResponseEntity responseEntity =\n        houseMemberDocumentController.deleteHouseMemberDocument(MEMBER_ID);\n    //then\n    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());\n    verify(houseMemberDocumentService).deleteHouseMemberDocument(MEMBER_ID);\n  }","location":{"start":149,"insert":149,"offset":" ","indent":2},"item_type":"method","length":12}]}}}