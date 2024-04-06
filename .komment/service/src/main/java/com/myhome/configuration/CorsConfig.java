{"name":"CorsConfig.java","path":"service/src/main/java/com/myhome/configuration/CorsConfig.java","content":{"structured":{"description":"A configuration class `CorsConfig` that enables CORS (Cross-Origin Resource Sharing) for a Spring MVC web application. The `allowedOrigins` field specifies the origins from which requests are allowed, and the `addCorsMappings` method adds CORS mapping to allow all methods, headers, and credentials.","items":[{"id":"29d27593-f2ed-43a6-be41-abf5aedda4af","ancestors":[],"type":"function","description":"TODO","name":"CorsConfig","code":"@Configuration\npublic class CorsConfig {\n\n  @Value(\"${server.cors.allowedOrigins}\")\n  private String[] allowedOrigins;\n\n  @Bean\n  public WebMvcConfigurer corsConfigurer() {\n    return new WebMvcConfigurer() {\n      @Override\n      public void addCorsMappings(CorsRegistry registry) {\n        registry.addMapping(\"/**\")\n            .allowedOrigins(allowedOrigins)\n            .allowedMethods(\"*\")\n            .allowedHeaders(\"*\")\n            .exposedHeaders(\"token\", \"userId\")\n            .allowCredentials(true);\n      }\n    };\n  }\n}","location":{"start":25,"insert":25,"offset":" ","indent":0},"item_type":"class","length":21},{"id":"1774d16a-4ed1-77a8-d34d-4f082dcb884e","ancestors":["29d27593-f2ed-43a6-be41-abf5aedda4af"],"type":"function","description":"defines CORS mappings for a web application, allowing requests from specified origins, methods, headers, and credentials.","params":[],"returns":{"type_name":"instance","description":"a set of CORS mappings that allow requests from any origin and specify which methods, headers, and credentials are allowed.\n\n* `allowedOrigins`: An array of allowed origins for CORS requests. In this case, it is empty, indicating that any origin can make a request to the server.\n* `allowedMethods`: An array of allowed HTTP methods for CORS requests. This is set to \"*\" to allow all methods.\n* `allowedHeaders`: An array of allowed headers for CORS requests. This is also set to \"*\" to allow all headers.\n* `exposedHeaders`: An array of headers that are exposed in the response. In this case, two headers are exposed: \"token\" and \"userId\".\n* `allowCredentials`: A boolean value indicating whether credentials (e.g., cookies, authentication tokens) should be allowed for CORS requests. This is set to true to allow credentials.","complex_type":true},"usage":{"language":"java","code":"@Bean\npublic WebMvcConfigurer corsConfigurer() {\n    return new WebMvcConfigurer() {\n        @Override\n        public void addCorsMappings(CorsRegistry registry) {\n            registry.addMapping(\"/**\")\n                .allowedOrigins(new String[]{\"*\"})\n                .allowedMethods(\"*\")\n                .allowedHeaders(\"*\")\n                .exposedHeaders(\"token\", \"userId\")\n                .allowCredentials(true);\n        }\n    };\n}\n","description":"\nThis method is used to configure CORS in the Spring MVC application. The `CorsRegistry` class is used to define the allowed origins, methods, headers, and exposed headers for the API. The `allowedOrigins()` method specifies that any origin can access the API, while the `allowedMethods()`, `allowedHeaders()`, `exposedHeaders()`, and `allowCredentials()` methods specify that any method, header, or credential can be used in the request."},"name":"corsConfigurer","code":"@Bean\n  public WebMvcConfigurer corsConfigurer() {\n    return new WebMvcConfigurer() {\n      @Override\n      public void addCorsMappings(CorsRegistry registry) {\n        registry.addMapping(\"/**\")\n            .allowedOrigins(allowedOrigins)\n            .allowedMethods(\"*\")\n            .allowedHeaders(\"*\")\n            .exposedHeaders(\"token\", \"userId\")\n            .allowCredentials(true);\n      }\n    };\n  }","location":{"start":31,"insert":31,"offset":" ","indent":2},"item_type":"method","length":14},{"id":"7dd3341a-dbfa-09a5-f748-c3e60bd79f35","ancestors":["29d27593-f2ed-43a6-be41-abf5aedda4af","1774d16a-4ed1-77a8-d34d-4f082dcb884e"],"type":"function","description":"adds CORS mappings to a registry, allowing requests from any origin and specifying which methods, headers, and credentials are allowed.","params":[{"name":"registry","type_name":"CorsRegistry","description":"Cors registry that the method adds mappings to.\n\n* `registry`: This is an instance of the `CorsRegistry` class, which represents a collection of CORS configuration mappings for a server.\n* `addMapping`: This method is used to add a new CORS mapping to the registry. The method takes a string parameter representing the URL path that the mapping applies to.\n* `allowedOrigins`: An array of strings representing the origins (domains or IP addresses) that are allowed to make requests to the server.\n* `allowedMethods`: An array of strings representing the HTTP methods (such as GET, POST, PUT, DELETE, etc.) that are allowed for the mapping.\n* `allowedHeaders`: An array of strings representing the HTTP headers that are allowed for the mapping.\n* `exposedHeaders`: An array of strings representing the HTTP headers that are exposed to clients in responses.\n* `allowCredentials`: A boolean value indicating whether the CORS configuration allows credentials (such as cookies or authorized access tokens) in requests.","complex_type":true}],"usage":{"language":"java","code":"@Override\npublic void addCorsMappings(CorsRegistry registry) {\n    registry.addMapping(\"/**\")\n            .allowedOrigins(\"http://example.com\", \"https://example.com\")\n            .allowedMethods(\"*\")\n            .allowedHeaders(\"*\")\n            .exposedHeaders(\"token\", \"userId\")\n            .allowCredentials(true);\n}\n","description":""},"name":"addCorsMappings","code":"@Override\n      public void addCorsMappings(CorsRegistry registry) {\n        registry.addMapping(\"/**\")\n            .allowedOrigins(allowedOrigins)\n            .allowedMethods(\"*\")\n            .allowedHeaders(\"*\")\n            .exposedHeaders(\"token\", \"userId\")\n            .allowCredentials(true);\n      }","location":{"start":34,"insert":34,"offset":" ","indent":6},"item_type":"method","length":9}]}}}