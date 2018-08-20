package pe.gob.sunat.microservices.curso.orders.service;

import pe.gob.sunat.microservices.curso.customers.client.CustomerServiceClient;
import pe.gob.sunat.microservices.curso.orders.service.command.CustomerServiceRemoteInvokerCommand;
import pe.gob.sunat.microservices.curso.orders.service.command.CustomerAddressServiceRemoteInvokerCommand;

public class CustomerService {
  private final CustomerServiceClient customerServiceClient;

  public CustomerService(CustomerServiceClient customerServiceClient) {
    this.customerServiceClient = customerServiceClient;
  }

  public Boolean validateCustomer(Long id) {
    return
      new CustomerServiceRemoteInvokerCommand(customerServiceClient, id)
        .execute();
  }

  public Boolean validateAddress(Long customerId, Long addressId) {
    return
      new CustomerAddressServiceRemoteInvokerCommand(customerServiceClient, customerId, addressId)
        .execute();
  }
}
