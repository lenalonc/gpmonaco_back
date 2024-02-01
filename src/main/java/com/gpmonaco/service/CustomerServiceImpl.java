package com.gpmonaco.service;

import com.gpmonaco.dto.CustomerDTO;
import com.gpmonaco.entities.Customer;
import com.gpmonaco.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    ModelMapper mapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO kupacDTO) {
        Customer customer = mapper.map(kupacDTO, Customer.class);
        return mapper.map(customerRepository.save(customer), CustomerDTO.class);
    }
}
