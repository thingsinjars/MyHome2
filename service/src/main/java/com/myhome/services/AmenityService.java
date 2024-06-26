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

package com.myhome.services;

import com.myhome.domain.Amenity;
import com.myhome.model.AmenityDto;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * allows for the creation, retrieval, deletion, and update of amenities within a
 * community, using a set of methods that return optional lists, single entities, or
 * boolean values.
 */
public interface AmenityService {

  Optional<List<AmenityDto>> createAmenities(Set<AmenityDto> amenities, String communityId);

  Optional<Amenity> getAmenityDetails(String amenityId);

  boolean deleteAmenity(String amenityId);

  Set<Amenity> listAllAmenities(String communityId);

  boolean updateAmenity(AmenityDto updatedAmenityDto);
}
