package org.zhenchao.visitor.grind;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zhenchao.wang 2019-11-25 14:08
 * @version 1.0.0
 */
public class ObjectStructure {

    private Collection<Customer> customers = new ArrayList<>();

    public void handleRequest(Visitor visitor) {
        for (final Customer customer : customers) {
            customer.accept(visitor);
        }
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

}
