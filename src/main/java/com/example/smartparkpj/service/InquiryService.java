package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.mapper.InquiryMapper;

import java.util.Date;
import java.util.List;

public interface InquiryService {

    List<InquiryDTO> getAll(int mno);

    InquiryDTO getOne(int ino);

    InquiryDTO getOneRead(int ino, int mno);

    void modify(InquiryDTO inquiryDTO);

    List<Date> getFormattedDate();

    void remove(int ino);

    void add(InquiryDTO inquiryDTO);

    void updateAnswer(InquiryDTO inquiryDTO);

    List<InquiryDTO> getAdminListAll();

    void adminRemove(int ino);
}
