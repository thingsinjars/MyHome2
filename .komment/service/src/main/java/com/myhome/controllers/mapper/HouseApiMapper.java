{"name":"HouseApiMapper.java","path":"service/src/main/java/com/myhome/controllers/mapper/HouseApiMapper.java","content":{"structured":{"description":"An interface `HouseApiMapper` that maps between two data structures: `Set<CommunityHouse>` and `Set<GetHouseDetailsResponseCommunityHouse>`, and vice versa. The interface implements the `Mapper` interface from the `org.mapstruct` package, which provides a way to map between different data structures in a type-safe manner.","image":"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n<!-- Generated by graphviz version 2.43.0 (0)\n -->\n<!-- Title: com.myhome.domain.CommunityHouse Pages: 1 -->\n<svg width=\"192pt\" height=\"148pt\"\n viewBox=\"0.00 0.00 192.00 148.00\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n<g id=\"graph0\" class=\"graph\" transform=\"scale(1 1) rotate(0) translate(4 144)\">\n<title>com.myhome.domain.CommunityHouse</title>\n<!-- Node1 -->\n<g id=\"Node000001\" class=\"node\">\n<title>Node1</title>\n<g id=\"a_Node000001\"><a xlink:title=\" \">\n<polygon fill=\"#999999\" stroke=\"#666666\" points=\"184,-30 0,-30 0,0 184,0 184,-30\"/>\n<text text-anchor=\"start\" x=\"8\" y=\"-18\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.domain.Community</text>\n<text text-anchor=\"middle\" x=\"92\" y=\"-7\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">House</text>\n</a>\n</g>\n</g>\n<!-- Node2 -->\n<g id=\"Node000002\" class=\"node\">\n<title>Node2</title>\n<g id=\"a_Node000002\"><a xlink:href=\"classcom_1_1myhome_1_1domain_1_1BaseEntity.html\" target=\"_top\" xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"181.5,-85 2.5,-85 2.5,-66 181.5,-66 181.5,-85\"/>\n<text text-anchor=\"middle\" x=\"92\" y=\"-73\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">com.myhome.domain.BaseEntity</text>\n</a>\n</g>\n</g>\n<!-- Node2&#45;&gt;Node1 -->\n<g id=\"edge1_Node000001_Node000002\" class=\"edge\">\n<title>Node2&#45;&gt;Node1</title>\n<g id=\"a_edge1_Node000001_Node000002\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M92,-55.65C92,-47.36 92,-37.78 92,-30.11\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"88.5,-55.87 92,-65.87 95.5,-55.87 88.5,-55.87\"/>\n</a>\n</g>\n</g>\n<!-- Node3 -->\n<g id=\"Node000003\" class=\"node\">\n<title>Node3</title>\n<g id=\"a_Node000003\"><a xlink:title=\" \">\n<polygon fill=\"white\" stroke=\"#666666\" points=\"129,-140 55,-140 55,-121 129,-121 129,-140\"/>\n<text text-anchor=\"middle\" x=\"92\" y=\"-128\" font-family=\"Helvetica,sans-Serif\" font-size=\"10.00\">Serializable</text>\n</a>\n</g>\n</g>\n<!-- Node3&#45;&gt;Node2 -->\n<g id=\"edge2_Node000002_Node000003\" class=\"edge\">\n<title>Node3&#45;&gt;Node2</title>\n<g id=\"a_edge2_Node000002_Node000003\"><a xlink:title=\" \">\n<path fill=\"none\" stroke=\"#63b8ff\" d=\"M92,-110.66C92,-101.93 92,-91.99 92,-85.09\"/>\n<polygon fill=\"#63b8ff\" stroke=\"#63b8ff\" points=\"88.5,-110.75 92,-120.75 95.5,-110.75 88.5,-110.75\"/>\n</a>\n</g>\n</g>\n</g>\n</svg>\n","items":[{"id":"d0ce816a-436f-64bc-5d4d-59d79674d5e4","ancestors":[],"type":"function","description":"defines a mapping between sets of CommunityHouse objects and GetHouseDetailsResponseCommunityHouse objects using MapStruct.","name":"HouseApiMapper","code":"@Mapper\npublic interface HouseApiMapper {\n  Set<GetHouseDetailsResponseCommunityHouse> communityHouseSetToRestApiResponseCommunityHouseSet(\n      Set<CommunityHouse> communityHouse);\n\n  GetHouseDetailsResponseCommunityHouse communityHouseToRestApiResponseCommunityHouse(\n      CommunityHouse communityHouse);\n}","location":{"start":24,"insert":24,"offset":" ","indent":0},"item_type":"interface","length":8}]}}}