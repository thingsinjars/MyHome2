package com.myhome.utils;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@EqualsAndHashCode
@ToString
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageInfo {
  private final int currentPage;
  private final int pageLimit;
  private final int totalPages;
  private final long totalElements;

  /**
   * generates PageInfo data from a pageable and a page object, providing information
   * on the page number, size, total pages, and total elements.
   * 
   * @param pageable pagination information for the page being processed, providing the
   * number of pages and the size of each page.
   * 
   * @param page current page being processed, providing its total number of elements
   * and pages.
   * 
   * @returns a `PageInfo` object containing page number, size, total pages, and total
   * elements.
   */
  public static PageInfo of(Pageable pageable, Page<?> page) {
    return new PageInfo(
        pageable.getPageNumber(),
        pageable.getPageSize(),
        page.getTotalPages(),
        page.getTotalElements()
    );
  }
}
