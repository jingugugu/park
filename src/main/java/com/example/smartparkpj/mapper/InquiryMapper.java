package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryMapper {

    void addInquiry(InquiryVO inquiryVO);

}
