package com.tvd12.ezydata.example.jpa.converter;

import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.data.BookData;
import com.tvd12.ezydata.example.jpa.data.CategoryData;
import com.tvd12.ezydata.example.jpa.response.AuthorResponse;
import com.tvd12.ezydata.example.jpa.response.BookResponse;
import com.tvd12.ezydata.example.jpa.response.CategoryResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;
import java.util.stream.Collectors;

@EzySingleton
public class DataToResponseConverter {
	public AuthorResponse toResponse(
		AuthorData data
	) {
		return AuthorResponse.builder()
			.id(data.getId())
			.name(data.getName())
			.build();
	}

	public CategoryResponse toResponse(
		CategoryData data
	) {
		return CategoryResponse.builder()
			.id(data.getId())
			.name(data.getName())
			.build();
	}

	public BookResponse toResponse(BookData bookData) {
		return BookResponse.builder()
			.bookId(bookData.getId())
			.bookName(bookData.getName())
			.author(toResponse(bookData.getAuthor()))
			.category(toResponse(bookData.getCategory()))
			.price(bookData.getPrice())
			.releaseDate(bookData.getReleaseDate())
			.releaseTime(bookData.getReleaseTime())
			.build();
	}

	public List<BookResponse> toResponseList(List<BookData> dataList) {
		return dataList.stream()
			.map(this::toResponse)
			.collect(Collectors.toList());
    }
}
