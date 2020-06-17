# Cartering Service

It's a very simple **Java Enterprise** project with **Maven* and **WildFly**.

In the preject have to user tipe: ADMIN, USER

The Admin user can uploat food menus and he can change the order status (Accepted, Declined, Shipping, Completed, ...)
The can register and he can order a menu. (And after the ordering, he can check the order status).

## API endpoinds

Login (POST)
```
http:localhost:8080/cartering-api/login

```

Logout  (GET)
```
http:localhost:8080/cartering-api/logout     
```

Registration  (POST)
```
http:localhost:8080/cartering-api/reistration     
```

List menus  (GET)
```
http:localhost:8080/cartering-api/menu     
``` 

Get a menu  (GET)
```
http:localhost:8080/cartering-api/menu/{id}     
``` 

Upload a menu  (POST)
```
http:localhost:8080/cartering-api/menu    
``` 

Upload a menu  (POST)
```
http:localhost:8080/cartering-api/menu    
``` 

Change the order status (POST, only Admin)
```
http:localhost:8080/cartering-api/order/state/{orderNumber}    
```


Change the order status (POST, only Admin)
```
http:localhost:8080/cartering-api/orders/state/{orderNumber}    
```

Menu order (POST)
```
http:localhost:8080/cartering-api/orders   
```

User orders (GET)
```
http:localhost:8080/cartering-api/orders   
```

Menu orders (GET)
```
http:localhost:8080/cartering-api/orders/{menuId}   
```

## Installing

Clone the repo:
```
git clone https://github.com/forisnikoletta/cartering-company.git
```
After if you want to deplo:
```
mvn wildfly:deploy or mvn wildfly:redeploy 
```
