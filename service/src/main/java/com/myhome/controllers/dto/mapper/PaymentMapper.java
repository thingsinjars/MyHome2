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
package com.myhome.controllers.dto.mapper;

import com.myhome.controllers.dto.PaymentDto;
import com.myhome.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Provides conversion between DTO and entity object
 */
/**
 * provides conversion between DTO and entity objects for the Payment domain object.
 */
@Mapper
public interface PaymentMapper {
  Payment paymentDtoToPayment(PaymentDto paymentDto);

  @Mapping(source = "payment.dueDate", target = "dueDate", dateFormat = "yyyy-MM-dd")
  PaymentDto paymentToPaymentDto(Payment payment);
}
