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

package com.myhome.repositories;

import com.myhome.domain.Amenity;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * provides methods for retrieving Optional<Amenity> objects representing amenities
 * based on their amenityId, using Spring Data JPA queries and entity graphs.
 */
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

  @Query("from Amenity amenity where amenity.amenityId = :amenityId")
  @EntityGraph(value = "Amenity.community")
  Optional<Amenity> findByAmenityIdWithCommunity(@Param("amenityId") String amenityId);

  Optional<Amenity> findByAmenityId(String amenityId);
}
