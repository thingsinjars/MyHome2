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

import lombok.*;

import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * represents a booking item for an amenity with unique amenity ID and booking user,
 * along with start and end dates for the booking.
 * Fields:
 * 	- amenityBookingItemId (String): in the AmenityBookingItem class represents a
 * unique identifier for each booking item, used to distinguish it from other bookings
 * of the same amenity by different users.
 * 	- amenity (Amenity): in the AmenityBookingItem entity represents an amenity or
 * service associated with a booking item.
 * 	- bookingStartDate (LocalDateTime): represents the date and time when the booking
 * for the amenity started.
 * 	- bookingEndDate (LocalDateTime): represents the end time of a booking for an amenity.
 * 	- bookingUser (User): in the AmenityBookingItem class represents the user who
 * made the booking for the amenity item.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"amenityBookingItemId"})
@Getter
@Setter
@With
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "AmenityBookingItem.amenity",
        attributeNodes = {
            @NamedAttributeNode("amenity"),
        }),
    @NamedEntityGraph(
        name = "AmenityBookingItem.bookingUser",
        attributeNodes = {
            @NamedAttributeNode("bookingUser"),
        })
})

public class AmenityBookingItem extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String amenityBookingItemId;
  @ManyToOne(fetch = FetchType.LAZY)
  private Amenity amenity;
  @Column(nullable = false)
  private LocalDateTime bookingStartDate;
  @Column
  private LocalDateTime bookingEndDate;
  @ManyToOne(fetch = FetchType.LAZY)
  private User bookingUser;
}
