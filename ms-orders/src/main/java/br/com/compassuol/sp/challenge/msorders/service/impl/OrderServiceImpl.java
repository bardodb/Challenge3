package br.com.compassuol.sp.challenge.msorders.service.impl;

import br.com.compassuol.sp.challenge.msorders.dto.*;
import br.com.compassuol.sp.challenge.msorders.entity.Address;
import br.com.compassuol.sp.challenge.msorders.entity.Order;
import br.com.compassuol.sp.challenge.msorders.entity.OrderProduct;
import br.com.compassuol.sp.challenge.msorders.exception.ResourceNotFoundException;
import br.com.compassuol.sp.challenge.msorders.publisher.OrderProducer;
import br.com.compassuol.sp.challenge.msorders.repository.AddressRepository;
import br.com.compassuol.sp.challenge.msorders.repository.OrderProductRepository;
import br.com.compassuol.sp.challenge.msorders.repository.OrderRepository;
import br.com.compassuol.sp.challenge.msorders.service.APIClient;
import br.com.compassuol.sp.challenge.msorders.service.OrderService;
import br.com.compassuol.sp.challenge.msorders.service.ViaCepAPIClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private AddressRepository addressRepository;

    private APIClient apiClient;
    private ViaCepAPIClient viaCepAPIClient;


    @Override
    public OrderDTO makeOrder(OrderCreateDTO orderCreateDTO) {
        System.out.println(viaCepAPIClient.getAddress("95800-000").toString());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(orderCreateDTO.getUserId());
        orderDTO.setStatus("PENDING");

        String zipCode = orderCreateDTO.getDeliveryAddress().getZipCode();

        AddressDTO addressDTO = viaCepAPIClient.getAddress(zipCode);
        addressDTO.setComplement(orderCreateDTO.getDeliveryAddress().getComplement());
        Address address = mapToAddress(addressDTO);
        orderDTO.setProducts(orderCreateDTO.getProducts());
        Order order = mapToEntity(orderDTO);
        order.setAddress(address);
        Order savedOrder = orderRepository.save(order);
        OrderDTO orderDTO1 = mapToOrderDTO(savedOrder);
        orderDTO1.setProducts(orderDTO.getProducts());

        for (Long productId : orderDTO1.getProducts()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(productId);
            orderProduct.setOrderId(orderDTO1.getId());
            OrderProduct newOrderProduct = orderProductRepository.save(orderProduct);
        }

        return orderDTO1;

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderDTO).collect(Collectors.toList());
    }


    @Override
    public OrderDetailsDTO getOrderDetails(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order","id",id));
        return mapToOrderDetailsDTO(order);
    }



    @Override
    public OrderDTO updateOrder(OrderUpdateDTO orderUpdateDTO, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order","id",id));
        order.setStatus(orderUpdateDTO.getStatus());
        Order updateOrder = orderRepository.save(order);
        return mapToOrderDTO(updateOrder);
    }

    @Override
    public List<AddressDTO> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::mapToAddressDTO).collect(Collectors.toList());
    }

    private Order mapToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setStatus(orderDTO.getStatus());


        return order;
    }

    private OrderDTO mapToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setStatus(order.getStatus());
        List<OrderProduct> orderProducts = orderProductRepository.findProductIdByOrderId(order.getId());
        List<Long> productIds = orderProducts.stream().map(OrderProduct::getProductId).toList();
        orderDTO.setProducts(productIds);

        orderDTO.setDeliveryAddress(mapToAddressDTO(order.getAddress()));
        return orderDTO;
    }
    private OrderDetailsDTO mapToOrderDetailsDTO(Order order) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setId(order.getId());
        orderDetailsDTO.setUserId(order.getUserId());
        orderDetailsDTO.setStatus(order.getStatus());
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderProduct> orderProducts = orderProductRepository.findProductIdByOrderId(order.getId());
        List<Long> productIds = orderProducts.stream().map(OrderProduct::getProductId).toList();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Long productId : productIds){
            ProductDTO productDTO = apiClient.getProductById(productId);
            productDTOS.add(productDTO);
            totalPrice = totalPrice.add(productDTO.getPrice());
        }
        orderDetailsDTO.setTotalPrice(totalPrice);
        orderDetailsDTO.setProducts(productDTOS);
        orderDetailsDTO.setDeliveryAddress(mapToAddressDTO(order.getAddress()));
        return orderDetailsDTO;
    }


    private Address mapToAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setZipCode(addressDTO.getZipCode());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setDistrict(addressDTO.getDistrict());
        address.setComplement(addressDTO.getComplement());
        address.setState(addressDTO.getState());
        return address;
    }


    private AddressDTO mapToAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setState(address.getState());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setDistrict(address.getDistrict());
        return addressDTO;
    }








}
