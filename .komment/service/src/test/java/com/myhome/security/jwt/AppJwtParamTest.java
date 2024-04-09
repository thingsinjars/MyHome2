{"name":"AppJwtParamTest.java","path":"service/src/test/java/com/myhome/security/jwt/AppJwtParamTest.java","content":{"structured":{"description":"A test class called AppJwtParamTest that creates an instance of the AppJwt class using its builder methods to set user ID, expiration time, and builds the final JWT parameter instance.","items":[{"id":"4a1bdade-cdcc-ba96-f54e-e5edf3d460af","ancestors":[],"type":"function","description":"TODO","name":"AppJwtParamTest","code":"class AppJwtParamTest {\n\n  @Test\n  void testParamCreationBuilder() {\n    AppJwt param = AppJwt.builder().userId(\"test-user-id\").expiration(LocalDateTime.now()).build();\n    System.out.println(param);\n  }\n}","location":{"start":22,"insert":22,"offset":" ","indent":0},"item_type":"class","length":8},{"id":"5ffa7860-d109-c8b6-a746-14efbdf2aa30","ancestors":["4a1bdade-cdcc-ba96-f54e-e5edf3d460af"],"type":"function","description":"creates an instance of the `AppJwt` class using a builder-like approach, allowing for customization of the resulting object's attributes, including `userId`, `expiration`, and builds the final object.","params":[],"usage":{"language":"java","code":"@Test\n  void testParamCreationBuilder() {\n    AppJwt param = AppJwt.builder().userId(\"test-user-id\").expiration(LocalDateTime.now()).build();\n    System.out.println(param);\n  }\n","description":"\nThis code creates a builder object and populates it with the required values. It then uses the build() method to create an instance of AppJwt, which is then printed to the console."},"name":"testParamCreationBuilder","code":"@Test\n  void testParamCreationBuilder() {\n    AppJwt param = AppJwt.builder().userId(\"test-user-id\").expiration(LocalDateTime.now()).build();\n    System.out.println(param);\n  }","location":{"start":24,"insert":24,"offset":" ","indent":2},"item_type":"method","length":5}]}}}