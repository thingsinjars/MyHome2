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
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * represents a document associated with a house member, containing a unique filename
 * and binary content.
 * Fields:
 * 	- documentFilename (String): in the HouseMemberDocument class represents a unique
 * filename for a document belonging to a member of a house.
 * 	- documentContent (byte[]): in the HouseMemberDocument class represents an array
 * of bytes containing the contents of a document, with a default size of zero bytes.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"documentFilename"}, callSuper = false)
public class HouseMemberDocument extends BaseEntity {

  @Column(unique = true)
  private String documentFilename;

  @Lob
  @Column
  private byte[] documentContent = new byte[0];
}
