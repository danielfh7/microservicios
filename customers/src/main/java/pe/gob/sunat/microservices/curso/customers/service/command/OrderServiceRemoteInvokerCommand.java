package pe.gob.sunat.microservices.curso.customers.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import pe.gob.sunat.microservices.curso.orders.client.OrderServiceClient;

public class OrderServiceRemoteInvokerCommand extends HystrixCommand<Boolean> {
  public static final int CONCURRENT_REQUESTS = 20;
  private final OrderServiceClient orderServiceClient;
  private final Long customerId;

  public OrderServiceRemoteInvokerCommand(OrderServiceClient orderServiceClient, Long customerId) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupComandoLatencia3"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationSemaphoreMaxConcurrentRequests(CONCURRENT_REQUESTS)));
    this.orderServiceClient = orderServiceClient;
    this.customerId = customerId;
  }


  @Override
  protected Boolean run() throws Exception {
    return !orderServiceClient.get(customerId).execute().isSuccessful();
  }

  @Override
  protected Boolean getFallback() {
    return false;
  }
}
