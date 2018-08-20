echo Registrando usuario domix
http :8090/v1/users username=domix password=secreto -v
echo Registrando usuario beto
http :8090/v1/users username=beto password=elmo -v
echo Registrando un cliente
http :8080/v1/customers id=40066838 firstName=Daniel lastName=Fernandez businessName=DFH email=danielfh@outlook.com taxId=10400668383 -v -a beto:elmo
echo Registrando una direccion del cliente
http :8080/v1/customers/1/addresses street=Liberata name=Casa country=Peru customerId=1 -v -a beto:elmo
echo Registrando una orden VALIDA del cliente
http :9000/v1/orders amount=1000 customerId=1 deliveryAddressId=1 -v -a beto:elmo
echo Registrando una orden INVALIDA por cliente inexistente: GENERA ERROR------------------------------------------
http :9000/v1/orders amount=1000 customerId=50 deliveryAddressId=1 -v -a beto:elmo
echo Registrando una orden INVALIDA por direccion inexistente: GENERA ERROR----------------------------------------
http :9000/v1/orders amount=1000 customerId=1 deliveryAddressId=90 -v -a beto:elmo
echo Eliminando un cliente con ordenes existentes: GENERA ERROR----------------------------------------------------
http delete :8080/v1/customers/1 -v -a beto:elmo
