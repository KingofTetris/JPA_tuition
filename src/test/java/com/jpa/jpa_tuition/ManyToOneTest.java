package com.jpa.jpa_tuition;

import com.jpa.jpa_tuition.dao.CustomerRepository;
import com.jpa.jpa_tuition.dao.MessageRepository;
import com.jpa.jpa_tuition.entity.Customer;
import com.jpa.jpa_tuition.entity.Message;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author by KingOfTetris
 * @date 2023/6/20
 */

@SpringBootTest
public class ManyToOneTest {
    @Autowired
    MessageRepository repository;

    @Autowired
    CustomerRepository customerRepository;
    /**
     * 当你要插入多的数据的时候，你就需要从多的这一方来进行保存
     * 这样不需要update少的那方，更加合理。
     */
    @Test
    public void test(){
        Customer customer = new Customer();
        customer.setId(4);
        customer.setCustName("司马懿");
        customer.setCustAddress("洛阳");
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("还好",customer));
        messageList.add(new Message("在的",customer));
        repository.saveAll(messageList);
    }

    //根据用户ID查询他发过的所有信息
    @Test
    @Transactional
    public void test2(){
        Optional<Customer> infos = customerRepository.findById(4);
//        customer.getMessages().forEach(System.out::println);
        //小心这个输出，你的@Data 会隐式地调用Customer.toString
        //而Customer.toString包含了Message.toString
        //而Message.toString又包含了Customer.toString
        //所以才会Stack overflow
        //解决办法就是自己写个toString.
        //避开两个toString互相调用
        /**
         * 所以出现双向关联的时候，你最好不要用@Data自己写的toString
         * 这就是为什么有的公司禁用@Data的原因之一。
         */
        infos.ifPresent(System.out::println);
    }

    @Test
    @Transactional
    @Commit
    public void test4(){
        Customer customer = new Customer();
        customer.setId(4);
        Message message = new Message();
        message.setCustomer(customer);
        repository.delete(message);
    }
}
