package pe.gob.sunat.microservices.curso.orders.client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;
import pe.gob.sunat.microservices.curso.orders.client.Order;

public interface OrderServiceClient {
  @GET("v1/orders/_customer/{id}")
  Call<List<Order>> get(@Path("id") Long id);
}
