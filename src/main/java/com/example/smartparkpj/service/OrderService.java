package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void add(OrderDTO orderDTO);

    List<OrderDTO> getOneAll(String email_id);

    void modifyFinished(String orderVO);

    void modifyHasAbility(String orderVO);
}
