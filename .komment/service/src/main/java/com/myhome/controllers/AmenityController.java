{"name":"AmenityController.java","path":"service/src/main/java/com/myhome/controllers/AmenityController.java","content":{"structured":{"description":"A REST controller class `AmenityController` that implements the `AmenitiesApi` interface. The controller handles various HTTP requests related to amenities, including listing all amenities for a community, adding an amenity to a community, deleting an amenity, and updating an amenity. The code uses Spring Boot packages such as `@RestController`, `@Slf4j`, `@RequiredArgsConstructor`, `AmenityService`, `AmenityApiMapper`, and `ResponseEntity`.","image":"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<!-- Generated by graphviz version 2.43.0 (0)\n -->\n<!-- Title: com.myhome.controllers.AmenityController Pages: 1 -->\n<svg width=\"191pt\" height=\"93pt\"\n viewBox=\"0.00 0.00 191.00 93.00\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n<g id=\"graph0\" class=\"graph\" transform=\"scale(1 1) rotate(0) translate(4 89)\">\n<title>com.myhome.controllers.AmenityController</title>\n<!-- Node1 -->\n<g id=\"Node000001\" class=\"node\">\n<title>Node1</title>\n<g id=\"a_Node000001\"><a xlink:title=\" \">\n<polygon fill=\"#999999\" stroke=\"#666666\" points=\"183,-30 0,-30 0,0 183,0 183,-30\"/>\n<text text-anchor=\"start\" x=\"8\" y=\"-18\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.controllers.Amenity</text>\n<text text-anchor=\"middle\" x=\"91.5\" y=\"-7\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">Controller</text>\n</a>\n</g>\n</g>\n<!-- Node2 -->\n<g id=\"Node000002\" class=\"node\">\n<title>Node2</title>\n<g id=\"a_Node000002\"><a xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"132.5,-85 50.5,-85 50.5,-66 132.5,-66 132.5,-85\"/>\n<text text-anchor=\"middle\" x=\"91.5\" y=\"-73\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">AmenitiesApi</text>\n</a>\n</g>\n</g>\n<!-- Node2&#45;&gt;Node1 -->\n<g id=\"edge1_Node000001_Node000002\" class=\"edge\">\n<title>Node2&#45;&gt;Node1</title>\n<g id=\"a_edge1_Node000001_Node000002\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M91.5,-55.65C91.5,-47.36 91.5,-37.78 91.5,-30.11\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"88,-55.87 91.5,-65.87 95,-55.87 88,-55.87\"/>\n</a>\n</g>\n</g>\n</g>\n</svg>\n","items":[{"id":"48dcd7a5-9a45-f298-ea4e-d1d4d41fc953","ancestors":[],"type":"function","description":"TODO","name":"AmenityController","code":"@RestController\n@Slf4j\n@RequiredArgsConstructor\npublic class AmenityController implements AmenitiesApi {\n\n  private final AmenityService amenitySDJpaService;\n  private final AmenityApiMapper amenityApiMapper;\n\n  @Override\n  public ResponseEntity<GetAmenityDetailsResponse> getAmenityDetails(\n      @PathVariable String amenityId) {\n    return amenitySDJpaService.getAmenityDetails(amenityId)\n        .map(amenityApiMapper::amenityToAmenityDetailsResponse)\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());\n  }\n\n  @Override\n  public ResponseEntity<Set<GetAmenityDetailsResponse>> listAllAmenities(\n      @PathVariable String communityId) {\n    Set<Amenity> amenities = amenitySDJpaService.listAllAmenities(communityId);\n    Set<GetAmenityDetailsResponse> response =\n        amenityApiMapper.amenitiesSetToAmenityDetailsResponseSet(amenities);\n    return ResponseEntity.ok(response);\n  }\n\n  @Override\n  public ResponseEntity<AddAmenityResponse> addAmenityToCommunity(\n      @PathVariable String communityId,\n      @RequestBody AddAmenityRequest request) {\n    return amenitySDJpaService.createAmenities(request.getAmenities(), communityId)\n        .map(amenityList -> new AddAmenityResponse().amenities(amenityList))\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.notFound().build());\n  }\n\n  @Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n\n  @Override\n  public ResponseEntity<Void> updateAmenity(@PathVariable String amenityId,\n      @Valid @RequestBody UpdateAmenityRequest request) {\n    AmenityDto amenityDto = amenityApiMapper.updateAmenityRequestToAmenityDto(request);\n    amenityDto.setAmenityId(amenityId);\n    boolean isUpdated = amenitySDJpaService.updateAmenity(amenityDto);\n    if (isUpdated) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n}","location":{"start":38,"insert":38,"offset":" ","indent":0},"item_type":"class","length":59},{"id":"67ab9c9d-f3d8-6592-124d-f02d8ead350a","ancestors":["48dcd7a5-9a45-f298-ea4e-d1d4d41fc953"],"type":"function","description":"retrieves amenity details from the database using `amenitySDJpaService`. If the amenity is found, it returns an `ResponseEntity` with a status code of `OK` and the converted `AmenityDetailsResponse`. Otherwise, it returns an `ResponseEntity` with a status code of `NOT_FOUND`.","params":[{"name":"amenityId","type_name":"String","description":"unique identifier of an amenity that is being requested by the user.\n\n* `amenitySDJpaService`: A service for accessing amenity data from a Java Persistence API (JPA) database.\n* `amenityId`: The primary key of an amenity in the JPA database, which identifies a specific amenity record.\n* `amenityApiMapper`: A mapper class that maps the JPA entity to a `GetAmenityDetailsResponse` object for API consumption.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a `ResponseEntity` object representing the amenity details or a `HttpStatus.NOT_FOUND` status code if the amenity cannot be found.\n\n* `ResponseEntity`: This is the type of the output return from the function, which indicates whether the operation was successful or not.\n* `<GetAmenityDetailsResponse>`: This is the class that represents the response entity, which contains information about the amenity details.\n* `amenityId`: This is the parameter passed to the function, representing the unique identifier of the amenity for which details are being requested.\n* `amenitySDJpaService`: This is a Java interface that provides methods for interacting with the amenity data store.\n* `amenityApiMapper`: This is a Java class that maps the amenity data from the data store to the response entity.\n* `map(Function<ResponseEntity, GetAmenityDetailsResponse> mapper)`: This line calls the `map` method on the `ResponseEntity` object, passing in a lambda function that maps the `ResponseEntity` object to a `GetAmenityDetailsResponse` object. The `map` method is used to transform the output of the function into the desired response entity format.\n* `orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());`: This line provides an alternative outcome for the function, where if the `getAmenityDetails` method fails, it returns a response entity with a status code of `HttpStatus.NOT_FOUND`.","complex_type":true},"usage":{"language":"java","code":"@Override\npublic ResponseEntity<GetAmenityDetailsResponse> getAmenityDetails(String amenityId) {\n    return this.amenitySDJpaService.getAmenityDetails(amenityId)\n        .map(this.amenityApiMapper::amenityToAmenityDetailsResponse)\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());\n}\n","description":""},"name":"getAmenityDetails","code":"@Override\n  public ResponseEntity<GetAmenityDetailsResponse> getAmenityDetails(\n      @PathVariable String amenityId) {\n    return amenitySDJpaService.getAmenityDetails(amenityId)\n        .map(amenityApiMapper::amenityToAmenityDetailsResponse)\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());\n  }","location":{"start":46,"insert":46,"offset":" ","indent":2},"item_type":"method","length":8},{"id":"fdb5d6d8-0f1e-b4b4-e149-465bf59c68f9","ancestors":["48dcd7a5-9a45-f298-ea4e-d1d4d41fc953"],"type":"function","description":"retrieves a set of amenities from the database using `JpaService`, maps them to `GetAmenityDetailsResponse` objects using `ApiMapper`, and returns a response entity with the mapped amenities.","params":[{"name":"communityId","type_name":"String","description":"ID of the community for which the amenities are to be listed.\n\n* `communityId`: This parameter represents a unique identifier for a community. It is a string variable that contains the ID of the community.\n\nThe `listAllAmenities` function retrieves all amenities associated with a particular community using the `amenitySDJpaService`. The retrieved amenities are then transformed into a set of `GetAmenityDetailsResponse` objects through the `amenityApiMapper`. Finally, the function returns an `ResponseEntity` object with the converted set of `GetAmenityDetailsResponse` objects.","complex_type":true}],"returns":{"type_name":"Set","description":"a set of `GetAmenityDetailsResponse` objects containing information about the amenities for a given community.\n\n* `ResponseEntity<Set<GetAmenityDetailsResponse>>`: This is an entity that contains a set of `GetAmenityDetailsResponse` objects in its body.\n* `Set<GetAmenityDetailsResponse>`: This is a set of objects that contain the details of each amenity, such as name, description, and images.\n* `amenitySDJpaService.listAllAmenities(communityId)`: This method returns a set of `Amenity` objects, which are used to populate the `GetAmenityDetailsResponse` objects in the output set.\n* `amenityApiMapper.amenitiesSetToAmenityDetailsResponseSet(amenities)`: This method is responsible for mapping the `Amenity` objects to `GetAmenityDetailsResponse` objects, which are then added to the output set.","complex_type":true},"usage":{"language":"java","code":"@PathVariable String communityId = \"communityId\";\nSet<GetAmenityDetailsResponse> response = controller.listAllAmenities(communityId);\nreturn ResponseEntity.ok(response);\n","description":""},"name":"listAllAmenities","code":"@Override\n  public ResponseEntity<Set<GetAmenityDetailsResponse>> listAllAmenities(\n      @PathVariable String communityId) {\n    Set<Amenity> amenities = amenitySDJpaService.listAllAmenities(communityId);\n    Set<GetAmenityDetailsResponse> response =\n        amenityApiMapper.amenitiesSetToAmenityDetailsResponseSet(amenities);\n    return ResponseEntity.ok(response);\n  }","location":{"start":55,"insert":55,"offset":" ","indent":2},"item_type":"method","length":8},{"id":"e0d61791-899f-56a5-1c40-521cb955822d","ancestors":["48dcd7a5-9a45-f298-ea4e-d1d4d41fc953"],"type":"function","description":"adds amenities to a community by calling the `createAmenities` method of the `amenitySDJpaService` and returning an `AddAmenityResponse` object containing the added amenities.","params":[{"name":"communityId","type_name":"String","description":"ID of the community to which the amenities will be added.\n\n* `communityId`: A string representing the ID of the community to which amenities will be added.\n* `@PathVariable`: An annotation indicating that the value of the `communityId` field is passed from the URL path as a String.","complex_type":true},{"name":"request","type_name":"AddAmenityRequest","description":"AddAmenityRequest object containing the amenities to be added to the community, which is used by the `amenitySDJpaService` to create the new amenities in the database.\n\n* `communityId`: The ID of the community to which the amenities will be added.\n* `request.getAmenities()`: An array of `AddAmenityRequest.Amenity` objects containing the amenities to be added to the community.\n* `request.getAmenities().size()`: The number of amenities in the array.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a `ResponseEntity` object with an `ok` status and a list of created amenities.\n\n* `ResponseEntity<AddAmenityResponse>`: This is the type of the output returned by the function, which is an entity containing a `AddAmenityResponse` object.\n* `AddAmenityResponse`: This is a class that represents the response to the add amenity request, containing an array of `Amenity` objects representing the newly created amenities.\n* `amenitySDJpaService.createAmenities(request.getAmenities(), communityId)`: This is a call to the `amenitySDJpaService` method that creates a new amenity for the given community ID using the request's `Amenity` objects.\n* `.map(amenityList -> new AddAmenityResponse().amenities(amenityList))` : This line maps the `amenityList` to a new `AddAmenityResponse` object, setting the `amenities` property to the list of newly created amenities.\n* `.map(ResponseEntity::ok)`: This line maps the result of the previous mapping to an `Ok` response entity, indicating that the add amenity request was successful.\n* `.orElse(ResponseEntity.notFound().build())`: This line provides an alternative outcome in case the add amenity request failed, returning a `NotFound` response entity instead.","complex_type":true},"usage":{"language":"java","code":"@Test\npublic void testAddAmenityToCommunity() {\n    String communityId = \"1234\"; // Some valid community ID\n    AddAmenityRequest request = new AddAmenityRequest();\n    request.setAmenities(List.of(\"amenity\"));\n    \n    ResponseEntity<AddAmenityResponse> response = this.addAmenityToCommunity(communityId, request);\n    \n    assertEquals(HttpStatus.OK, response.getStatusCode());\n}\n","description":"\nIn the example above, we are testing the addAmenityToCommunity method with a valid community ID and an AddAmenityRequest object containing a list of amenities. We are asserting that the status code of the ResponseEntity returned by this method is 200 (OK)."},"name":"addAmenityToCommunity","code":"@Override\n  public ResponseEntity<AddAmenityResponse> addAmenityToCommunity(\n      @PathVariable String communityId,\n      @RequestBody AddAmenityRequest request) {\n    return amenitySDJpaService.createAmenities(request.getAmenities(), communityId)\n        .map(amenityList -> new AddAmenityResponse().amenities(amenityList))\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.notFound().build());\n  }","location":{"start":64,"insert":64,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"27a3d100-59e6-3892-d14f-223fe8bcb034","ancestors":["48dcd7a5-9a45-f298-ea4e-d1d4d41fc953"],"type":"function","description":"deletes an amenity from the database based on its ID, returning a HTTP status code indicating whether the operation was successful or not.","params":[{"name":"amenityId","type_name":"String","description":"identifier of an amenity to be deleted.\n\n* `amenityId`: This is a string input parameter that represents the unique identifier for an amenity in the system.\n* `amenitySDJpaService`: This is an instance of `AmenitySDJpaService`, which is a class that provides methods for interacting with the amenity data stored in the database.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a `ResponseEntity` with a status code of either `NO_CONTENT` or `NOT_FOUND`, depending on whether the amenity was successfully deleted.\n\n* `HttpStatus.NO_CONTENT`: This status code indicates that the requested resource has been successfully deleted and no content was returned in the response.\n* `HttpStatus.NOT_FOUND`: This status code indicates that the requested amenity could not be found, which means it may have been deleted or it may never have existed in the first place.","complex_type":true},"usage":{"language":"java","code":"@Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n}\n","description":"\nThis method takes the amenityId as a parameter in the PathVariable annotation, and then calls deleteAmenity(String amenityId) from the AmenityService class. The response is returned as a ResponseEntity object that includes either an HttpStatus of NO_CONTENT or NOT_FOUND depending on whether the amenity was successfully deleted."},"name":"deleteAmenity","code":"@Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }","location":{"start":74,"insert":74,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"2b7ab2a8-0e59-0d8f-e64a-a266722651e8","ancestors":["48dcd7a5-9a45-f298-ea4e-d1d4d41fc953"],"type":"function","description":"updates an amenity in the database based on a request body containing the amenity details. If the update is successful, it returns a `ResponseEntity` with a `HttpStatus.NO_CONTENT`. Otherwise, it returns a `ResponseEntity` with a `HttpStatus.NOT_FOUND`.","params":[{"name":"amenityId","type_name":"String","description":"ID of the amenity being updated.\n\n* `amenityId`: The unique identifier for an amenity.\n\nThe function updates the amenity information in the database using the `amenitySDJpaService`. If the update is successful, a `ResponseEntity` with a status code of `NO_CONTENT` is returned. Otherwise, a `ResponseEntity` with a status code of `NOT_FOUND` is returned.","complex_type":true},{"name":"request","type_name":"UpdateAmenityRequest","description":"`UpdateAmenityRequest` object containing the details of the amenity to be updated, which is then converted into an `AmenityDto` object by the `amenityApiMapper` and used for updating the amenity in the database.\n\n* `@Valid`: This annotation indicates that the `request` object is validated by the framework before it is processed further.\n* `@RequestBody`: This annotation specifies that the `request` object should be serialized and sent as the request body in the HTTP request.\n* `UpdateAmenityRequest`: This class represents the request body of the `updateAmenity` function, containing attributes for updating an amenity.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a `ResponseEntity` object with a status code of either `NO_CONTENT` or `NOT_FOUND`, depending on whether the amenity was successfully updated or not.\n\n* `HttpStatus`: This is an instance of the `HttpStatus` class, which represents the status code of the response. In this case, it can be either `NO_CONTENT` or `NOT_FOUND`.\n* `ResponseEntity`: This is a class that holds the status code and the body of the response. The body can be either an empty object (`NO_CONTENT`) or a `Void` object (`NOT_FOUNDED`).","complex_type":true},"usage":{"language":"java","code":"@Autowired\nprivate AmenitiesApi amenityAPI;\n\npublic void updateAmenity() {\n    UpdateAmenityRequest request = new UpdateAmenityRequest();\n    request.setAmenityName(\"New name\");\n    request.setDescription(\"New description\");\n    \n    ResponseEntity<Void> response = amenityAPI.updateAmenity(amenityId, request);\n}\n","description":""},"name":"updateAmenity","code":"@Override\n  public ResponseEntity<Void> updateAmenity(@PathVariable String amenityId,\n      @Valid @RequestBody UpdateAmenityRequest request) {\n    AmenityDto amenityDto = amenityApiMapper.updateAmenityRequestToAmenityDto(request);\n    amenityDto.setAmenityId(amenityId);\n    boolean isUpdated = amenitySDJpaService.updateAmenity(amenityDto);\n    if (isUpdated) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }","location":{"start":84,"insert":84,"offset":" ","indent":2},"item_type":"method","length":12}]}}}