import com.revature.scoops.daos.OrderDao;
import com.revature.scoops.models.Orders;
import org.junit.jupiter.api.Test;

import java.util.List;

public class methodTests {
    OrderDao orderDao = new OrderDao();

        @Test
        public void readAll(){
            // TODO: What OrderDao intellisense telling me?
            List<Orders> orders = orderDao.findAll();
            System.out.println(orders.toString());
        }
    }
