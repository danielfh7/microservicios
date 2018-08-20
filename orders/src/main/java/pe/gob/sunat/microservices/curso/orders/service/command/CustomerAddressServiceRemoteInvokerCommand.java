package pe.gob.sunat.microservices.curso.orders.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import pe.gob.sunat.microservices.curso.customers.client.CustomerServiceClient;

public class CustomerAddressServiceRemoteInvokerCommand extends HystrixCommand<Boolean> {
  public static final int CONCURRENT_REQUESTS = 20;
  private final CustomerServiceClient customerServiceClient;
  private final Long customerId;
  private final Long addressId;

  public CustomerAddressServiceRemoteInvokerCommand(CustomerServiceClient customerServiceClient, Long customerId, Long addressId) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupComandoLatencia2"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationSemaphoreMaxConcurrentRequests(CONCURRENT_REQUESTS)));
    this.customerServiceClient = customerServiceClient;
    this.customerId = customerId;
    this.addressId = addressId;
  }

  @Override
  protected Boolean run() throws Exception {
    return customerServiceClient.getAddress(customerId, addressId).execute().isSuccessful();
  }

  @Override
  protected Boolean getFallback() {
    return false;
  }
}
