package sdp.shop.now.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sdp.shop.now.dao.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}