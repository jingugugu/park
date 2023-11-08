package com.example.smartparkpj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;
    // 끝 페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    private int sno;
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        // 페이지 목차
        this.page = pageRequestDTO.getPage(); // 현재 페이지
        this.size = pageRequestDTO.getSize(); // 페이지 목차에 들어있는 목록 개수

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10; // 목차 끝 번호
        this.start = this.end - 9; // 목차 처음 번호

        int last = (int)(Math.ceil(total/(double)size)); // 가장 마지막 페이지 번호

        this.end = end > last ? last : end; // 목차 끝번호가 마지막 페이지 번호인가?

        this.prev = this.start > 1; // 이전 버튼 여부
        this.next = total > this.end * this.size; // 다음 버튼 여부

        this.sno = (total - (this.page - 1) * this.size);

        this.total = total; // 전체 게시글 개수
        this.dtoList = dtoList;
    }
}
