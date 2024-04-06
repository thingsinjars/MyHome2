{"name":"CreateUserResponse.java","path":"service/src/main/java/com/myhome/controllers/response/CreateUserResponse.java","content":{"structured":{"description":"A response model for creating a user request. The `CreateUserResponse` class has three fields: `userId`, `name`, and `email`. These fields are annotated with various Lombok annotations, indicating that they should be automatically generated by the Lombok tool. The summary is of a high-level overview of the code, including the packages used and any notable technical details.","items":[{"id":"e245cb49-9c47-2fb6-874d-1daf754b4a96","ancestors":[],"type":"function","description":"represents a model for responding to a create user request with attributes for user ID, name, and email.\nFields:\n\t- userId (String): represents a unique identifier for a user in the system. \n\t- name (String): of the CreateUserResponse class holds a string value representing the user's name. \n\t- email (String): represents a string value in the CreateUserResponse class. \n\n","name":"CreateUserResponse","code":"@AllArgsConstructor\n@NoArgsConstructor\n@Getter\n@Setter\npublic class CreateUserResponse {\n  private String userId;\n  private String name;\n  private String email;\n}","location":{"start":30,"insert":30,"offset":" ","indent":0},"item_type":"class","length":9}]}}}