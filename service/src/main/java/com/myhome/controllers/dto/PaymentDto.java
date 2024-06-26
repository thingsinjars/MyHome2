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

package com.myhome.controllers.dto;

import com.myhome.model.HouseMemberDto;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * is a data transfer object for representing payment information, including payment
 * ID, charge amount, payment type, description, recurring status, due date, and admin
 * and member details.
 * Fields:
 * 	- paymentId (String): in the PaymentDto class represents a unique identifier for
 * each payment made by a member of a house.
 * 	- charge (BigDecimal): represents a monetary value.
 * 	- type (String): in the PaymentDto class represents a string indicating the type
 * of payment, such as "credit card", "bank transfer", or "other".
 * 	- description (String): in the PaymentDto class represents a human-readable
 * explanation of the payment, which may include information about the purpose of the
 * payment or any relevant details.
 * 	- recurring (boolean): in the PaymentDto represents whether the payment is ongoing
 * or a one-time transfer.
 * 	- dueDate (String): represents the date by which a payment is intended to be made.
 * 	- admin (UserDto): in the PaymentDto class represents an user who manages or
 * oversees payments.
 * 	- member (HouseMemberDto): in the PaymentDto class represents an association
 * between a payment and a specific house member.
 */
@Builder
@Getter
@Setter
public class PaymentDto {
  private String paymentId;
  private BigDecimal charge;
  private String type;
  private String description;
  private boolean recurring;
  private String dueDate;
  private UserDto admin;
  private HouseMemberDto member;
}
