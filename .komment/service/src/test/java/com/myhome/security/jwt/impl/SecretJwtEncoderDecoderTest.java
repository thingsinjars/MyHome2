{"name":"SecretJwtEncoderDecoderTest.java","path":"service/src/test/java/com/myhome/security/jwt/impl/SecretJwtEncoderDecoderTest.java","content":{"structured":{"description":"A `SecretJwtEncoderDecoder` class that handles JWT encoding and decoding. The `encode()` method takes an `AppJwt` object and a secret key as inputs and returns an encoded JWT string. The `decode()` method takes an encoded JWT string and the same secret key as inputs and returns a decoded `AppJwt` object. Tests are provided to verify the proper functioning of the class, including successful encoding and decoding operations, as well as errors handling for invalid secrets or expired JWTs.","items":[{"id":"bbb66009-1bbf-5387-ab4f-76687f82f7dc","ancestors":[],"type":"function","description":"TODO","name":"SecretJwtEncoderDecoderTest","code":"class SecretJwtEncoderDecoderTest {\n  private static final String TEST_USER_ID = \"test-user-id\";\n\n  private static final String EXPIRED_JWT = \"eyJhbGciOiJIUzUxMiJ9.\"\n      + \"eyJzdWIiOiJ0ZXN0LXVzZXItaWQiLCJleHAiOjE1OTYwOTg4MDF9.\"\n      + \"jnvLiLzobwW2XKz0iuNHZu3W_XO3FNDJoDySxQv_9oUsTPGPcy83_9ETMZRsUBLB9YzkZ0ZtSfP05g4RVKuFhg\";\n\n  private static final String INVALID_SECRET = \"secret\";\n  private static final String VALID_SECRET = \"secretsecretsecretsecretsecretsecretsecretsecret\"\n      + \"secretsecretsecretsecretsecretsecretsecretsecret\"\n      + \"secretsecretsecretsecretsecretsecretsecretsecret\"\n      + \"secretsecretsecretsecretsecretsecretsecretsecret\"\n      + \"secretsecretsecretsecretsecretsecretsecretsecret\";\n\n  @Test\n  void jwtEncodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // then\n    Assertions.assertNotNull(jwtEncoderDecoder.encode(appJwt, VALID_SECRET));\n  }\n\n  @Test\n  void jwtEncodeFailWithException() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // when and then\n    Assertions.assertThrows(WeakKeyException.class,\n        () -> jwtEncoderDecoder.encode(appJwt, INVALID_SECRET));\n  }\n\n  @Test\n  void jwtDecodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt =\n        AppJwt.builder().userId(TEST_USER_ID).expiration(LocalDateTime.now().plusHours(1)).build();\n    String encodedJwt = jwtEncoderDecoder.encode(appJwt, VALID_SECRET);\n\n    // when\n    AppJwt decodedJwt = jwtEncoderDecoder.decode(encodedJwt, VALID_SECRET);\n\n    // then\n    Assertions.assertNotNull(decodedJwt);\n    Assertions.assertEquals(decodedJwt.getUserId(), TEST_USER_ID);\n    Assertions.assertNotNull(decodedJwt.getExpiration());\n  }\n\n  @Test\n  void jwtDecodeFailWithExpiredJwt() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when and then\n    Assertions.assertThrows(ExpiredJwtException.class,\n        () -> jwtEncoderDecoder.decode(EXPIRED_JWT, VALID_SECRET));\n  }\n}","location":{"start":26,"insert":26,"offset":" ","indent":0},"item_type":"class","length":64},{"id":"016f80e9-e6d8-98b9-d34e-9a853c65c555","ancestors":["bbb66009-1bbf-5387-ab4f-76687f82f7dc"],"type":"function","description":"tests whether the `SecretJwtEncoderDecoder` class can successfully encode an JWT token using a provided secret.","params":[],"usage":{"language":"java","code":"@Test\n  void jwtEncodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // then\n    Assertions.assertNotNull(jwtEncoderDecoder.encode(appJwt, VALID_SECRET));\n  }\n","description":"\nThis example shows how to use the method jwtEncodeSuccess by first creating an instance of SecretJwtEncoderDecoder and then using it to encode a JWT with a user ID and expiration time. The test expects the encoded JWT not to be null, which is why we are using Assertions.assertNotNull()."},"name":"jwtEncodeSuccess","code":"@Test\n  void jwtEncodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // then\n    Assertions.assertNotNull(jwtEncoderDecoder.encode(appJwt, VALID_SECRET));\n  }","location":{"start":40,"insert":40,"offset":" ","indent":2},"item_type":"method","length":11},{"id":"9f0de27f-c63b-20b8-4949-32ace799e12b","ancestors":["bbb66009-1bbf-5387-ab4f-76687f82f7dc"],"type":"function","description":"tests the behavior of the `SecretJwtEncoderDecoder` class when an invalid secret key is provided during JWT encoding. It asserts that an exception of type `WeakKeyException` is thrown when an invalid secret key is used.","params":[],"usage":{"language":"java","code":"@Test\n  void jwtEncodeFailWithException() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // when and then\n    Assertions.assertThrows(WeakKeyException.class,\n        () -> jwtEncoderDecoder.encode(appJwt, INVALID_SECRET));\n  }\n","description":""},"name":"jwtEncodeFailWithException","code":"@Test\n  void jwtEncodeFailWithException() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt = AppJwt.builder().expiration(LocalDateTime.now()).userId(TEST_USER_ID).build();\n\n    // when and then\n    Assertions.assertThrows(WeakKeyException.class,\n        () -> jwtEncoderDecoder.encode(appJwt, INVALID_SECRET));\n  }","location":{"start":52,"insert":52,"offset":" ","indent":2},"item_type":"method","length":10},{"id":"fd421437-b1dc-0199-5448-5ec63f02c324","ancestors":["bbb66009-1bbf-5387-ab4f-76687f82f7dc"],"type":"function","description":"tests the successful decoding of a JWT token using a secret key. It verifies that the decoded JWT has the expected user ID and expiration time.","params":[],"usage":{"language":"java","code":"@Test\n  void jwtDecodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt =\n        AppJwt.builder().userId(TEST_USER_ID).expiration(LocalDateTime.now().plusHours(1)).build();\n    String encodedJwt = jwtEncoderDecoder.encode(appJwt, VALID_SECRET);\n\n    // when\n    AppJwt decodedJwt = jwtEncoderDecoder.decode(encodedJwt, VALID_SECRET);\n\n    // then\n    Assertions.assertNotNull(decodedJwt);\n    Assertions.assertEquals(decodedJwt.getUserId(), TEST_USER_ID);\n    Assertions.assertNotNull(decodedJwt.getExpiration());\n  }\n","description":"\nThe example shows how to use the jwtDecodeSuccess method by creating an instance of SecretJwtEncoderDecoder, and then using it to encode and decode a JWT token. The encoded JWT is decoded with the same secret key used for encoding, and the decoded JWT's user ID and expiration date are asserted to match those expected. This test demonstrates that the jwtDecodeSuccess method can successfully decode a valid JWT token generated by the encode method."},"name":"jwtDecodeSuccess","code":"@Test\n  void jwtDecodeSuccess() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n    AppJwt appJwt =\n        AppJwt.builder().userId(TEST_USER_ID).expiration(LocalDateTime.now().plusHours(1)).build();\n    String encodedJwt = jwtEncoderDecoder.encode(appJwt, VALID_SECRET);\n\n    // when\n    AppJwt decodedJwt = jwtEncoderDecoder.decode(encodedJwt, VALID_SECRET);\n\n    // then\n    Assertions.assertNotNull(decodedJwt);\n    Assertions.assertEquals(decodedJwt.getUserId(), TEST_USER_ID);\n    Assertions.assertNotNull(decodedJwt.getExpiration());\n  }","location":{"start":63,"insert":63,"offset":" ","indent":2},"item_type":"method","length":16},{"id":"b61dc4fb-0aa4-1e98-6e46-61af37a89d87","ancestors":["bbb66009-1bbf-5387-ab4f-76687f82f7dc"],"type":"function","description":"tests whether an exception is thrown when attempting to decode an expired JWT using the `SecretJwtEncoderDecoder`.","params":[],"usage":{"language":"java","code":"@Test\n  void jwtDecodeFailWithExpiredJwt() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when and then\n    Assertions.assertThrows(ExpiredJwtException.class,\n        () -> jwtEncoderDecoder.decode(EXPIRED_JWT, VALID_SECRET));\n  }\n","description":""},"name":"jwtDecodeFailWithExpiredJwt","code":"@Test\n  void jwtDecodeFailWithExpiredJwt() {\n    // given\n    SecretJwtEncoderDecoder jwtEncoderDecoder = new SecretJwtEncoderDecoder();\n\n    // when and then\n    Assertions.assertThrows(ExpiredJwtException.class,\n        () -> jwtEncoderDecoder.decode(EXPIRED_JWT, VALID_SECRET));\n  }","location":{"start":80,"insert":80,"offset":" ","indent":2},"item_type":"method","length":9}]}}}