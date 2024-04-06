{"name":"AmenityController.java","path":"service/src/main/java/com/myhome/controllers/AmenityController.java","content":{"structured":{"description":"An API for managing amenities in a community. The code defines four endpoints: `getAmenityDetails`, `listAllAmenities`, `addAmenityToCommunity`, and `deleteAmenity`. The endpoints handle CRUD (create, read, update, delete) operations on amenities. The API uses Spring WebFlux and Lombok to simplify the code.","image":"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<!-- Generated by graphviz version 2.43.0 (0)\n -->\n<!-- Title: com.myhome.controllers.AmenityController Pages: 1 -->\n<svg width=\"191pt\" height=\"93pt\"\n viewBox=\"0.00 0.00 191.00 93.00\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n<g id=\"graph0\" class=\"graph\" transform=\"scale(1 1) rotate(0) translate(4 89)\">\n<title>com.myhome.controllers.AmenityController</title>\n<!-- Node1 -->\n<g id=\"Node000001\" class=\"node\">\n<title>Node1</title>\n<g id=\"a_Node000001\"><a xlink:title=\" \">\n<polygon fill=\"#999999\" stroke=\"#666666\" points=\"183,-30 0,-30 0,0 183,0 183,-30\"/>\n<text text-anchor=\"start\" x=\"8\" y=\"-18\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.controllers.Amenity</text>\n<text text-anchor=\"middle\" x=\"91.5\" y=\"-7\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">Controller</text>\n</a>\n</g>\n</g>\n<!-- Node2 -->\n<g id=\"Node000002\" class=\"node\">\n<title>Node2</title>\n<g id=\"a_Node000002\"><a xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"132.5,-85 50.5,-85 50.5,-66 132.5,-66 132.5,-85\"/>\n<text text-anchor=\"middle\" x=\"91.5\" y=\"-73\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">AmenitiesApi</text>\n</a>\n</g>\n</g>\n<!-- Node2&#45;&gt;Node1 -->\n<g id=\"edge1_Node000001_Node000002\" class=\"edge\">\n<title>Node2&#45;&gt;Node1</title>\n<g id=\"a_edge1_Node000001_Node000002\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M91.5,-55.65C91.5,-47.36 91.5,-37.78 91.5,-30.11\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"88,-55.87 91.5,-65.87 95,-55.87 88,-55.87\"/>\n</a>\n</g>\n</g>\n</g>\n</svg>\n","items":[{"id":"ef940e8e-a9fa-5eb9-db4c-e40858f57e66","ancestors":[],"type":"function","description":"TODO","name":"AmenityController","code":"@RestController\n@Slf4j\n@RequiredArgsConstructor\npublic class AmenityController implements AmenitiesApi {\n\n  private final AmenityService amenitySDJpaService;\n  private final AmenityApiMapper amenityApiMapper;\n\n  @Override\n  public ResponseEntity<GetAmenityDetailsResponse> getAmenityDetails(\n      @PathVariable String amenityId) {\n    return amenitySDJpaService.getAmenityDetails(amenityId)\n        .map(amenityApiMapper::amenityToAmenityDetailsResponse)\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());\n  }\n\n  @Override\n  public ResponseEntity<Set<GetAmenityDetailsResponse>> listAllAmenities(\n      @PathVariable String communityId) {\n    Set<Amenity> amenities = amenitySDJpaService.listAllAmenities(communityId);\n    Set<GetAmenityDetailsResponse> response =\n        amenityApiMapper.amenitiesSetToAmenityDetailsResponseSet(amenities);\n    return ResponseEntity.ok(response);\n  }\n\n  @Override\n  public ResponseEntity<AddAmenityResponse> addAmenityToCommunity(\n      @PathVariable String communityId,\n      @RequestBody AddAmenityRequest request) {\n    return amenitySDJpaService.createAmenities(request.getAmenities(), communityId)\n        .map(amenityList -> new AddAmenityResponse().amenities(amenityList))\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.notFound().build());\n  }\n\n  @Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n\n  @Override\n  public ResponseEntity<Void> updateAmenity(@PathVariable String amenityId,\n      @Valid @RequestBody UpdateAmenityRequest request) {\n    AmenityDto amenityDto = amenityApiMapper.updateAmenityRequestToAmenityDto(request);\n    amenityDto.setAmenityId(amenityId);\n    boolean isUpdated = amenitySDJpaService.updateAmenity(amenityDto);\n    if (isUpdated) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n}","location":{"start":38,"insert":38,"offset":" ","indent":0},"item_type":"class","length":59},{"id":"361e2b7f-2ee3-f298-794a-67a6f0cea348","ancestors":["ef940e8e-a9fa-5eb9-db4c-e40858f57e66"],"type":"function","description":"retrieves amenity details from the database using `amenitySDJpaService`. The method maps the retrieved data to a `GetAmenityDetailsResponse` object using `amenityApiMapper`, and returns an `OK` response entity if successful.","params":[{"name":"amenityId","type_name":"String","description":"identifier of the amenity for which details are requested.\n\n* `amenitySDJpaService`: This is an instance of `AmenitySDJpaService`, which is a Java class that provides methods for interacting with the amenity data in the system.\n* `getAmenityDetails()`: This method is part of the `AmenitySDJpaService` interface and returns an instance of `GetAmenityDetailsResponse`.\n* `@PathVariable`: This annotation indicates that the `amenityId` parameter is passed through the URL path, which means it is obtained from the HTTP request.\n* `amenityId`: This variable represents the unique identifier for the amenity being queried. Its properties are not explicitly mentioned in the code snippet provided.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"an `ResponseEntity` object containing the amenity details as a `GetAmenityDetailsResponse`.\n\n* `ResponseEntity<GetAmenityDetailsResponse>`: This is a class that represents an entity with a response status and a body containing the amenity details.\n* `status()`: This is a method that returns the HTTP status code of the response, which can be either `OK` or `NOT_FOUND`.\n* `build()`: This is a method that builds a new `ResponseEntity` object with the specified status and body.\n* `map()`: These are methods that take a function as an argument and apply it to the output of the `getAmenityDetails` function. The functions are used to map the original output to a new response entity with the `amenityToAmenityDetailsResponse` method, which is responsible for transforming the original amenity object into an `AmenityDetailsResponse` object.\n* `orElse()`: This is a method that returns the default response entity if the result of the `map()` methods is `null`. The default response entity has a status code of `NOT_FOUND`.","complex_type":true},"usage":{"language":"java","code":"ResponseEntity<GetAmenityDetailsResponse> response =\n    this.getAmenityDetails(request.getAmenityId());\n\nif (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {\n  // Handle the case where no amenity was found for the specified ID\n} else {\n  // Use the response object to get the details of the amenity\n  GetAmenityDetailsResponse amenity = response.getBody();\n  // Do something with the amenity object, such as display its name or description\n}\n","description":""},"name":"getAmenityDetails","code":"@Override\n  public ResponseEntity<GetAmenityDetailsResponse> getAmenityDetails(\n      @PathVariable String amenityId) {\n    return amenitySDJpaService.getAmenityDetails(amenityId)\n        .map(amenityApiMapper::amenityToAmenityDetailsResponse)\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());\n  }","location":{"start":46,"insert":46,"offset":" ","indent":2},"item_type":"method","length":8},{"id":"90082dfa-dd21-2aa8-b34f-9d68f1c6f4d5","ancestors":["ef940e8e-a9fa-5eb9-db4c-e40858f57e66"],"type":"function","description":"retrieves a set of amenities from the database using `JPA`, and maps them to a set of `GetAmenityDetailsResponse` objects using `Mapper`. The resulting set of `GetAmenityDetailsResponse` objects is then returned in an `Ok` response entity.","params":[{"name":"communityId","type_name":"String","description":"identifier of the community whose amenities will be listed.\n\n* `communityId`: This is a String variable representing the community ID. It is passed as a Path Variable to the function.\n\nThe `listAllAmenities` function retrieves all amenities associated with the given community ID using the `amenitySDJpaService`. The retrieved amenities are then converted into a Set of `GetAmenityDetailsResponse` objects using the `amenityApiMapper`. Finally, an `ResponseEntity` is created with the converted response set and returned.","complex_type":true}],"returns":{"type_name":"SetGetAmenityDetailsResponse","description":"a set of `GetAmenityDetailsResponse` objects containing the details of all amenities for a given community.\n\n* `ResponseEntity`: This is the generic type of the response entity, which indicates that it contains a set of `GetAmenityDetailsResponse` objects.\n* `Set<GetAmenityDetailsResponse>`: This is the actual set of `GetAmenityDetailsResponse` objects contained within the response entity.\n* `amenitiesSetToAmenityDetailsResponseSet()`: This is a method that takes a set of `Amenity` objects and returns a set of `GetAmenityDetailsResponse` objects, each containing details about a single amenity.","complex_type":true},"usage":{"language":"java","code":"@Test\npublic void testListAllAmenities() {\n    String communityId = \"communityId\";\n    Set<GetAmenityDetailsResponse> response = controller.listAllAmenities(communityId);\n    assertEquals(200, response.getStatusCode());\n}\n","description":""},"name":"listAllAmenities","code":"@Override\n  public ResponseEntity<Set<GetAmenityDetailsResponse>> listAllAmenities(\n      @PathVariable String communityId) {\n    Set<Amenity> amenities = amenitySDJpaService.listAllAmenities(communityId);\n    Set<GetAmenityDetailsResponse> response =\n        amenityApiMapper.amenitiesSetToAmenityDetailsResponseSet(amenities);\n    return ResponseEntity.ok(response);\n  }","location":{"start":55,"insert":55,"offset":" ","indent":2},"item_type":"method","length":8},{"id":"afd616c2-c75a-50b2-b74b-edfdf36a35d2","ancestors":["ef940e8e-a9fa-5eb9-db4c-e40858f57e66"],"type":"function","description":"adds amenities to a community by calling the `createAmenities` method of the `amenitySDJpaService` and returning an `AddAmenityResponse` object with the added amenities.","params":[{"name":"communityId","type_name":"String","description":"identifier of the community to which the amenities will be added.\n\n* `communityId`: A string representing the unique identifier for a community.\n* `request`: An object containing the amenities to be added to the community.\n\nThe function first calls the `createAmenities` method of the `amenitySDJpaService`, passing in the amenities and the community ID as parameters. This method creates a list of amenities in the database. Then, it maps the list of amenities to an instance of `AddAmenityResponse`. Finally, it returns a `ResponseEntity` with a status code of `ok` or `notFound`, depending on whether the amenities were successfully added to the community.","complex_type":true},{"name":"request","type_name":"AddAmenityRequest","description":"AddAmenityRequest object containing the amenities to be added to the community, which is used by the `amenitySDJpaService` to create the new amenities in the database.\n\n* `communityId`: A string representing the ID of the community to which amenities will be added.\n* `request.getAmenities()`: An array of `AddAmenityRequest.Amenity` objects, representing the amenities to be added to the community.","complex_type":true}],"returns":{"type_name":"AddAmenityResponse","description":"a `ResponseEntity` object representing either a successful addition of amenities to the community or an error message indicating that the community does not exist.\n\n* `ResponseEntity<AddAmenityResponse>`: This is an entity object that contains the response to the add amenities request. It has a `body` field that contains an instance of `AddAmenityResponse`.\n* `AddAmenityResponse`: This is an object that contains information about the added amenities. It has a `amenities` field that contains a list of `Amenity` objects representing the added amenities.\n* `ok`: This is a boolean value indicating whether the add amenities request was successful or not. If the request was successful, this field will be set to `true`, otherwise it will be set to `false`.\n* `notFound`: This is an error message that indicates that the community with the provided `communityId` could not be found.","complex_type":true},"usage":{"language":"java","code":"AddAmenityRequest request = new AddAmenityRequest();\nrequest.setAmenities(Set.of(\"Wifi\", \"Parking\"));\nResponseEntity<AddAmenityResponse> response = controller.addAmenityToCommunity(\"1234567890\", request);\n","description":"\nIn this example, a request is created and added to the request body with two amenities specified, Wifi and Parking. The method then takes in the community ID \"1234567890\" and uses that to call the controller's addAmenityToCommunity method.  It then returns an object of type ResponseEntity<AddAmenityResponse>, which is a generic class containing an HTTP status code and any additional details returned in the response body."},"name":"addAmenityToCommunity","code":"@Override\n  public ResponseEntity<AddAmenityResponse> addAmenityToCommunity(\n      @PathVariable String communityId,\n      @RequestBody AddAmenityRequest request) {\n    return amenitySDJpaService.createAmenities(request.getAmenities(), communityId)\n        .map(amenityList -> new AddAmenityResponse().amenities(amenityList))\n        .map(ResponseEntity::ok)\n        .orElse(ResponseEntity.notFound().build());\n  }","location":{"start":64,"insert":64,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"7855805d-8753-72b9-5241-3f822df6ab8f","ancestors":["ef940e8e-a9fa-5eb9-db4c-e40858f57e66"],"type":"function","description":"deletes an amenity from the database based on its ID, returning a HTTP status code indicating the outcome of the operation.","params":[{"name":"amenityId","type_name":"String","description":"ID of an amenity to be deleted.\n\n* `amenityId`: A string representing the unique identifier for an amenity in the system.\n\nThe function checks whether the amenity with the provided `amenityId` exists in the database and deletes it if found. If successful, a `HttpStatus.NO_CONTENT` response is returned, indicating that the amenity has been deleted successfully. Otherwise, a `HttpStatus.NOT_FOUND` response is returned, indicating that the amenity could not be found in the database.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a HTTP `NO_CONTENT` status code indicating the amenity was successfully deleted.\n\n* `HttpStatus.NO_CONTENT`: The HTTP status code indicating that the amenity was successfully deleted.\n* `HttpStatus.NOT_FOUND`: The HTTP status code indicating that the specified amenity could not be found in the database.","complex_type":true},"usage":{"language":"java","code":"@Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }\n","description":""},"name":"deleteAmenity","code":"@Override\n  public ResponseEntity deleteAmenity(@PathVariable String amenityId) {\n    boolean isAmenityDeleted = amenitySDJpaService.deleteAmenity(amenityId);\n    if (isAmenityDeleted) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }","location":{"start":74,"insert":74,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"63d60ac8-ecc1-adbe-724a-6a780e09bb73","ancestors":["ef940e8e-a9fa-5eb9-db4c-e40858f57e66"],"type":"function","description":"updates an amenity in the system by passing the amenity ID and update request to the API, then checking if the update was successful or not and returning the appropriate response.","params":[{"name":"amenityId","type_name":"String","description":"ID of the amenity being updated.\n\n* `amenityId`: A String representing the unique identifier for an amenity.","complex_type":true},{"name":"request","type_name":"UpdateAmenityRequest","description":"UpdateAmenityRequest object containing the details of the amenity to be updated, which is then converted into an AmenityDto object through the use of the amenityApiMapper method.\n\n* `@Valid`: Indicates that the request body must be validated by the API.\n* `@RequestBody`: Marks the request body as a serializable Java object.\n* `UpdateAmenityRequest`: The class that defines the structure of the request body.","complex_type":true}],"returns":{"type_name":"ResponseEntity","description":"a `ResponseEntity` object with a status code of either `NO_CONTENT` or `NOT_FOUND`, depending on whether the amenity was updated successfully.\n\n* `HttpStatus`: This is an instance of the `HttpStatus` class, which represents the HTTP status code of the response. The value of this field indicates whether the request was successful (200-level status codes) or not (400-level status codes).\n* `ResponseEntity`: This is a class that holds the HTTP response entity, which includes the status code, headers, and body. In this case, the body is an instance of the `Void` type, indicating that there is no response data to return.","complex_type":true},"usage":{"language":"java","code":"@RestController\npublic class AmenityController {\n    private final AmenityService amenitySDJpaService;\n    private final AmenityApiMapper amenityApiMapper;\n\n    @Override\n    public ResponseEntity<Void> updateAmenity(@PathVariable String amenityId,\n                                             @Valid @RequestBody UpdateAmenityRequest request) {\n        AmenityDto amenityDto = amenityApiMapper.updateAmenityRequestToAmenityDto(request);\n        amenityDto.setAmenityId(amenityId);\n        boolean isUpdated = amenitySDJpaService.updateAmenity(amenityDto);\n        if (isUpdated) {\n            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n        } else {\n            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n        }\n    }\n}\n","description":""},"name":"updateAmenity","code":"@Override\n  public ResponseEntity<Void> updateAmenity(@PathVariable String amenityId,\n      @Valid @RequestBody UpdateAmenityRequest request) {\n    AmenityDto amenityDto = amenityApiMapper.updateAmenityRequestToAmenityDto(request);\n    amenityDto.setAmenityId(amenityId);\n    boolean isUpdated = amenitySDJpaService.updateAmenity(amenityDto);\n    if (isUpdated) {\n      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();\n    } else {\n      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();\n    }\n  }","location":{"start":84,"insert":84,"offset":" ","indent":2},"item_type":"method","length":12}]}}}