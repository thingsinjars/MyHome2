/*
 * Copyright 2020 Prathab Murugan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myhome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * represents an individual member of a house with unique identification, name, and
 * relationship to a community house through its memberId.
 * Fields:
 * 	- memberId (String): a unique identifier for each house member.
 * 	- houseMemberDocument (HouseMemberDocument): a document related to the member's
 * membership in a community house.
 * 	- name (String): a string value of a person's name associated with a particular
 * community house.
 * 	- communityHouse (CommunityHouse): a reference to a CommunityHouse object.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false, exclude = "communityHouse")
public class HouseMember extends BaseEntity {

  @With
  @Column(nullable = false, unique = true)
  private String memberId;

  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "document_id")
  private HouseMemberDocument houseMemberDocument;

  @With
  @Column(nullable = false)
  private String name;

  @ManyToOne
  private CommunityHouse communityHouse;
}
