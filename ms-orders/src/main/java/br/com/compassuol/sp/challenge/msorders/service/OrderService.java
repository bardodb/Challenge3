package br.com.compassuol.sp.challenge.msorders.service;

import br.com.compassuol.sp.challenge.msorders.dto.AddressDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderCreateDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderDetailsDTO;
import br.com.compassuol.sp.challenge.msorders.dto.OrderUpdateDTO;

import java.util.List;

public interface OrderService {
    OrderDTO makeOrder(OrderCreateDTO orderCreateDTO);

    List<OrderDTO> getAllOrders();

    OrderDetailsDTO getOrderDetails(Long id);

    OrderDTO updateOrder(OrderUpdateDTO orderUpdateDTO, Long id);

    List<AddressDTO> getAllAddress();




}
