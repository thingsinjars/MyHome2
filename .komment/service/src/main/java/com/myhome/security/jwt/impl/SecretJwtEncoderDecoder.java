{"name":"SecretJwtEncoderDecoder.java","path":"service/src/main/java/com/myhome/security/jwt/impl/SecretJwtEncoderDecoder.java","content":{"structured":{"description":"An implementation of `AppJwtEncoderDecoder`, a concrete class that follows the JWT standard for encoding and decoding JSON Web Tokens. The class uses the Spring Boot framework's `@Profile` annotation to specify the default profile and the `io.jsonwebtoken` package for encoding and decoding JWTs. The class provides methods for encoding and decoding JWTs, including extracting claims from the JWT, generating a new JWT with updated expiration, and signing the JWT with a secret key.","items":[{"id":"9a15bc89-9267-36b5-7440-bf2b867cfa55","ancestors":[],"type":"function","description":"TODO","name":"SecretJwtEncoderDecoder","code":"@Component\n@Profile(\"default\")\npublic class SecretJwtEncoderDecoder implements AppJwtEncoderDecoder {\n\n  @Override public AppJwt decode(String encodedJwt, String secret) {\n    Claims claims = Jwts.parserBuilder()\n        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))\n        .build()\n        .parseClaimsJws(encodedJwt)\n        .getBody();\n    String userId = claims.getSubject();\n    Date expiration = claims.getExpiration();\n    return AppJwt.builder()\n        .userId(userId)\n        .expiration(expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())\n        .build();\n  }\n\n  @Override public String encode(AppJwt jwt, String secret) {\n    Date expiration = Date.from(jwt.getExpiration().atZone(ZoneId.systemDefault()).toInstant());\n    return Jwts.builder()\n        .setSubject(jwt.getUserId())\n        .setExpiration(expiration)\n        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512).compact();\n  }\n}","location":{"start":33,"insert":33,"offset":" ","indent":0},"item_type":"class","length":26},{"id":"dcd224a4-391e-4e9d-ec46-068416113841","ancestors":["9a15bc89-9267-36b5-7440-bf2b867cfa55"],"type":"function","description":"decodes a JSON Web Token (JWT) and returns an instance of the `AppJwt` class with the decoded user ID, expiration date, and other relevant information.","params":[{"name":"encodedJwt","type_name":"String","description":"JSON Web Token (JWT) that is to be decoded and converted into an instance of the `AppJwt` class.\n\n* `encodedJwt`: A string representation of a JSON Web Token (JWT) that contains claims about the user and its expiration date.\n* `secret`: The secret key used to sign the JWT, which is required for decoding.\n\nThe function first uses the `Jwts.parserBuilder()` method to create a `JwsParsingContext` instance with the provided secret key. Then, it parses the `encodedJwt` using the `build().parseClaimsJws()` method, which returns a `Claims` object containing the decoded claims from the JWT. Finally, the function creates a new `AppJwt` instance with the user ID and expiration date extracted from the `Claims` object, and returns it.","complex_type":true},{"name":"secret","type_name":"String","description":"HSM key used for signing and verifying the JWT token.\n\n* `secret`: This is the secret key used for signing the JWT. It is an instance of the `Keys` class, which provides methods for generating and managing cryptographic keys.\n* `hmacShaKeyFor(secret.getBytes())`: This method generates a HMAC-SHA-256 key for signing the JWT using the provided secret. The resulting key is an instance of the `HmacSha256Key` class.\n* `parseClaimsJws(encodedJwt)`: This method parses the JSON Web Token (JWT) and extracts the claims from it. The claims are stored in a `Claims` object, which represents the payload of the JWT.\n* `getBody()`: This method returns the body of the `Claims` object, which contains the subject and expiration information of the JWT.","complex_type":true}],"returns":{"type_name":"AppJwt","description":"an instance of `AppJwt` with updated user ID and expiration date.\n\n* The `AppJwt` object is constructed with the decoded `Claims` object from the input JWT.\n* The `userId` field contains the subject claim of the decoded JWT.\n* The `expiration` field contains the expiration date and time of the decoded JWT, represented as a `LocalDateTime`.\n\nThe output of the `decode` function can be further analyzed or processed based on the specific use case.","complex_type":true},"usage":{"language":"java","code":"String encodedJwt = \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.hBTmEw-5v2fH7S2Q643KGWQ8oCXJL_9FxQO18dD-kYs\";\nString secret = \"My$ecretK3y\";\nAppJwt appJwt = decode(encodedJwt, secret);\n","description":"\nThis code will parse the encoded JWT and return an instance of AppJwt. The method decode takes in two parameters: encodedJwt and secret, both are strings."},"name":"decode","code":"@Override public AppJwt decode(String encodedJwt, String secret) {\n    Claims claims = Jwts.parserBuilder()\n        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))\n        .build()\n        .parseClaimsJws(encodedJwt)\n        .getBody();\n    String userId = claims.getSubject();\n    Date expiration = claims.getExpiration();\n    return AppJwt.builder()\n        .userId(userId)\n        .expiration(expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())\n        .build();\n  }","location":{"start":37,"insert":37,"offset":" ","indent":2},"item_type":"method","length":13},{"id":"3f49bbc3-5cda-0185-cd49-6344c685cd3e","ancestors":["9a15bc89-9267-36b5-7440-bf2b867cfa55"],"type":"function","description":"takes a `AppJwt` object and a secret as input, and generates a new JWT with an updated expiration time based on the current date and time, and signs it using HmacShaKeyFor and HS512 algorithm.","params":[{"name":"jwt","type_name":"AppJwt","description":"JSON Web Token (JWT) to be encoded, which contains information about the user and their expiration date.\n\n* `jwt`: This is an instance of the AppJwt class, which represents a JSON Web Token (JWT) that contains information about a user's identity and other attributes.\n* `secret`: This is a secret key used for signing the JWT.\n* `expiration`: This represents the date and time when the JWT will expire, represented as an Instant object in the standard Java Date format.\n* `subject`: This represents the user ID associated with the JWT.","complex_type":true},{"name":"secret","type_name":"String","description":"secret key used for HMAC-SHA-256 signature creation.\n\n* `secret`: A string that represents a secret key used for HMAC-SHA-512 signature creation.\n* `getBytes()`: Returns the bytes representation of the `secret` string.","complex_type":true}],"returns":{"type_name":"String","description":"a JWT with a validated expiration time and a unique subject ID, signed with HMAC-SHA512 using a secret key.\n\n1. `String encode`: The return type is a string representing the encoded JWT.\n2. `Jwt jwt`: The input parameter is an instance of the `AppJwt` class, which contains information about the JWT, such as the user ID and expiration date.\n3. `Date expiration`: The `expiration` object is created by converting the `jwt.getExpiration()` field to a `Date` object using the `Date.from()` method. This object represents the expiration time of the JWT in milliseconds since the epoch (January 1, 1970, 00:00:00 GMT).\n4. `Builder builder`: The `builder` is an instance of the `Jwts.builder()` method, which is used to create a new JWT builder object. This object allows us to specify the claims and signing algorithm for the JWT.\n5. `setSubject(String userId)`: The `userId` parameter sets the subject of the JWT, which is the identifier of the user who the JWT belongs to.\n6. `setExpiration(Date expiration)`: The `expiration` parameter sets the expiration time of the JWT, as mentioned above.\n7. `signWith(String signatureAlgorithm, byte[] secret)`: The `signatureAlgorithm` parameter specifies the signing algorithm to be used for the JWT signature. In this case, it is set to `SignatureAlgorithm.HS512`. The `secret` parameter is the secret key used for signing the JWT.\n8. `compact()`: The `compact()` method returns the encoded JWT as a string.\n\nThe output of the `encode` function is a JWT that contains information about the user, the expiration time, and the signing algorithm used.","complex_type":true},"usage":{"language":"java","code":"@Override public String encode(AppJwt jwt, String secret) {\n    Date expiration = Date.from(jwt.getExpiration().atZone(ZoneId.systemDefault()).toInstant());\n    return Jwts.builder()\n        .setSubject(jwt.getUserId())\n        .setExpiration(expiration)\n        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512).compact();\n  }\n","description":"\nThe method encode takes two arguments, AppJwt jwt and String secret. It first converts the expiration date of the JWT to an instance of Date using the getExpiration() method and the atZone(ZoneId.systemDefault()).toInstant()) method to obtain a date in UTC format. After that, it sets the subject and expiration dates of the builder using the setSubject() and setExpiration() methods respectively. The signWith() method then signs the JWT with the HS512 algorithm and the secret key provided as an argument. Finally, the compact() method is used to create a compact JSON Web Token (JWT) that can be sent in HTTP requests."},"name":"encode","code":"@Override public String encode(AppJwt jwt, String secret) {\n    Date expiration = Date.from(jwt.getExpiration().atZone(ZoneId.systemDefault()).toInstant());\n    return Jwts.builder()\n        .setSubject(jwt.getUserId())\n        .setExpiration(expiration)\n        .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512).compact();\n  }","location":{"start":51,"insert":51,"offset":" ","indent":2},"item_type":"method","length":7}]}}}