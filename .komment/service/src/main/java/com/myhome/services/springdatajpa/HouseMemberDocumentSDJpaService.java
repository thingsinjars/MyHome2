{"name":"HouseMemberDocumentSDJpaService.java","path":"service/src/main/java/com/myhome/services/springdatajpa/HouseMemberDocumentSDJpaService.java","content":{"structured":{"description":"A HouseMemberDocumentSDJpaService class that provides services for handling House Member Documents within a Spring Data JPA application. The service class implements the HouseMemberDocumentService interface and provides methods for finding and deleting House Member Documents, creating new ones, and updating existing ones. The service uses dependencies on the House MemberRepository and House MemberDocumentRepository classes to perform these operations. Additionally, the code defines methods for compressing and saving image data, which is used in the House Member Document creation and update processes.","image":"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<!-- Generated by graphviz version 2.43.0 (0)\n -->\n<!-- Title: com.myhome.domain.HouseMember Pages: 1 -->\n<svg width=\"206pt\" height=\"137pt\"\n viewBox=\"0.00 0.00 206.00 137.00\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n<g id=\"graph0\" class=\"graph\" transform=\"scale(1 1) rotate(0) translate(4 133)\">\n<title>com.myhome.domain.HouseMember</title>\n<!-- Node1 -->\n<g id=\"Node000001\" class=\"node\">\n<title>Node1</title>\n<g id=\"a_Node000001\"><a xlink:title=\" \">\n<polygon fill=\"#999999\" stroke=\"#666666\" points=\"198,-19 0,-19 0,0 198,0 198,-19\"/>\n<text text-anchor=\"middle\" x=\"99\" y=\"-7\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.domain.HouseMember</text>\n</a>\n</g>\n</g>\n<!-- Node2 -->\n<g id=\"Node000002\" class=\"node\">\n<title>Node2</title>\n<g id=\"a_Node000002\"><a xlink:href=\"classcom_1_1myhome_1_1domain_1_1BaseEntity.html\" target=\"_top\" xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"188.5,-74 9.5,-74 9.5,-55 188.5,-55 188.5,-74\"/>\n<text text-anchor=\"middle\" x=\"99\" y=\"-62\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.domain.BaseEntity</text>\n</a>\n</g>\n</g>\n<!-- Node2&#45;&gt;Node1 -->\n<g id=\"edge1_Node000001_Node000002\" class=\"edge\">\n<title>Node2&#45;&gt;Node1</title>\n<g id=\"a_edge1_Node000001_Node000002\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M99,-44.66C99,-35.93 99,-25.99 99,-19.09\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"95.5,-44.75 99,-54.75 102.5,-44.75 95.5,-44.75\"/>\n</a>\n</g>\n</g>\n<!-- Node3 -->\n<g id=\"Node000003\" class=\"node\">\n<title>Node3</title>\n<g id=\"a_Node000003\"><a xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"136,-129 62,-129 62,-110 136,-110 136,-129\"/>\n<text text-anchor=\"middle\" x=\"99\" y=\"-117\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">Serializable</text>\n</a>\n</g>\n</g>\n<!-- Node3&#45;&gt;Node2 -->\n<g id=\"edge2_Node000002_Node000003\" class=\"edge\">\n<title>Node3&#45;&gt;Node2</title>\n<g id=\"a_edge2_Node000002_Node000003\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M99,-99.66C99,-90.93 99,-80.99 99,-74.09\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"95.5,-99.75 99,-109.75 102.5,-99.75 95.5,-99.75\"/>\n</a>\n</g>\n</g>\n</g>\n</svg>\n","items":[{"id":"46b7cd06-29d0-fa86-8b4d-74e375ae390f","ancestors":[],"type":"function","description":"TODO","name":"HouseMemberDocumentSDJpaService","code":"@Service\npublic class HouseMemberDocumentSDJpaService implements HouseMemberDocumentService {\n\n  private final HouseMemberRepository houseMemberRepository;\n  private final HouseMemberDocumentRepository houseMemberDocumentRepository;\n  @Value(\"${files.compressionBorderSizeKBytes}\")\n  private int compressionBorderSizeKBytes;\n  @Value(\"${files.maxSizeKBytes}\")\n  private int maxFileSizeKBytes;\n  @Value(\"${files.compressedImageQuality}\")\n  private float compressedImageQuality;\n\n  public HouseMemberDocumentSDJpaService(HouseMemberRepository houseMemberRepository,\n      HouseMemberDocumentRepository houseMemberDocumentRepository) {\n    this.houseMemberRepository = houseMemberRepository;\n    this.houseMemberDocumentRepository = houseMemberDocumentRepository;\n  }\n\n  @Override\n  public Optional<HouseMemberDocument> findHouseMemberDocument(String memberId) {\n    return houseMemberRepository.findByMemberId(memberId)\n        .map(HouseMember::getHouseMemberDocument);\n  }\n\n  @Override\n  public boolean deleteHouseMemberDocument(String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      if (member.getHouseMemberDocument() != null) {\n        member.setHouseMemberDocument(null);\n        houseMemberRepository.save(member);\n        return true;\n      }\n      return false;\n    }).orElse(false);\n  }\n\n  @Override\n  public Optional<HouseMemberDocument> updateHouseMemberDocument(MultipartFile multipartFile,\n      String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      Optional<HouseMemberDocument> houseMemberDocument = tryCreateDocument(multipartFile, member);\n      houseMemberDocument.ifPresent(document -> addDocumentToHouseMember(document, member));\n      return houseMemberDocument;\n    }).orElse(Optional.empty());\n  }\n\n  @Override\n  public Optional<HouseMemberDocument> createHouseMemberDocument(MultipartFile multipartFile,\n      String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      Optional<HouseMemberDocument> houseMemberDocument = tryCreateDocument(multipartFile, member);\n      houseMemberDocument.ifPresent(document -> addDocumentToHouseMember(document, member));\n      return houseMemberDocument;\n    }).orElse(Optional.empty());\n  }\n\n  private Optional<HouseMemberDocument> tryCreateDocument(MultipartFile multipartFile,\n      HouseMember member) {\n\n    try (ByteArrayOutputStream imageByteStream = new ByteArrayOutputStream()) {\n      BufferedImage documentImage = getImageFromMultipartFile(multipartFile);\n      if (multipartFile.getSize() < DataSize.ofKilobytes(compressionBorderSizeKBytes).toBytes()) {\n        writeImageToByteStream(documentImage, imageByteStream);\n      } else {\n        compressImageToByteStream(documentImage, imageByteStream);\n      }\n      if (imageByteStream.size() < DataSize.ofKilobytes(maxFileSizeKBytes).toBytes()) {\n        HouseMemberDocument houseMemberDocument = saveHouseMemberDocument(imageByteStream,\n            String.format(\"member_%s_document.jpg\", member.getMemberId()));\n        return Optional.of(houseMemberDocument);\n      } else {\n        return Optional.empty();\n      }\n    } catch (IOException e) {\n      return Optional.empty();\n    }\n  }\n\n  private HouseMember addDocumentToHouseMember(HouseMemberDocument houseMemberDocument,\n      HouseMember member) {\n    member.setHouseMemberDocument(houseMemberDocument);\n    return houseMemberRepository.save(member);\n  }\n\n  private HouseMemberDocument saveHouseMemberDocument(ByteArrayOutputStream imageByteStream,\n      String filename) {\n    HouseMemberDocument newDocument =\n        new HouseMemberDocument(filename, imageByteStream.toByteArray());\n    return houseMemberDocumentRepository.save(newDocument);\n  }\n\n  private void writeImageToByteStream(BufferedImage documentImage,\n      ByteArrayOutputStream imageByteStream)\n      throws IOException {\n    ImageIO.write(documentImage, \"jpg\", imageByteStream);\n  }\n\n  private void compressImageToByteStream(BufferedImage bufferedImage,\n      ByteArrayOutputStream imageByteStream) throws IOException {\n\n    try (ImageOutputStream imageOutStream = ImageIO.createImageOutputStream(imageByteStream)) {\n\n      ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(\"jpg\").next();\n      imageWriter.setOutput(imageOutStream);\n      ImageWriteParam param = imageWriter.getDefaultWriteParam();\n\n      if (param.canWriteCompressed()) {\n        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);\n        param.setCompressionQuality(compressedImageQuality);\n      }\n      imageWriter.write(null, new IIOImage(bufferedImage, null, null), param);\n      imageWriter.dispose();\n    }\n  }\n\n  private BufferedImage getImageFromMultipartFile(MultipartFile multipartFile) throws IOException {\n    try (InputStream multipartFileStream = multipartFile.getInputStream()) {\n      return ImageIO.read(multipartFileStream);\n    }\n  }\n}","location":{"start":39,"insert":39,"offset":" ","indent":0},"item_type":"class","length":121},{"id":"93cb37ba-ea1f-329f-f045-4543b3c58069","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"retrieves a House Member Document associated with a given member ID from the repository and maps it to an Optional<HouseMemberDocument>.","params":[{"name":"memberId","type_name":"String","description":"unique identifier of a member in the system, which is used to retrieve the corresponding `HouseMemberDocument` from the repository.\n\n* `houseMemberRepository`: This is a repository responsible for storing and retrieving House Member documents.\n* `findByMemberId(memberId)`: This method returns an optional `HouseMemberDocument` object that corresponds to the provided `memberId`.\n* `map(HouseMember::getHouseMemberDocument)`: This line maps the returned `HouseMemberDocument` object to a new `Optional<HouseMemberDocument>` object, which is then returned as the function's output.","complex_type":true}],"returns":{"type_name":"Optional","description":"an optional instance of `HouseMemberDocument`.\n\n* `Optional<HouseMemberDocument>` is the type of the output, indicating that it may or may not contain a value depending on whether a match was found in the repository.\n* `houseMemberRepository.findByMemberId(memberId)` is the method called to retrieve the House Member Document from the repository based on the input `memberId`.\n* `map(HouseMember::getHouseMemberDocument)` is a method that applies the function `getHouseMemberDocument` to the result of the previous call, transforming it into the final output.","complex_type":true},"usage":{"language":"java","code":"public class SomeClass {\n    private HouseMemberRepository houseMemberRepository;\n    \n    public void someMethod() {\n        Optional<HouseMemberDocument> document = findHouseMemberDocument(\"memberId\");\n        \n        if (document.isPresent()) {\n            // Do something with the document\n        } else {\n            // Document is not present, handle this case\n        }\n    }\n}\n","description":""},"name":"findHouseMemberDocument","code":"@Override\n  public Optional<HouseMemberDocument> findHouseMemberDocument(String memberId) {\n    return houseMemberRepository.findByMemberId(memberId)\n        .map(HouseMember::getHouseMemberDocument);\n  }","location":{"start":57,"insert":57,"offset":" ","indent":2},"item_type":"method","length":5},{"id":"9900cc78-0023-79b4-ca4c-a618ddcb31cf","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"deletes a house member's document by finding the member in the repository, setting their document to null, and saving them in the repository. If successful, it returns `true`.","params":[{"name":"memberId","type_name":"String","description":"id of the member whose house member document should be deleted.\n\n* `memberId`: A string representing the member ID to delete the house member document for.\n\nThe function first retrieves the house member record associated with the `memberId`. If the record exists and has a non-null value for the `HouseMemberDocument` field, it is set to null, and then saved in the repository. Finally, the function returns a boolean indicating whether the operation was successful or not.","complex_type":true}],"returns":{"type_name":"boolean","description":"a boolean value indicating whether the house member document associated with the provided member ID has been successfully deleted.","complex_type":false},"usage":{"language":"java","code":"boolean deleted = deleteHouseMemberDocument(\"memberId\");\nif (deleted) {\n    System.out.println(\"Successfully deleted document of member with id 'memberId'.\");\n} else {\n    System.out.println(\"Document of member with id 'memberId' not found or already deleted.\");\n}\n","description":"\nExplanation:\nThe method deleteHouseMemberDocument searches for a HouseMember entity using the given memberId and checks if it has a document attached to it. If the document is null, nothing is done and false is returned. Otherwise, the HouseMember document property is set to null, saved in the repository, and true is returned.\nThe example code uses this method to delete the document of a HouseMember with the id \"memberId\". If successful, it will print \"Successfully deleted document of member with id 'memberId'.\" to the console. Otherwise, if the document was not found or already deleted, it will print \"Document of member with id 'memberId' not found or already deleted.\" to the console."},"name":"deleteHouseMemberDocument","code":"@Override\n  public boolean deleteHouseMemberDocument(String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      if (member.getHouseMemberDocument() != null) {\n        member.setHouseMemberDocument(null);\n        houseMemberRepository.save(member);\n        return true;\n      }\n      return false;\n    }).orElse(false);\n  }","location":{"start":63,"insert":63,"offset":" ","indent":2},"item_type":"method","length":11},{"id":"18842367-9282-ffb9-984b-65d372211618","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"updates an existing House Member Document by finding the corresponding member, creating a new document if necessary, and adding it to the member's record.","params":[{"name":"multipartFile","type_name":"MultipartFile","description":"file containing the House Member document to be updated, which is being passed through the `findByMemberId()` method to retrieve the corresponding House Member entity.\n\n* `multipartFile`: This is an instance of the `MultipartFile` class, which contains various attributes related to a file upload. These attributes may include the file name, file type, size, and other metadata.","complex_type":true},{"name":"memberId","type_name":"String","description":"unique identifier of the member whose document is being updated.\n\n* `memberId`: This is a String attribute that represents the unique identifier for a member in the system.","complex_type":true}],"returns":{"type_name":"OptionalHouseMemberDocument","description":"an `Optional` object containing a `HouseMemberDocument`, which represents the updated document for the specified member.\n\n* `Optional<HouseMemberDocument>` represents an optional reference to a House Member Document. If a document exists for the provided member ID, this output will contain a non-empty reference to that document. Otherwise, it will be empty.\n* The `houseMemberRepository` method call returns a `Map` containing a single entry with the member ID as key and an `Optional<House Member Document>` value. This map is used to retrieve the House Member Document associated with the provided member ID, or an empty reference if no document exists.\n* The `tryCreateDocument` method creates a new House Member Document if one does not already exist for the provided member ID. If the document cannot be created (e.g., due to a database constraint violation), the output will contain an empty reference. Otherwise, it will contain a non-empty reference to the newly created document.\n* The `addDocumentToHouse Member` method adds the new or updated House Member Document to the House Member's collection of documents. This is a no-op if the document already exists in the collection.","complex_type":true},"usage":{"language":"java","code":"// Assuming we have already loaded the member with id \"1234\" from the database and stored it in a variable called 'member' of type HouseMember\nOptional<HouseMemberDocument> updatedDocument = documentService.updateHouseMemberDocument(multipartFile, \"1234\");\nif (updatedDocument.isPresent()) {\n    System.out.println(\"Updated member with id 1234's document:\");\n    System.out.println(updatedDocument.get());\n} else {\n    System.out.println(\"The uploaded document did not meet the specified requirements and could not be saved.\");\n}\n","description":"\nThis example would update the document of a member with id \"1234\" if it was found in the database, and return an Optional containing the updated HouseMemberDocument if successful. If the document does not meet the specified requirements (i.e. is too large to be stored), then the optional will be empty."},"name":"updateHouseMemberDocument","code":"@Override\n  public Optional<HouseMemberDocument> updateHouseMemberDocument(MultipartFile multipartFile,\n      String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      Optional<HouseMemberDocument> houseMemberDocument = tryCreateDocument(multipartFile, member);\n      houseMemberDocument.ifPresent(document -> addDocumentToHouseMember(document, member));\n      return houseMemberDocument;\n    }).orElse(Optional.empty());\n  }","location":{"start":75,"insert":75,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"3eba8003-b099-fba9-cb43-b5c1128280af","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"takes a `MultipartFile` and a `String` member ID as input, and returns an `Optional<HouseMemberDocument>` representing the created document or empty if none was created. It first retrieves the member from the repository using the member ID, then creates a new document using the multipart file and associates it with the member using the `addDocumentToHouseMember` function. If the creation is successful, the function returns an `Optional<HouseMemberDocument>` containing the created document; otherwise, it returns an empty `Optional`.","params":[{"name":"multipartFile","type_name":"MultipartFile","description":"file to be processed and is used to retrieve the document from the repository.\n\n* `multipartFile`: A MultipartFile object containing the file to be processed.\n* `memberId`: The ID of the member for whom the document is being created.","complex_type":true},{"name":"memberId","type_name":"String","description":"12-digit unique identifier of a member for whom a HouseMemberDocument is to be created.\n\n* `memberId`: A string representing the member ID to find the corresponding House Member document for.","complex_type":true}],"returns":{"type_name":"OptionalHouseMemberDocument","description":"an `Optional` object containing a `HouseMemberDocument`, which represents the created document if successful, or an empty `Optional` otherwise.\n\n* The first line returns an `Optional` object containing a `HouseMemberDocument`. If a document can be created successfully, this Optional will contain a non-empty value. Otherwise, it will be empty.\n* The second line uses the `map` method to apply a function to the `member` parameter. In this case, the function tries to create a new `HouseMemberDocument` based on the provided `multipartFile` and `memberId`. If successful, this function returns an `Optional` containing the newly created document.\n* The third line checks if the `Optional` returned by the previous line is non-empty. If it is, the function calls the `addDocumentToHouseMember` method to add the new document to the associated member. This method takes no arguments.\n* The fourth line returns the Optional containing the newly created or updated `HouseMemberDocument`.","complex_type":true},"usage":{"language":"java","code":"@Autowired\nprivate HouseMemberRepository houseMemberRepository;\n\npublic Optional<HouseMemberDocument> createHouseMemberDocument(MultipartFile multipartFile, String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n        Optional<HouseMemberDocument> houseMemberDocument = tryCreateDocument(multipartFile, member);\n        houseMemberDocument.ifPresent(document -> addDocumentToHouseMember(document, member));\n        return houseMemberDocument;\n    }).orElse(Optional.empty());\n}\n","description":"\nIn this example, the method is used to create a House Member Document from a Multipart File and a member ID. The method first retrieves the House Member with the given member ID using the repository's findByMemberId method. It then checks if the retrieved House Member is present in the Optional returned by the map method (i.e., it makes sure that the House Member exists). If so, the method tries to create a House Member Document from the Multipart File and adds it to the House Member using addDocumentToHouseMember(). Finally, it returns an Optional containing the new House Member Document or an empty Optional if the document cannot be created.\n\nPlease note that this is just one way of using the method, and there may be other ways as well. Additionally, for the code to work correctly, the repository's findByMemberId method needs to return an Optional that contains a valid House Member object."},"name":"createHouseMemberDocument","code":"@Override\n  public Optional<HouseMemberDocument> createHouseMemberDocument(MultipartFile multipartFile,\n      String memberId) {\n    return houseMemberRepository.findByMemberId(memberId).map(member -> {\n      Optional<HouseMemberDocument> houseMemberDocument = tryCreateDocument(multipartFile, member);\n      houseMemberDocument.ifPresent(document -> addDocumentToHouseMember(document, member));\n      return houseMemberDocument;\n    }).orElse(Optional.empty());\n  }","location":{"start":85,"insert":85,"offset":" ","indent":2},"item_type":"method","length":9},{"id":"21717666-9a42-0582-684f-a043f4112632","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"takes a MultipartFile and HouseMember object as input, and attempts to create a document from the file using image manipulation and saving it as a JPEG file. If successful, it returns an Optional containing the created HouseMemberDocument, otherwise it returns an empty Optional.","params":[{"name":"multipartFile","type_name":"MultipartFile","description":"multipart file containing the image to be processed and converted into a HouseMemberDocument.\n\n* `MultipartFile multipartFile`: This represents a multipart file that contains an image to be processed.\n* `HouseMember member`: This parameter represents a house member whose document is being created.\n* `ByteArrayOutputStream imageByteStream`: A byte array output stream used to capture the image data.\n* `BufferedImage documentImage`: An instance of `BufferedImage` containing the image data from the multipart file.\n* `DataSize compressionBorderSizeKBytes`: The size of the image in kilobytes, used for compression.\n* `DataSize maxFileSizeKBytes`: The maximum size of a file in kilobytes, used for validation.","complex_type":true},{"name":"member","type_name":"HouseMember","description":"HouseMember object whose document is being created and saved.\n\n* `member`: A HouseMember object representing an individual member of a house.\n* `multipartFile`: A MultipartFile object containing the image file to be processed.\n* `compressionBorderSizeKBytes`: The size threshold for compressing the image file (in kilobytes).\n* `maxFileSizeKBytes`: The maximum size allowed for the resulting document file (in kilobytes).\n* `getImageFromMultipartFile()`: A method that extracts an image from a MultipartFile object.\n* `writeImageToByteStream()`: A method that writes the extracted image to a ByteArrayOutputStream object.\n* `compressImageToByteStream()`: A method that compresses the image using a compression algorithm.\n* `saveHouseMemberDocument()`: A method that saves the compressed image to a file with a specified name based on the member ID.","complex_type":true}],"returns":{"type_name":"OptionalHouseMemberDocument","description":"an `Optional` containing a `HouseMemberDocument` object, or an empty `Optional` if there was an error.\n\n* `Optional<HouseMemberDocument>`: The output is an optional instance of `HouseMemberDocument`, which represents a document related to a member of a house.\n* `HouseMemberDocument`: This class represents a document related to a member of a house, with properties such as the member ID and the document type.\n* `member`: This is the input parameter representing the member for whom the document is being created.\n* `MultipartFile`: This is the input parameter representing the multipart file containing the image data for the document.\n* ` ByteArrayOutputStream` : This is a buffered output stream used to store the image data in a byte array.\n* `BufferedImage`: This is the input parameter representing the image data from the multipart file, which is processed and stored in the `ByteArrayOutputStream`.\n* `DataSize`: This is an intermediate variable used to compare the size of the image data with the maximum allowed file size.\n* `maxFileSizeKBytes`: This is a constant representing the maximum allowed file size in kilobytes.\n* `ImageIO`: This is a class used for reading and writing image files.\n* `IOException`: This is an exception that may be thrown if there is an error while reading or writing the image file.","complex_type":true},"usage":{"language":"java","code":"HouseMember member = new HouseMember(\"1234567890\", \"John Doe\");\nMultipartFile multipartFile = mock(MultipartFile.class);\nwhen(multipartFile.getSize()).thenReturn(DataSize.ofKilobytes(compressionBorderSizeKBytes).toBytes());\nBufferedImage documentImage = getImageFromMultipartFile(multipartFile);\nwriteImageToByteStream(documentImage, imageByteStream);\nHouseMemberDocument houseMemberDocument = saveHouseMemberDocument(imageByteStream, String.format(\"member_%s_document.jpg\", member.getMemberId()));\n","description":"\nNote that this example assumes that the MultipartFile has a size that is less than the compressionBorderSizeKBytes, and that the getImageFromMultipartFile function does not throw an IOException."},"name":"tryCreateDocument","code":"private Optional<HouseMemberDocument> tryCreateDocument(MultipartFile multipartFile,\n      HouseMember member) {\n\n    try (ByteArrayOutputStream imageByteStream = new ByteArrayOutputStream()) {\n      BufferedImage documentImage = getImageFromMultipartFile(multipartFile);\n      if (multipartFile.getSize() < DataSize.ofKilobytes(compressionBorderSizeKBytes).toBytes()) {\n        writeImageToByteStream(documentImage, imageByteStream);\n      } else {\n        compressImageToByteStream(documentImage, imageByteStream);\n      }\n      if (imageByteStream.size() < DataSize.ofKilobytes(maxFileSizeKBytes).toBytes()) {\n        HouseMemberDocument houseMemberDocument = saveHouseMemberDocument(imageByteStream,\n            String.format(\"member_%s_document.jpg\", member.getMemberId()));\n        return Optional.of(houseMemberDocument);\n      } else {\n        return Optional.empty();\n      }\n    } catch (IOException e) {\n      return Optional.empty();\n    }\n  }","location":{"start":95,"insert":95,"offset":" ","indent":2},"item_type":"method","length":21},{"id":"a7a870b7-92cc-eab7-aa4b-5853e13029f1","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"updates a HouseMember object's `HouseMemberDocument` field and saves it to the repository, inserting or replacing the document with the given ID in the member's record.","params":[{"name":"houseMemberDocument","type_name":"HouseMemberDocument","description":"HouseMember document to be associated with the specified `HouseMember`.\n\n* `HouseMemberDocument`: Represents the document related to a house member, which contains information about the member and their membership in the house.","complex_type":true},{"name":"member","type_name":"HouseMember","description":"HouseMember to whom the `houseMemberDocument` is being added, and it is set as the `HouseMemberDocument` of that member.\n\n* `setHouseMemberDocument(houseMemberDocument)` sets the `HouseMemberDocument` field of the `member` object to the provided `houseMemberDocument`.\n* `save()` saves the updated `member` object in the repository.","complex_type":true}],"returns":{"type_name":"HouseMember","description":"a saved House Member with the associated document.\n\nThe `houseMemberRepository.save()` method saves the updated `HouseMember` object in the database. The `member` parameter is passed to this method as a reference to the `HouseMember` object that contains an updated `HouseMemberDocument` field, which was set to the input `house MemberDocument`.","complex_type":true},"usage":{"language":"java","code":"private HouseMember addDocumentToHouseMember(HouseMemberDocument houseMemberDocument,\n      HouseMember member) {\n    member.setHouseMemberDocument(houseMemberDocument);\n    return houseMemberRepository.save(member);\n  }\n","description":"\nFor inputs of ('houseMemberDocument', 'HouseMemberDocument'), this method will create a new instance of the HouseMember class with a reference to the provided HouseMemberDocument object as its document and then save it into the database. It is an example of how to use this method, but it should be used correctly and with the correct inputs to work as expected."},"name":"addDocumentToHouseMember","code":"private HouseMember addDocumentToHouseMember(HouseMemberDocument houseMemberDocument,\n      HouseMember member) {\n    member.setHouseMemberDocument(houseMemberDocument);\n    return houseMemberRepository.save(member);\n  }","location":{"start":117,"insert":117,"offset":" ","indent":2},"item_type":"method","length":5},{"id":"0908d726-acc5-38ae-354c-f386dd3425ef","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"saves a `HouseMemberDocument` object to the database, creating it first if it doesn't exist and then storing its contents in the database.","params":[{"name":"imageByteStream","type_name":"ByteArrayOutputStream","description":"image data of the HouseMemberDocument to be saved.\n\n* ` ByteArrayOutputStream imageByteStream`: This is an output stream that stores binary data as a byte array. The method `toByteArray()` returns the contents of the stream as a byte array.","complex_type":true},{"name":"filename","type_name":"String","description":"name of the output file for the saved HouseMemberDocument.\n\n* `filename`: String representing the name of the document to be saved.","complex_type":true}],"returns":{"type_name":"HouseMemberDocument","description":"a new `HouseMemberDocument` instance saved to the repository.\n\n* `newDocument`: A new instance of `HouseMemberDocument`, representing a new document created by combining the image data from `imageByteStream` with its corresponding filename.\n* `houseMemberDocumentRepository`: The repository responsible for storing the newly created document in the database.","complex_type":true},"usage":{"language":"java","code":"public void someMethod() {\n    HouseMember member = new HouseMember(\"John\", \"Doe\");\n    HouseMemberDocument document = saveHouseMemberDocument(new ByteArrayOutputStream(), \"filename.jpg\");\n    // Save the document to the house member\n    member.setHouseMemberDocument(document);\n}\n","description":"\nThis code creates a new HouseMember object with the first name \"John\" and last name \"Doe\". It then creates a new HouseMemberDocument using an empty ByteArrayOutputStream, and sets the filename of the document to \"filename.jpg\". The created HouseMemberDocument is then set to the house member as its document."},"name":"saveHouseMemberDocument","code":"private HouseMemberDocument saveHouseMemberDocument(ByteArrayOutputStream imageByteStream,\n      String filename) {\n    HouseMemberDocument newDocument =\n        new HouseMemberDocument(filename, imageByteStream.toByteArray());\n    return houseMemberDocumentRepository.save(newDocument);\n  }","location":{"start":123,"insert":123,"offset":" ","indent":2},"item_type":"method","length":6},{"id":"be2617fb-28de-6198-724c-13938d5a1953","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"converts a `BufferedImage` into a JPEG file and stores it in an `OutputStream`.","params":[{"name":"documentImage","type_name":"BufferedImage","description":"2D image to be written to a byte stream as a JPEG file.\n\n* `BufferedImage`: This object represents an image that is to be written to a byte stream. It contains various attributes related to the image, such as its size, resolution, and color depth.\n* `ByteArrayOutputStream`: This object is used to store the output of the function, which is a byte array representing the image data.","complex_type":true},{"name":"imageByteStream","type_name":"ByteArrayOutputStream","description":"byte array that will store the written image data after being converted from an image format to JPEG format.\n\n* It is an instance of `ByteArrayOutputStream`, which is a class in Java for buffering bytes.\n* It has a capacity to hold at least 1024 bytes (the default size), but this can be changed by the user through its constructor.\n* It has a `write` method that allows you to write bytes to it.\n* It does not have any other properties or attributes beyond these basic functionalities.","complex_type":true}],"usage":{"language":"java","code":"BufferedImage documentImage = getImageFromMultipartFile(multipartFile);\ntry (ByteArrayOutputStream imageByteStream = new ByteArrayOutputStream()) {\n  writeImageToByteStream(documentImage, imageByteStream);\n} catch (IOException e) {\n  throw new RuntimeException(\"Error writing to ByteArrayOutputStream\", e);\n}\n","description":""},"name":"writeImageToByteStream","code":"private void writeImageToByteStream(BufferedImage documentImage,\n      ByteArrayOutputStream imageByteStream)\n      throws IOException {\n    ImageIO.write(documentImage, \"jpg\", imageByteStream);\n  }","location":{"start":130,"insert":130,"offset":" ","indent":2},"item_type":"method","length":5},{"id":"7370432a-de79-1f87-064e-24c2b0545bad","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"compresses a `BufferedImage` using an `ImageWriter`, setting the compression mode and quality to specified values, and writes the compressed image to a `ByteArrayOutputStream`.","params":[{"name":"bufferedImage","type_name":"BufferedImage","description":"2D image that will be compressed and written to an output stream.\n\n* The `BufferedImage` object represents a bitmapped image that has been loaded from an image file or stream.\n* It has several attributes such as `width`, `height`, `colorspace`, `imageType`, and others.\n* These attributes provide information about the image's format, size, and other characteristics.","complex_type":true},{"name":"imageByteStream","type_name":"ByteArrayOutputStream","description":"output stream where the compressed image will be written.\n\n* `BufferedImage bufferedImage`: The original image to be compressed.\n* `ByteArrayOutputStream imageByteStream`: A buffered output stream used to store the compressed image data as a byte array.\n* `IOException`: An exception class that may be thrown if there is an error during compression or output.\n* `ImageIO`: A class that provides methods for reading and writing images in various formats.\n* `ImageWriter`: An interface that provides methods for writing images to various output streams.\n* `ImageWriteParam`: An interface that provides methods for configuring the compression settings for an image write operation.\n* `MODE_EXPLICIT`: A value representing the compression mode, which can be either \"EXPLICIT\" or \"IMPLICIT\".\n* `compressedImageQuality`: A value representing the quality of the compressed image, which can range from 0 to 100.","complex_type":true}],"usage":{"language":"java","code":"// Compress image to a ByteArrayOutputStream\nByteArrayOutputStream byteStream = new ByteArrayOutputStream();\ntry {\n  BufferedImage image = ImageIO.read(file);\n  compressImageToByteStream(image, byteStream);\n} catch (IOException e) {\n  System.out.println(\"Error reading file\");\n  return;\n}\n","description":"\nExplanation:\nIn this example, the method is used to read an image from a file using ImageIO.read() and then compress it into a ByteArrayOutputStream with a compression quality set to 0.95 using the compressImageToByteStream method."},"name":"compressImageToByteStream","code":"private void compressImageToByteStream(BufferedImage bufferedImage,\n      ByteArrayOutputStream imageByteStream) throws IOException {\n\n    try (ImageOutputStream imageOutStream = ImageIO.createImageOutputStream(imageByteStream)) {\n\n      ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(\"jpg\").next();\n      imageWriter.setOutput(imageOutStream);\n      ImageWriteParam param = imageWriter.getDefaultWriteParam();\n\n      if (param.canWriteCompressed()) {\n        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);\n        param.setCompressionQuality(compressedImageQuality);\n      }\n      imageWriter.write(null, new IIOImage(bufferedImage, null, null), param);\n      imageWriter.dispose();\n    }\n  }","location":{"start":136,"insert":136,"offset":" ","indent":2},"item_type":"method","length":17},{"id":"93637d4a-2cd6-f2ae-7548-77b8a47c5df0","ancestors":["46b7cd06-29d0-fa86-8b4d-74e375ae390f"],"type":"function","description":"reads an image from an InputStream created from a MultipartFile object and returns the image as a BufferedImage object.","params":[{"name":"multipartFile","type_name":"MultipartFile","description":"MultipartFile object that contains the image file to be read.\n\n* `InputStream multipartFileStream`: A stream of binary data representing the contents of the uploaded file.\n* `MultipartFile multipartFile`: The file being processed, containing information such as the file name, size, and content type.","complex_type":true}],"returns":{"type_name":"BufferedImage","description":"a `BufferedImage` object.\n\n* The output is an instance of `BufferedImage`, which represents a raster image in Java.\n* The image is read from the input stream provided by the `MultipartFile` object using the `ImageIO.read()` method.\n* The resulting image is stored in the `BufferedImage` instance for later use or processing.","complex_type":true},"usage":{"language":"java","code":"import org.springframework.web.multipart.MultipartFile;\n\n// This code demonstrates how the getImageFromMultipartFile method is used.\n\npublic class Test {\n  public static void main(String[] args) throws IOException {\n    // create a MultipartFile object and initialize it with an image file\n    MultipartFile multipartFile = new MultipartFile(\"image.jpg\", \"image/jpg\".getBytes());\n    \n    // call the getImageFromMultipartFile method to read the image file from the multipartFile object\n    BufferedImage bufferedImage = Test.getImageFromMultipartFile(multipartFile);\n    \n    // do something with the bufferedImage object, e.g. display it on a web page\n    System.out.println(\"The width of the image is \" + bufferedImage.getWidth());\n  }\n}\n","description":""},"name":"getImageFromMultipartFile","code":"private BufferedImage getImageFromMultipartFile(MultipartFile multipartFile) throws IOException {\n    try (InputStream multipartFileStream = multipartFile.getInputStream()) {\n      return ImageIO.read(multipartFileStream);\n    }\n  }","location":{"start":154,"insert":154,"offset":" ","indent":2},"item_type":"method","length":5}]}}}